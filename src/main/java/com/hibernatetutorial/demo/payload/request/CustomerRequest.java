package com.hibernatetutorial.demo.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.persistence.*;

import org.hibernate.validator.constraints.Range;
import java.util.Date;

public class CustomerRequest {

    @NotEmpty(message = "Name is Not Empty")
    private String name;

    @Email(message = "Format Email")
    private String email;
    @NotBlank(message="noKTP is Not Empty")
    @Range(min = 16, max = 16, message = "No KTP 16 digit")
    private Long noKTP;
    @NotEmpty(message = "Name is Not Empty")
    private String jenisKelamin;

    private boolean isAdult;

    private String tglLahir;

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

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

}
