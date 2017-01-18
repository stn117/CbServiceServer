package dao;

import exchangers.BankExchanger;
import org.apache.log4j.Logger;
import org.services.CbService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;

/**
 * Created by Glebza on 01.12.2016.
 */
public class DbUtils {
    private String dbPassword;
    private String dbUser;
    private String dbPort;
    private String dbHost;
    private String dbName;
    public static final Logger logger = Logger.getLogger(DbUtils.class);

    public String insertBatch(Map<String, BankExchanger> bankRates) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName,
                            dbUser, dbPassword);
            stmt = c.createStatement();

            logger.debug(bankRates.size());
            for (Map.Entry<String, BankExchanger> bank : bankRates.entrySet()) {




                stmt.addBatch("INSERT INTO FIN_OBJECTS(TYPE_ID, FIN_OBJ_NAME, SALE_EU, BUY_EU, SALE_DL, BUY_DL ) values("
                        + bank.getValue().getType() + ", " +
                        "'" + bank.getValue().getBankName() + "'" +  "," +
                        bank.getValue().getEuroSell() + "," +
                        bank.getValue().getEuroBuy() + "," +
                        bank.getValue().getDollarSell() + "," +
                        bank.getValue().getDollarBuy() +  ");");
            }

            stmt.executeBatch();
            stmt.close();
            c.close();
        } catch (Exception e) {

            e.printStackTrace();
            logger.error(e.getClass().getName() + ": " + e.getMessage());
            return e.toString();
        }
        return "success";
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbPort() {
        return dbPort;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public String getDbHost() {
        return dbHost;
    }


    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }
}
