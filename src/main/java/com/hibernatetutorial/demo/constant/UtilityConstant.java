package com.hibernatetutorial.demo.constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * UtilityConstant
 */
public class UtilityConstant {


    public enum GENDER {
        LAKI_LAKI("L", "LAKI-LAKI"),
        WANITA("P", "PEREMPUAN");

        private String code, description;

        GENDER(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

    }

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public enum INFO_SERVICE {
        START_SERVICE("****Start Service"),
        END_SERVICE("****End Service"),
        START_CONTROLLER("****Start Controller"),
        END_CONTOLLER("****End Controller");

        private String description;

        INFO_SERVICE(String description) {
            this.description = description;
        }

        public String getDescription(Class thisClass) {
            return description + " " + thisClass.getName() + " : " + thisClass.getEnclosingMethod().getName();
        }
    }

    public enum MESSAGE_VALIDATE {
        DATE_FORMAT("Invalid Date Format (dd-MM-yyyy)");

        private String description;

        MESSAGE_VALIDATE(String description) {
            this.description = description;
        }

        public String getDescription(String value) {
            return description + " `" + value + "`";
        }
    }


    public static Boolean isDatePattern(String date) {
        String datePatternFirst = "^[0-3]?[0-9]-(1[0-2]|0[1-9])-(?:[0-9]{2})?[0-9]{2}$";
        Pattern pattern = Pattern.compile(datePatternFirst);
        Matcher matcher = pattern.matcher(date);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean calculateAdult(Date birthAge) throws Exception {
        Calendar dob = Calendar.getInstance();
        dob.setTime(birthAge);
        if (getAge(dob) >= 18) {
            return true;
        }
        return false;
    }

    public static int getAge(Calendar dob) throws Exception {
        Calendar today = Calendar.getInstance();
        int curYear = today.get(Calendar.YEAR);
        int dobYear = dob.get(Calendar.YEAR);
        int curMonth = today.get(Calendar.MONTH);
        int dobMonth = dob.get(Calendar.MONTH);
        int curDay = today.get(Calendar.DAY_OF_MONTH);
        int dobDay = dob.get(Calendar.DAY_OF_MONTH);
        int age = curYear - dobYear;

        if (dobMonth > curMonth) {
            age--;
        } else if (dobMonth == curMonth) {
            if (dobDay > curDay) {
                age--;
            }
        }
        return age;
    }

    public static String generateIdOrder(Long idCustomer,String splitIdProduct){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONDAY);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Date now = new Date();
        String sequence =String.valueOf(System.currentTimeMillis()+idCustomer+splitIdProduct);
        return sequence;
    }

}