package org.services;

import exchangers.BankExchanger;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Glebza on 21.11.2016.
 */
public class BanksService {
    public static final Logger logger = Logger.getLogger(CbService.class);
    public static final int TBODY = 1;
    public static final int BANK = 0;
    public static final int BANK_NAME = 0;
    public static final int DOLLAR_BUY = 1;
    public static final int DOLLAR_SELL = 2;
    public static final int EURO_BUY = 3;
    public static final int EURO_SELL = 4;
    public Map<String, BankExchanger> rates = new TreeMap<>();

    public Map<String, BankExchanger> getBanksRates() {
        try {
            logger.debug("wow we are in a bank!");
            Document doc = Jsoup.connect("http://www.exocur.ru/").get();

            Elements table = doc.select("table.sortable");

            for (Element element : table) {
                logger.debug(element.tagName());
            }
            Elements banks = table.select("tbody").get(0).children();
            for (Element link : banks) {
                BankExchanger bankExchanger = new BankExchanger();
                bankExchanger.setBankName(link.child(BANK_NAME).text());
                bankExchanger.setDollarBuy(Double.parseDouble(link.child(DOLLAR_BUY).text()));
                bankExchanger.setDollarSell(Double.parseDouble(link.child(DOLLAR_SELL).text()));
                bankExchanger.setEuroBuy(Double.parseDouble(link.child(EURO_BUY).text()));
                bankExchanger.setEuroSell(Double.parseDouble(link.child(EURO_SELL).text()));
                rates.put(bankExchanger.getBankName(),bankExchanger);

            }
            System.out.println(rates.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rates;
    }
}
