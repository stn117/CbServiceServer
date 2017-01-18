package org.services;

import com.google.gson.Gson;
import dao.DbUtils;
import exchangers.BankExchanger;
import exchangers.Exchanger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Glebza on 21.11.2016.
 */
public class ClientCourier {
    public static final Logger logger = Logger.getLogger(ClientCourier.class);

    private CbService cbService;

    private BanksService banksService;

    private Exchanger exchanger;
    private Map<String, BankExchanger> banksExchangers;


    private DbUtils dbUtils;

    public String sendToClient() {
        ObjectMapper objectMapper = new ObjectMapper();
        String result;
        try {
            String banksRates;
            String cbRates = objectMapper.writeValueAsString(this.cbService.getCurrenciesRate());
            banksExchangers = banksService.getBanksRates();
            dbUtils.insertBatch(banksExchangers);
            Gson gson = new Gson();
            banksRates = gson.toJson(banksExchangers);
            //    String exchgangerRates = exchanger.getExchangersRates();
            result = cbRates + banksRates; //+ exchgangerRates;
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return "Jonny it's a fuckup!";
        }


    }

    public CbService getCbService() {
        return cbService;
    }

    public void setCbService(CbService cbService) {
        this.cbService = cbService;
    }

    public BanksService getBanksService() {
        return banksService;
    }

    public void setBanksService(BanksService banksService) {
        this.banksService = banksService;
    }

    public Exchanger getExchanger() {
        return exchanger;
    }

    public void setExchanger(Exchanger exchanger) {
        this.exchanger = exchanger;
    }

    public void setDbUtils(DbUtils dbUtils) {
        this.dbUtils = dbUtils;
    }

    public DbUtils getDbUtils() {
        return dbUtils;
    }
}
