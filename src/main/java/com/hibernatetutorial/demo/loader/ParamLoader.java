package com.hibernatetutorial.demo.loader;

import com.hibernatetutorial.demo.DemoApplication;
import com.hibernatetutorial.demo.dao.TblParamDAO;
import com.hibernatetutorial.demo.dto.LookupDto;
import com.hibernatetutorial.demo.entity.TblParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ParamLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParamLoader.class);
    private static ConcurrentHashMap<String, TblParam> hTblParam = new ConcurrentHashMap<String, TblParam>();
    private static ConcurrentHashMap<String, List<LookupDto>> hLookUpTblParam = new ConcurrentHashMap<String, List<LookupDto>>();
    @Autowired
    private static TblParamDAO tblParamDAO;

    public static Integer allDataSize = 0;

    public static void load() {
        hLookUpTblParam.clear();
        try {
            ApplicationContext ctx = DemoApplication.getContext();
            tblParamDAO = ctx.getBean(TblParamDAO.class);
            List<TblParam> list = tblParamDAO.findAll();
            List<LookupDto> lookupById = null;
            if (list.size() > 0 && list != null) {
                for (TblParam tblParam : list) {
                    if (hLookUpTblParam.get(tblParam.getKey()) == null) {
                        lookupById = new ArrayList<LookupDto>();
                    } else {
                        lookupById = hLookUpTblParam.get(tblParam.getKey());
                    }
                    lookupById.add(new LookupDto(tblParam.getKey(), tblParam.getValue()));
                    hLookUpTblParam.put(tblParam.getKey(), lookupById);
                    hTblParam.put(tblParam.getKey(), tblParam);
                }
                allDataSize = list.size();
            }
            LOGGER.info("Param Loader Size : {}", list != null ? list.size() : 0);
            /*
             * load all Tbl Param
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TblParam getTblParam(String key) {
        TblParam param = hTblParam.get(key);
        if (param == null) {
            param = tblParamDAO.findByKey(key);
            hTblParam.put(key, param);
            return param;
        }
        return null;
    }

    public static String getParam(String key) {
        TblParam param = hTblParam.get(key);
        if (param == null) {
            param = getTblParam(key);
            if (param != null) {
                synchronized (hTblParam) {
                    hTblParam.put(key, param);
                }
                return param.getValue();
            }
        } else {
            return param.getValue();
        }
        return null;
    }

    public static ConcurrentHashMap<String, List<LookupDto>> gethLookUpTblParam() {
        return hLookUpTblParam;
    }
}
