package org.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jaxb.ObjectFactory;
import jaxb.ValCurs;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.xml.transform.StringSource;
import org.w3c.dom.Document;


import javax.xml.bind.JAXBElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Glebza on 16.10.2016.
 */

public class CbService {

    public static final Logger logger = Logger.getLogger(CbService.class);

    private String cbUrl;
    private Map<String, ValCurs.Valute> valutes;


    public CbService(String cbUrl) {
        this.cbUrl = cbUrl;
    }

    public CbService() {
        logger.debug("In CbConstr");

    }

    private Jaxb2Marshaller marshaller;


    public Map<String, ValCurs.Valute> getCurrenciesRate() {


        String result = null;
        HttpURLConnection connection = null;
        try {
            //Create connection

            URL url = new URL(this.cbUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/xml");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, "windows-1251"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            rd.close();
            result = response.toString();
            logger.debug("CbResponse is=" + result);
            ValCurs valCurs = (ValCurs) marshaller.unmarshal(new StringSource(result));
            valutes = valCurs.getValutes();
            return valutes;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    public void setCbUrl(String cbUrl) {
        this.cbUrl = cbUrl;
    }

    public String getCbUrl() {
        return cbUrl;
    }

    public void setMarshaller(Jaxb2Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Jaxb2Marshaller getMarshaller() {
        return marshaller;
    }
}