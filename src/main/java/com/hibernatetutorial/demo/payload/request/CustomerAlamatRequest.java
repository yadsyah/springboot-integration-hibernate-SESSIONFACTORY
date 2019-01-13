package com.hibernatetutorial.demo.payload.request;

public class CustomerAlamatRequest {

    private String namaAlamat;
    private Long customerId;
    private String negara;
    private Integer kodePos;

    public CustomerAlamatRequest() {
    }

    public CustomerAlamatRequest(String namaAlamat, Long customerId, String negara, Integer kodePos) {
        this.namaAlamat = namaAlamat;
        this.customerId = customerId;
        this.negara = negara;
        this.kodePos = kodePos;
    }

    public String getNamaAlamat() {
        return namaAlamat;
    }

    public void setNamaAlamat(String namaAlamat) {
        this.namaAlamat = namaAlamat;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getNegara() {
        return negara;
    }

    public void setNegara(String negara) {
        this.negara = negara;
    }

    public Integer getKodePos() {
        return kodePos;
    }

    public void setKodePos(Integer kodePos) {
        this.kodePos = kodePos;
    }
}
