package com.hibernatetutorial.demo.entity;

import com.hibernatetutorial.demo.entity.audit.AuditTrail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tbl_golongan")
public class TblGolongan extends AuditTrail implements Serializable {

    private String kodeGolongan;
    private String keterangan;
    private BigDecimal minimalUP;
    private BigDecimal maksimalUP;
    private BigDecimal pembulatanUP;
    private String jenisPembulatan;

    public TblGolongan() {
    }

    public TblGolongan(String kodeGolongan) {
        this.kodeGolongan = kodeGolongan;
    }

    public TblGolongan(String kodeGolongan, String keterangan, BigDecimal minimalUP, BigDecimal maksimalUP, BigDecimal pembulatanUP, String jenisPembulatan) {
        this.kodeGolongan = kodeGolongan;
        this.keterangan = keterangan;
        this.minimalUP = minimalUP;
        this.maksimalUP = maksimalUP;
        this.pembulatanUP = pembulatanUP;
        this.jenisPembulatan = jenisPembulatan;
    }

    @Id
    @Column(name = "kode_golongan", nullable = false, length = 2)
    public String getKodeGolongan() {
        return this.kodeGolongan;
    }

    public void setKodeGolongan(String kodeGolongan) {
        this.kodeGolongan = kodeGolongan;
    }

    @Column(name = "keterangan", length = 100)
    public String getKeterangan() {
        return this.keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Column(name = "minimal_up",precision =12, scale=2)
    public BigDecimal getMinimalUP() {
        return this.minimalUP;
    }

    public void setMinimalUP(BigDecimal minimalUP) {
        this.minimalUP = minimalUP;
    }

    @Column(name = "maksimal_up",precision = 12,scale=2)
    public BigDecimal getMaksimalUP() {
        return this.maksimalUP;
    }

    public void setMaksimalUP(BigDecimal maksimalUP) {
        this.maksimalUP = maksimalUP;
    }

    @Column(name = "pembulatan_up",precision=12,scale=2)
    public BigDecimal getPembulatanUP() {
        return this.pembulatanUP;
    }

    public void setPembulatanUP(BigDecimal pembulatanUP) {
        this.pembulatanUP = pembulatanUP;
    }

    @Column(name = "jenis_pembulatan", length = 1)
    public String getJenisPembulatan() {
        return jenisPembulatan;
    }

    public void setJenisPembulatan(String jenisPembulatan) {
        this.jenisPembulatan = jenisPembulatan;
    }
}
