package com.hibernatetutorial.demo.constant;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * UtilityConstant
 */
public class UtilityConstant {


    public enum GENDER{
        LAKI_LAKI("L","LAKI-LAKI"),
        WANITA("P","PEREMPUAN");

        private String code, description;

        GENDER(String code,String description){
            this.code = code;
            this.description = description;
        }

        public String getCode(){
            return code;
        }
        public String getDescription(){
            return description;
        }

    }

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public enum INFO_SERVICE{
        START_SERVICE("****Start Service"),
        END_SERVICE("****End Service"),
        START_CONTROLLER("****Start Controller"),
        END_CONTOLLER("****End Controller");

        private String description;

        INFO_SERVICE(String description){
            this.description = description;
        }

        public String getDescription(Class thisClass){
            return String.format("%s : %s",description);
        }
    }

    public enum MESSAGE_VALIDATE {
        DATE_FORMAT("Invalid Date Format (dd-MM-yyyy)");

        private String description;

        MESSAGE_VALIDATE(String description) {
            this.description = description;
        }

        public String getDescription(String value) {
            return description+ " `"+value+"`";
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
    
}