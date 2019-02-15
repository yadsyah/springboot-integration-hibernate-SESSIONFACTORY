package com.hibernatetutorial.demo.loader;


import com.hibernatetutorial.demo.DemoApplication;
import com.hibernatetutorial.demo.dao.TblStandardHargaDAO;
import com.hibernatetutorial.demo.entity.TblStandardHarga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TarifLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(TarifLoader.class);
    public static ConcurrentHashMap<Long, TblStandardHarga> hStlStp = new ConcurrentHashMap<Long, TblStandardHarga>();

    @Autowired
    private static TblStandardHargaDAO tblStandardHargaDAO;
    public static Integer allDataSize = 0;
    public static void load() {
        ApplicationContext ctx = DemoApplication.getContext();
        tblStandardHargaDAO = ctx.getBean(TblStandardHargaDAO.class);
        /*
         * STL LOADER
         */
        hStlStp.clear();
        List<TblStandardHarga> standardHargaList = tblStandardHargaDAO.findAll();
        if(standardHargaList!=null){
            for (TblStandardHarga standardHarga : standardHargaList) {
                hStlStp.put(standardHarga.getId(), standardHarga);
            }
            LOGGER.info("Tarif Loader : {}",standardHargaList.size());
        }
        allDataSize = standardHargaList.size();
    }
    /*
     * Get List Harga
     * @return
     */
    public static ConcurrentHashMap<Long, TblStandardHarga> getListStandardHarga() {
        return hStlStp;
    }

}
