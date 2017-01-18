package exchangers;

/**
 * Created by Glebza on 29.11.2016.
 */
public class BankExchanger {

    private String bankName;
    private double dollarSell;
    private double dollarBuy;
    private double euroBuy;
    private double euroSell;



    public  final int  BANK_TYPE=0;

    public BankExchanger(String bankName, double dollarSell, double dollarBuy, double euroBuy, double euroSell) {
        this.bankName = bankName;
        this.dollarSell = dollarSell;
        this.dollarBuy = dollarBuy;
        this.euroBuy = euroBuy;
        this.euroSell = euroSell;
    }

    public BankExchanger() {
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getDollarSell() {
        return dollarSell;
    }

    public void setDollarSell(double dollarSell) {
        this.dollarSell = dollarSell;
    }

    public double getDollarBuy() {
        return dollarBuy;
    }

    public void setDollarBuy(double dollarBuy) {
        this.dollarBuy = dollarBuy;
    }

    public double getEuroBuy() {
        return euroBuy;
    }

    public void setEuroBuy(double euroBuy) {
        this.euroBuy = euroBuy;
    }

    public double getEuroSell() {
        return euroSell;
    }

    public void setEuroSell(double euroSell) {
        this.euroSell = euroSell;
    }
    public int getType() {
        return BANK_TYPE;
    }
}
