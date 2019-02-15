package com.hibernatetutorial.demo.entity;

import com.hibernatetutorial.demo.entity.audit.AuditTrail;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tbl_rekening_emas")
public class TblRekeningEmas extends AuditTrail {
    private String norek;
    private BigDecimal saldoAwal;
    private BigDecimal mutasiD;
    private BigDecimal mutasiK;
    private BigDecimal saldoAkhir;
    private String description;
    private String branchCode;
    private Date lastTrxDate;
    private String saldoNormal;
    private BigDecimal saldoBlokir;

    public TblRekeningEmas() {
    }

    public TblRekeningEmas(String norek) {
        this.norek = norek;
    }

    @Id
    @Column(name = "norek", nullable = false, length = 16)
    public String getNorek() {
        return this.norek;
    }

    public void setNorek(String norek) {
        this.norek = norek;
    }

    @Column(name = "saldo_awal", precision = 12,scale=4)
    public BigDecimal getSaldoAwal() {
        return this.saldoAwal;
    }

    public void setSaldoAwal(BigDecimal saldoAwal) {
        this.saldoAwal = saldoAwal;
    }

    @Column(name = "mutasi_d", precision = 12,scale=4)
    public BigDecimal getMutasiD() {
        return this.mutasiD;
    }

    public void setMutasiD(BigDecimal mutasiD) {
        this.mutasiD = mutasiD;
    }

    @Column(name = "mutasi_k", precision = 12,scale=4)
    public BigDecimal getMutasiK() {
        return this.mutasiK;
    }

    public void setMutasiK(BigDecimal mutasiK) {
        this.mutasiK = mutasiK;
    }

    @Column(name = "saldo_akhir", precision = 12,scale=4)
    public BigDecimal getSaldoAkhir() {
        return this.saldoAkhir;
    }

    public void setSaldoAkhir(BigDecimal saldoAkhir) {
        this.saldoAkhir = saldoAkhir;
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

    @Column(name = "description", length = 200)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "saldo_normal", length = 1)
    public String getSaldoNormal() {
        return this.saldoNormal;
    }

    public void setSaldoNormal(String saldoNormal) {
        this.saldoNormal = saldoNormal;
    }

    @Column(name = "saldo_blokir", precision = 12,scale=4)
    public BigDecimal getSaldoBlokir() {
        return saldoBlokir;
    }

    public void setSaldoBlokir(BigDecimal saldoBlokir) {
        this.saldoBlokir = saldoBlokir;
    }
}
