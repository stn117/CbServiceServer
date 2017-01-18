package org.services;

import dao.DbUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;

/**
 * Created by Glebza on 19.07.2016.
 */
//TODO: настроить работу logger
@RestController
public class Starter {
    public static final Logger logger = Logger.getLogger(Starter.class);

    @Autowired
  public ClientCourier clientCourier;





    public Starter(ClientCourier clientCourier) {
        this.clientCourier = clientCourier;
    }

    @RequestMapping(value = "/getRates", method = RequestMethod.GET,produces = "text/plain;charset=CP1251")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        logger.debug("it logs!");


        return clientCourier.sendToClient();

    }



    public void setCbService(CbService CbService) {
        logger.debug("it logs!");
    }
}



