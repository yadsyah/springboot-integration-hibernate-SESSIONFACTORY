package com.hibernatetutorial.demo.entity;

import com.hibernatetutorial.demo.entity.audit.AuditTrail;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tbl_rasio_tarif")
public class TblRasioTarif extends AuditTrail implements Serializable {
    private long idRasioTarif;
    private String kodeProduct;
    private String kodeBiaya;
    private String kodeGolongan;
    private String rubrik;
    private Date tglBerlaku;
    private double tarifOrRasio;
    private String noRek;
    private BigDecimal pembulatanTarif;
    private String keteranganNo;
    private Double minRate;
    private Integer minDays;
    private String tenor;
    private Double minPersentUp;

    /**
     * null : tidak ada
     * 0 : Nasabah Baru
     * 1 : Nasabah Lama
     *
     */
    private String jenisNasabah;
    private String tipeJaminan;

    public TblRasioTarif() {
    }

    public TblRasioTarif(long idRasioTarif, Date tglBerlaku) {
        this.idRasioTarif = idRasioTarif;
        this.tglBerlaku = tglBerlaku;
    }

    public TblRasioTarif(long idRasioTarif, String kodeProduct, String kodeBiaya, String kodeGolongan, String rubrik, Date tglBerlaku, double tarifOrRasio, String noRek, BigDecimal pembulatanTarif) {
        this.idRasioTarif = idRasioTarif;
        this.kodeProduct = kodeProduct;
        this.kodeBiaya = kodeBiaya;
        this.kodeGolongan = kodeGolongan;
        this.rubrik = rubrik;
        this.tglBerlaku = tglBerlaku;
        this.tarifOrRasio = tarifOrRasio;
        this.noRek = noRek;
        this.pembulatanTarif = pembulatanTarif;
    }

    @Id
    @Column(name = "id_rasio_tarif", nullable = false)
    public long getIdRasioTarif() {
        return this.idRasioTarif;
    }

    public void setIdRasioTarif(long idRasioTarif) {
        this.idRasioTarif = idRasioTarif;
    }

    @Column(name = "kode_product", length = 2)
    public String getKodeProduct() {
        return this.kodeProduct;
    }

    public void setKodeProduct(String kodeProduct) {
        this.kodeProduct = kodeProduct;
    }

    @Column(name = "kode_biaya", length = 16)
    public String getKodeBiaya() {
        return this.kodeBiaya;
    }

    public void setKodeBiaya(String kodeBiaya) {
        this.kodeBiaya = kodeBiaya;
    }

    @Column(name = "kode_golongan", length = 10)
    public String getKodeGolongan() {
        return this.kodeGolongan;
    }

    public void setKodeGolongan(String kodeGolongan) {
        this.kodeGolongan = kodeGolongan;
    }

    @Column(name = "kode_rubrik", length = 2)
    public String getRubrik() {
        return this.rubrik;
    }

    public void setRubrik(String rubrik) {
        this.rubrik = rubrik;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_berlaku", nullable = false, length = 13)
    public Date getTglBerlaku() {
        return this.tglBerlaku;
    }

    public void setTglBerlaku(Date tglBerlaku) {
        this.tglBerlaku = tglBerlaku;
    }

    @Column(name = "tarif_rasio", precision = 17, scale = 17)
    public double getTarifOrRasio() {
        return this.tarifOrRasio;
    }

    public void setTarifOrRasio(double tarifOrRasio) {
        this.tarifOrRasio = tarifOrRasio;
    }

    @Column(name = "no_rek", length = 16)
    public String getNoRek() {
        return this.noRek;
    }

    public void setNoRek(String noRek) {
        this.noRek = noRek;
    }

    @Column(name = "pembulatan_tarif",precision=12,scale=2)
    public BigDecimal getPembulatanTarif() {
        return this.pembulatanTarif;
    }

    public void setPembulatanTarif(BigDecimal pembulatanTarif) {
        this.pembulatanTarif = pembulatanTarif;
    }

    @Column(name = "keterangan_no", length = 20)
    public String getKeteranganNo() {
        return this.keteranganNo;
    }

    public void setKeteranganNo(String keteranganNo) {
        this.keteranganNo = keteranganNo;
    }

    @Column(name = "min_rate", precision = 12, scale = 2)
    public Double getMinRate() {
        return minRate;
    }

    public void setMinRate(Double minRate) {
        this.minRate = minRate;
    }

    @Column(name = "min_days")
    public Integer getMinDays() {
        return minDays;
    }

    public void setMinDays(Integer minDays) {
        this.minDays = minDays;
    }

    @Column(name = "tenor", length = 3)
    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    @Column(name = "min_persent_up", precision = 12, scale = 2)
    public Double getMinPersentUp() {
        return minPersentUp;
    }

    public void setMinPersentUp(Double minPersentUp) {
        this.minPersentUp = minPersentUp;
    }

    @Column(name = "jenis_nasabah", length = 1)
    public String getJenisNasabah() {
        return jenisNasabah;
    }

    public void setJenisNasabah(String jenisNasabah) {
        this.jenisNasabah = jenisNasabah;
    }

    @Column(name = "tipe_jaminan", length = 4)
    public String getTipeJaminan() {
        return tipeJaminan;
    }

    public void setTipeJaminan(String tipeJaminan) {
        this.tipeJaminan = tipeJaminan;
    }


}
