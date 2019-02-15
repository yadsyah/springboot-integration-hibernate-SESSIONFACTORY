package com.hibernatetutorial.demo.utility;

import com.hibernatetutorial.demo.loader.ParamLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static long bussinedDateL = -1L;

    public static Date getBussinesDate() {
        if (bussinedDateL == -1L) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Date bussinesDate = sdf.parse(ParamLoader.getParam("BUSINESS.DATE"));
                bussinedDateL = bussinesDate.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return new Date(bussinedDateL);
    }
}
