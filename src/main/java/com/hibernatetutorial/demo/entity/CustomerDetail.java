package com.hibernatetutorial.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
public class CustomerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long customerDetailId;

    @Min(16)
    @Max(16)
    @Range(min = 16,max = 16, message="No KTP 16 digit")
    private Long noKTP;

    private String jenisKelamin;

    private boolean isAdult;

    private Date tglLahir;

    public CustomerDetail() {
    }

    public CustomerDetail(@Min(16) @Max(16) Long noKTP, String jenisKelamin, boolean isAdult, Date tglLahir) {
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

    public Long getNoKTP() {
        return noKTP;
    }

    public void setNoKTP(Long noKTP) {
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
}
