package com.hibernatetutorial.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CustomerAlamat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long customerAlamatId;
    @JsonIgnore
    private Long customerId;

    private String namaAlamat;

    private Integer kodePos;

    private String negara;

    public CustomerAlamat() {
    }

    public Long getCustomerAlamatId() {
        return customerAlamatId;
    }

    public void setCustomerAlamatId(Long customerAlamatId) {
        this.customerAlamatId = customerAlamatId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getNamaAlamat() {
        return namaAlamat;
    }

    public void setNamaAlamat(String namaAlamat) {
        this.namaAlamat = namaAlamat;
    }

    public Integer getKodePos() {
        return kodePos;
    }

    public void setKodePos(Integer kodePos) {
        this.kodePos = kodePos;
    }

    public String getNegara() {
        return negara;
    }

    public void setNegara(String negara) {
        this.negara = negara;
    }
}
