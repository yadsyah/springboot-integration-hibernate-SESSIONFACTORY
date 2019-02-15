package com.hibernatetutorial.demo.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
public class Loader {
    private static final Logger LOGGER = LoggerFactory.getLogger(Loader.class);
    private static Long startTime = new Date().getTime();

    public static void load() throws Exception {
        int totalData = 0;
        try{
            LOGGER.info("Loader Start !! ");
            TarifLoader.load();
            ProductJaminanLoader.load();
            ParamLoader.load();
            ProductLoader.load();
            totalData = TarifLoader.allDataSize +
                    ProductJaminanLoader.allDataSize + ParamLoader.allDataSize +
                    ProductLoader.allDataSize;
            LOGGER.info("Loader End !! {} ms", new Date().getTime() - startTime);
            LOGGER.info("Total Data Load : {} ", totalData);
        } catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Error : {}",e.getMessage());
        }
    }
}
