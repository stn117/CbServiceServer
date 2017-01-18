package dao;

import exchangers.Exchanger;
import org.services.BanksService;
import org.services.CbService;

/**
 * Created by Glebza on 21.11.2016.
 */
public class ClientPayload {
    private CbService cbService;

    private BanksService banksService;
    private Exchanger exchanger;

    public ClientPayload(CbService cbService, BanksService banksService, Exchanger exchanger) {
        this.cbService = cbService;
        this.banksService = banksService;
        this.exchanger = exchanger;
    }


}
