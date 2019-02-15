package com.hibernatetutorial.demo.loader;

import com.hibernatetutorial.demo.DemoApplication;
import com.hibernatetutorial.demo.dao.TblProductDAO;
import com.hibernatetutorial.demo.entity.TblProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ProductLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductLoader.class);
    @Autowired
    private static TblProductDAO tblProductDAO;

    public static ConcurrentHashMap<String, TblProduct> productMap = new ConcurrentHashMap<String, TblProduct>();
    public static Integer allDataSize = 0;
    public static void load() {
        try {
            ApplicationContext ctx = DemoApplication.getContext();
            tblProductDAO = ctx.getBean(TblProductDAO.class);

            List<TblProduct> list = tblProductDAO.findAll();
            productMap.clear();
            for (TblProduct product : list) {
                productMap.put(product.getKodeProduct(), product);
            }
            allDataSize = list.size();
            LOGGER.info("Product Loader Size : {}",list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TblProduct getProductBy(String productCode) {
        return productMap.get(productCode);
    }

}
