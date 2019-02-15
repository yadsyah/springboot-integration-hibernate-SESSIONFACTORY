package com.hibernatetutorial.demo.dto;

public class LookupDto {
    private String KEY_;

    private String VALUE_;

    public LookupDto(String KEY_, String VALUE_) {
        this.KEY_ = KEY_;
        this.VALUE_ = VALUE_;
    }

    public LookupDto() {
    }

    public String getKEY_() {
        return KEY_;
    }

    public void setKEY_(String KEY_) {
        this.KEY_ = KEY_;
    }

    public String getVALUE_() {
        return VALUE_;
    }

    public void setVALUE_(String VALUE_) {
        this.VALUE_ = VALUE_;
    }
}
