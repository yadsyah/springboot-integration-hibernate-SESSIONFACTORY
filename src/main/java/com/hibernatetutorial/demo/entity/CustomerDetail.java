package com.hibernatetutorial.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hibernatetutorial.demo.constant.UtilityConstant;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "customerdetail")
public class CustomerDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long customerDetailId;

    private String noKTP;

    private String jenisKelamin;

    private boolean isAdult;

    private Date tglLahir;

    public CustomerDetail() {
    }

    public CustomerDetail(@Min(16) @Max(16) String noKTP, String jenisKelamin, boolean isAdult, Date tglLahir) {
        this.noKTP = noKTP;
        this.jenisKelamin = jenisKelamin;
        this.isAdult = isAdult;
        this.tglLahir = tglLahir;
    }

    public long getCustomerDetailId() {
        return customerDetailId;
    }

    public void setCustomerDetailId(long customerDetailId) {
        this.customerDetailId = customerDetailId;
    }

    public String getNoKTP() {
        return noKTP;
    }

    public void setNoKTP(String noKTP) {
        this.noKTP = noKTP;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append("{").append("customerDetailId").append(":").append(this.getCustomerDetailId()).append("}").toString();
    }

}
