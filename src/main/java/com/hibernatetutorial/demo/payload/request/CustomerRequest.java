package com.hibernatetutorial.demo.payload.request;

import javax.validation.constraints.*;
import javax.persistence.*;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.util.Date;

public class CustomerRequest {

    @NotEmpty(message = "Name is Not Empty")
    @Length(message = "Name min char 3", min = 3, max = 255)
    private String name;
    @Email(message = "Format Email")
    private String email;
    @NotNull(message = "noKTP is Not Null")
    private String noKTP;
    @NotEmpty(message = "Jenis Kelamin is Not Empty")
    private String jenisKelamin;
    @NotNull
    private boolean isAdult;

    private String tglLahir;

    private String namaAlamat;

    private Integer kodePos;

    private String negara;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
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
