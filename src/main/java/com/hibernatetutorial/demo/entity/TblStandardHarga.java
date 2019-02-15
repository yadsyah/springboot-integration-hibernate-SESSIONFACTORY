package com.hibernatetutorial.demo.entity;

import com.hibernatetutorial.demo.entity.audit.AuditTrail;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tbl_standard_harga")
public class TblStandardHarga extends AuditTrail {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_berlaku")
    private Date tglBerlaku;

    private String description;

    @Column(name = "harga_emas", precision = 12, scale = 2)
    private BigDecimal hargaEmas;

    @Column(name = "harga_perak", precision = 12, scale = 2)
    private BigDecimal hargaPerak;

    @Column(name = "harga_berlian", precision = 12, scale = 2)
    private BigDecimal hargaBerlian;

    @Column(name = "harga_mutiara", precision = 12, scale = 2)
    private BigDecimal hargaMutiara;

    @Column(name = "harga_lantakan_emas", precision = 12, scale = 2)
    private BigDecimal hargaLantakanEmas;

    @Column(name = "harga_berlian_2", precision = 12, scale = 2)
    private BigDecimal hargaBerlian2;

    @Column(name = "flag_guk", length = 1)
    private String flagGUK;

    public TblStandardHarga() {
    }

    public TblStandardHarga(Long id, Date tglBerlaku, String description, BigDecimal hargaEmas, BigDecimal hargaPerak, BigDecimal hargaBerlian, BigDecimal hargaMutiara, BigDecimal hargaLantakanEmas, BigDecimal hargaBerlian2, String flagGUK) {
        this.id = id;
        this.tglBerlaku = tglBerlaku;
        this.description = description;
        this.hargaEmas = hargaEmas;
        this.hargaPerak = hargaPerak;
        this.hargaBerlian = hargaBerlian;
        this.hargaMutiara = hargaMutiara;
        this.hargaLantakanEmas = hargaLantakanEmas;
        this.hargaBerlian2 = hargaBerlian2;
        this.flagGUK = flagGUK;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTglBerlaku() {
        return tglBerlaku;
    }

    public void setTglBerlaku(Date tglBerlaku) {
        this.tglBerlaku = tglBerlaku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getHargaEmas() {
        return hargaEmas;
    }

    public void setHargaEmas(BigDecimal hargaEmas) {
        this.hargaEmas = hargaEmas;
    }

    public BigDecimal getHargaPerak() {
        return hargaPerak;
    }

    public void setHargaPerak(BigDecimal hargaPerak) {
        this.hargaPerak = hargaPerak;
    }

    public BigDecimal getHargaBerlian() {
        return hargaBerlian;
    }

    public void setHargaBerlian(BigDecimal hargaBerlian) {
        this.hargaBerlian = hargaBerlian;
    }

    public BigDecimal getHargaMutiara() {
        return hargaMutiara;
    }

    public void setHargaMutiara(BigDecimal hargaMutiara) {
        this.hargaMutiara = hargaMutiara;
    }

    public BigDecimal getHargaLantakanEmas() {
        return hargaLantakanEmas;
    }

    public void setHargaLantakanEmas(BigDecimal hargaLantakanEmas) {
        this.hargaLantakanEmas = hargaLantakanEmas;
    }

    public BigDecimal getHargaBerlian2() {
        return hargaBerlian2;
    }

    public void setHargaBerlian2(BigDecimal hargaBerlian2) {
        this.hargaBerlian2 = hargaBerlian2;
    }

    public String getFlagGUK() {
        return flagGUK;
    }

    public void setFlagGUK(String flagGUK) {
        this.flagGUK = flagGUK;
    }
}
