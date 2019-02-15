package com.hibernatetutorial.demo.entity;

import com.hibernatetutorial.demo.entity.audit.AuditTrail;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tbl_rekening_nasabah")
public class TblRekeningNasabah extends AuditTrail {

    private String norek;
    private String noCOA;
    private BigDecimal saldoAwal;
    private BigDecimal mutasiD;
    private BigDecimal mutasiK;
    private BigDecimal saldoAkhir;
    private String description;
    private String cif;
    private String norekPendamping;
    private String saldoNormal;
    private String branchCode;
    private Date lastTrxDate;
    private BigDecimal saldoBlokir;
    private BigDecimal saldoMinimal;

    public TblRekeningNasabah() {
    }

    public TblRekeningNasabah(String norek, String noCOA) {
        this.norek = norek;
        this.noCOA = noCOA;
    }

    public TblRekeningNasabah(String norek, String noCOA, BigDecimal saldoAwal, BigDecimal mutasiD, BigDecimal mutasiK, BigDecimal saldoAkhir, String description, String cif, String norekPendamping, String saldoNormal, String branchCode, Date lastTrxDate) {
        this.norek = norek;
        this.noCOA = noCOA;
        this.saldoAwal = saldoAwal;
        this.mutasiD = mutasiD;
        this.mutasiK = mutasiK;
        this.saldoAkhir = saldoAkhir;
        this.description = description;
        this.cif = cif;
        this.norekPendamping = norekPendamping;
        this.saldoNormal = saldoNormal;
        this.branchCode = branchCode;
        this.lastTrxDate = lastTrxDate;
    }

    @Id
    @Column(name = "norek", nullable = false, length = 16)
    public String getNorek() {
        return this.norek;
    }

    public void setNorek(String norek) {
        this.norek = norek;
    }

    @Column(name = "no_coa", nullable = false, length = 15)
    public String getNoCOA() {
        return this.noCOA;
    }

    public void setNoCOA(String noCOA) {
        this.noCOA = noCOA;
    }

    @Column(name = "saldo_awal", precision = 12, scale = 2)
    public BigDecimal getSaldoAwal() {
        return this.saldoAwal;
    }

    public void setSaldoAwal(BigDecimal saldoAwal) {
        this.saldoAwal = saldoAwal;
    }

    @Column(name = "mutasi_d", precision = 12, scale = 2)
    public BigDecimal getMutasiD() {
        return this.mutasiD;
    }

    public void setMutasiD(BigDecimal mutasiD) {
        this.mutasiD = mutasiD;
    }

    @Column(name = "mutasi_k", precision = 12, scale = 2)
    public BigDecimal getMutasiK() {
        return this.mutasiK;
    }

    public void setMutasiK(BigDecimal mutasiK) {
        this.mutasiK = mutasiK;
    }

    @Column(name = "saldo_akhir", precision = 12, scale = 2)
    public BigDecimal getSaldoAkhir() {
        return this.saldoAkhir;
    }

    public void setSaldoAkhir(BigDecimal saldoAkhir) {
        this.saldoAkhir = saldoAkhir;
    }

    @Column(name = "description", length = 200)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "cif", length = 10)
    public String getCif() {
        return this.cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    @Column(name = "norek_pendamping", length = 16)
    public String getNorekPendamping() {
        return this.norekPendamping;
    }

    public void setNorekPendamping(String norekPendamping) {
        this.norekPendamping = norekPendamping;
    }

    @Column(name = "saldo_normal", length = 1)
    public String getSaldoNormal() {
        return this.saldoNormal;
    }

    public void setSaldoNormal(String saldoNormal) {
        this.saldoNormal = saldoNormal;
    }

    @Column(name = "branch_code", length = 5)
    public String getBranchCode() {
        return this.branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "last_trx_date", length = 13)
    public Date getLastTrxDate() {
        return this.lastTrxDate;
    }

    public void setLastTrxDate(Date lastTrxDate) {
        this.lastTrxDate = lastTrxDate;
    }

    @Column(name = "saldo_blokir", precision = 12, scale = 2)
    public BigDecimal getSaldoBlokir() {
        return saldoBlokir;
    }

    public void setSaldoBlokir(BigDecimal saldoBlokir) {
        this.saldoBlokir = saldoBlokir;
    }

    @Column(name = "saldo_minimal", precision = 12, scale = 2)
    public BigDecimal getSaldoMinimal() {
        return saldoMinimal;
    }

    public void setSaldoMinimal(BigDecimal saldoMinimal) {
        this.saldoMinimal = saldoMinimal;
    }
}
