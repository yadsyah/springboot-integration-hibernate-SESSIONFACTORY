package com.hibernatetutorial.demo.payload.request.GTE;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;


@ApiModel(description = "All details about Simulasi GTE Request")
public class SimulasiGTERequest {


    @NotEmpty
    @ApiModelProperty(notes = "NoRek is not Empty!")
    private String noRek;
    @ApiModelProperty(notes = "Tenor GTE",example = "30/60/90/120")
    private Integer jangkaWaktu;
    @NotEmpty
    @ApiModelProperty(notes = "Jenis Input Transaksi is not Empty!",example = "UANG/GRAM")
    private String jenisInputTransaksi;
    @ApiModelProperty(notes = "if JenisInputTransaksi Uang is not Empty")
    private String uangNasabah;
    @ApiModelProperty(notes = "if JenisInputTransaksi Gram is not Empty")
    private String gramNasabah;

    public SimulasiGTERequest() {
    }

    public String getNoRek() {
        return noRek;
    }

    public void setNoRek(String noRek) {
        this.noRek = noRek;
    }

    public Integer getJangkaWaktu() {
        return jangkaWaktu;
    }

    public void setJangkaWaktu(Integer jangkaWaktu) {
        this.jangkaWaktu = jangkaWaktu;
    }

    public String getJenisInputTransaksi() {
        return jenisInputTransaksi;
    }

    public void setJenisInputTransaksi(String jenisInputTransaksi) {
        this.jenisInputTransaksi = jenisInputTransaksi;
    }

    public String getUangNasabah() {
        return uangNasabah;
    }

    public void setUangNasabah(String uangNasabah) {
        this.uangNasabah = uangNasabah;
    }

    public String getGramNasabah() {
        return gramNasabah;
    }

    public void setGramNasabah(String gramNasabah) {
        this.gramNasabah = gramNasabah;
    }
}
