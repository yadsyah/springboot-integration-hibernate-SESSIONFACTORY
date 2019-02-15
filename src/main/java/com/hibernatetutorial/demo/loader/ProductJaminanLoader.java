package com.hibernatetutorial.demo.loader;

import com.hibernatetutorial.demo.DemoApplication;
import com.hibernatetutorial.demo.dao.TblGolonganDAO;
import com.hibernatetutorial.demo.dao.TblRasioTarifDAO;
import com.hibernatetutorial.demo.dao.TblTipeJaminanDAO;
import com.hibernatetutorial.demo.entity.TblGolongan;
import com.hibernatetutorial.demo.entity.TblRasioTarif;
import com.hibernatetutorial.demo.entity.TblTipeJaminan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ProductJaminanLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductJaminanLoader.class);

    private static ConcurrentHashMap<String, ConcurrentHashMap<String, TblTipeJaminan>> hTipejaminanByRubrik = new ConcurrentHashMap<String, ConcurrentHashMap<String, TblTipeJaminan>>();
    private static ConcurrentHashMap<String, ConcurrentHashMap<Long, TblRasioTarif>> hRasioAndTarif = new ConcurrentHashMap<String, ConcurrentHashMap<Long, TblRasioTarif>>();
    private static ConcurrentHashMap<String, TblGolongan> hGolongan = new ConcurrentHashMap<String, TblGolongan>();
    private static ConcurrentHashMap<String, TblTipeJaminan> hTipeJaminan = new ConcurrentHashMap<String, TblTipeJaminan>();

    @Autowired
    private static TblGolonganDAO tblGolonganDAO;

    @Autowired
    private static TblRasioTarifDAO tblRasioTarifDAO;

    @Autowired
    private static TblTipeJaminanDAO tblTipeJaminanDAO;
    public static Integer allDataSize = 0;
    public static void load() {
        try {
            /*
             *@param LOADER TIPE JAMINAN
             *@return Tipe Jaminan Loader
             */
            ApplicationContext ctx = DemoApplication.getContext();
            tblTipeJaminanDAO  = ctx.getBean(TblTipeJaminanDAO.class);
            tblGolonganDAO = ctx.getBean(TblGolonganDAO.class);
            tblRasioTarifDAO = ctx.getBean(TblRasioTarifDAO.class);

            List<TblTipeJaminan> list = tblTipeJaminanDAO.findAll();
            ConcurrentHashMap<String, TblTipeJaminan> listTipeJaminan = null;
            String rubrik = "";
            for (TblTipeJaminan jaminan : list) {
                rubrik = jaminan.getRubrik();
                if (hTipejaminanByRubrik.get(rubrik) == null) {
                    listTipeJaminan = new ConcurrentHashMap<String, TblTipeJaminan>();
                } else {
                    listTipeJaminan = hTipejaminanByRubrik.get(rubrik);
                }
                listTipeJaminan.put(jaminan.getIdTipeJaminan(), jaminan);
                hTipejaminanByRubrik.put(rubrik, listTipeJaminan);
                hTipeJaminan.put(jaminan.getIdTipeJaminan(), jaminan);
            }
            LOGGER.info("Tipe Jaminan Size : {}",list.size());
            /*
             * Golongan Loader
             */
            List<TblGolongan> listGolongan = tblGolonganDAO.findAll();
            for (TblGolongan golongan : listGolongan) {
                hGolongan.put(golongan.getKodeGolongan(), golongan);
            }
            LOGGER.info("Golongan Size : {}",listGolongan.size());
            /*
             * Rasio Tarif Loader
             * @param kodeRasioTarif : KODE_BIAYA + GOLONGAN + PRODUK + RUBRIK
             */
            String kodeRasioTarif = "";
            ConcurrentHashMap<Long, TblRasioTarif> listRasio = null;
            List<TblRasioTarif> tarifs = tblRasioTarifDAO.findAll();
            for (TblRasioTarif tarif : tarifs) {
                kodeRasioTarif = tarif.getKodeBiaya() + (tarif.getKodeGolongan() == null ? "" : tarif.getKodeGolongan())
                        + tarif.getKodeProduct() + (tarif.getRubrik() == null ? "" : tarif.getRubrik() + (tarif.getTenor() == null ? "" : tarif.getTenor())
                        + (tarif.getTipeJaminan() == null ? "" : tarif.getTipeJaminan()) + (tarif.getJenisNasabah() == null ? "" : tarif.getJenisNasabah()));
                if (hRasioAndTarif.get(kodeRasioTarif) == null) {
                    listRasio = new ConcurrentHashMap<Long, TblRasioTarif>();
                } else {
                    listRasio = hRasioAndTarif.get(kodeRasioTarif);
                }
                listRasio.put(tarif.getIdRasioTarif(), tarif);
                hRasioAndTarif.put(kodeRasioTarif, listRasio);
            }
            LOGGER.info("Rasio Tarif Size : {}",tarifs.size());
            allDataSize = tarifs.size() + listGolongan.size() + list.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * @param key : kodeGolongan
     * @return
     */
    public static TblGolongan getGolongan(String key) {
        return hGolongan.get(key);
    }

    public static ConcurrentHashMap<String, TblGolongan> getListGolongan() {
        return hGolongan;
    }

    /*
     *@param kodeRasioTarif : KODE_BIAYA + GOLONGAN +PRODUK + RUBRIK + TENOR
     * @return
     */
    public static ConcurrentHashMap<Long, TblRasioTarif> getListRasioAndTarif(String kodeRasioTarif){
        return hRasioAndTarif.get(kodeRasioTarif);
    }

}
