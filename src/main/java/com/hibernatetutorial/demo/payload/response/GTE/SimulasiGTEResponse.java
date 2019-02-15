package com.hibernatetutorial.demo.payload.response.GTE;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimulasiGTEResponse {


    private String noRek;
    private Integer jangkaWaktu;
    private String jenisInputTransaksi;
    private String uangNasabah;
    private String gramNasabah;

    private String cif;
    private String namaNasabah;
    private String noBukuTabungan;

    private String hargaSTL;
    private Double gramJual;
    private String nominalJual;
    private Double saldoNominal;
    private Double saldoTransaksi;
    private Double saldoGram;
    private Double saldoBlokir;
    private Double nominalJualUl;
    public SimulasiGTEResponse() {
    }

    public Double getNominalJualUl() {
        return nominalJualUl;
    }

    public void setNominalJualUl(Double nominalJualUl) {
        this.nominalJualUl = nominalJualUl;
    }

    public String getHargaSTL() {
        return hargaSTL;
    }

    public void setHargaSTL(String hargaSTL) {
        this.hargaSTL = hargaSTL;
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

    public String getJenisInputTransaksi() {
        return jenisInputTransaksi;
    }

    public void setJenisInputTransaksi(String jenisInputTransaksi) {
        this.jenisInputTransaksi = jenisInputTransaksi;
    }

    public Integer getJangkaWaktu() {
        return jangkaWaktu;
    }

    public void setJangkaWaktu(Integer jangkaWaktu) {
        this.jangkaWaktu = jangkaWaktu;
    }

    public String getNoRek() {
        return noRek;
    }

    public void setNoRek(String noRek) {
        this.noRek = noRek;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }

    public String getNoBukuTabungan() {
        return noBukuTabungan;
    }

    public void setNoBukuTabungan(String noBukuTabungan) {
        this.noBukuTabungan = noBukuTabungan;
    }

    public Double getGramJual() {
        return gramJual;
    }

    public void setGramJual(Double gramJual) {
        this.gramJual = gramJual;
    }

    public String getNominalJual() {
        return nominalJual;
    }

    public void setNominalJual(String nominalJual) {
        this.nominalJual = nominalJual;
    }

    public Double getSaldoNominal() {
        return saldoNominal;
    }

    public void setSaldoNominal(Double saldoNominal) {
        this.saldoNominal = saldoNominal;
    }

    public Double getSaldoTransaksi() {
        return saldoTransaksi;
    }

    public void setSaldoTransaksi(Double saldoTransaksi) {
        this.saldoTransaksi = saldoTransaksi;
    }

    public Double getSaldoGram() {
        return saldoGram;
    }

    public void setSaldoGram(Double saldoGram) {
        this.saldoGram = saldoGram;
    }

    public Double getSaldoBlokir() {
        return saldoBlokir;
    }

    public void setSaldoBlokir(Double saldoBlokir) {
        this.saldoBlokir = saldoBlokir;
    }

    @Override
    public String toString() {
        return "{" +
                "noRek:'" + noRek + '\'' +
                ", jangkaWaktu:" + jangkaWaktu +
                ", jenisInputTransaksi:'" + jenisInputTransaksi + '\'' +
                ", uangNasabah:'" + uangNasabah + '\'' +
                ", gramNasabah:'" + gramNasabah + '\'' +
                ", cif:'" + cif + '\'' +
                ", namaNasabah:'" + namaNasabah + '\'' +
                ", noBukuTabungan:'" + noBukuTabungan + '\'' +
                ", hargaSTL:'" + hargaSTL + '\'' +
                ", gramJual:" + gramJual +
                ", nominalJual:'" + nominalJual + '\'' +
                ", saldoNominal:" + saldoNominal +
                ", saldoTransaksi:" + saldoTransaksi +
                ", saldoGram:" + saldoGram +
                ", saldoBlokir:" + saldoBlokir +
                ", nominalJualUl:" + nominalJualUl +
                '}';
    }
}
