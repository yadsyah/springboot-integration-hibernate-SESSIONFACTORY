package com.hibernatetutorial.demo.entity;


import com.hibernatetutorial.demo.entity.audit.AuditTrail;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tbl_tabungan")
public class TblTabungan extends AuditTrail {

    private String norek;
    private String productCode;
    private String cif;
    private String custName;
    private String noKartu;
    private String statusRek; // 0=Closed; 1=Aktif; 2=Blokir All; 3=Blokir Post No Debet; 4=Blokir Post No Credit; 5=Dormant
    private String documentChecklist;
    private String referralCode;
    private String defaultPin;
    private String pin;
    private int failPinCount;
    private Date tglBuka;
    private String detailBlokirSaldo; // tgl,amount,keterangan;tgl,amount,keterangan;
    private Date tglBlokirRek;
    private String ketBlokirRek;
    private Date tglTutup;
    private String ccy;
    private String branchCode;
    private String referralName;
    private String mobileBranchCode;
    private String noBuku;
    private BigDecimal saldoBuku;
    private Long jurnalId;
    private Integer noCetak;
    private Double saldoEmasBuku;
    private String isCetakHeader;

    private String idNegara;

    /**
     * flag
     *
     * 1 : Sudah Bayar
     * 0 / NULL : Belum Bayar
     */
    private String flagCetakBuku;
    private String flagDendaOrder;
    private String flagRekKoran;
    private String branchCodeBlokir;

    /**
     * Proses Amortisasi
     */
    // Nilai ini akan di Set Saat Pengajuan, Kemudian tiap bulan dikurang sebesar amortisasi
    private BigDecimal amountBiaya;

    // Tanggal Bayar dan Tanggal Jatuh Tempo
    private Date tglLastBayarBiaya;
    private Date tglJatuhTempoBiaya;

    // di update Saat pembebanan Terakhir
    private Date tglLastAmortisasi;

    // Tgl ini si set saat Pengajuan Tabungan dan Saat pembebanan amortisasi
    private Date tglNextAmortisasi;


    /**
     * Nasabah Tambahan (QQ)
     */
    private String isRekeningTambahan;
    private String custNameTambahan;
    private Date tglLahirCustTambahan;
    private String rekeningInduk;

    /**
     * Aget
     */
    private String idAgent;
    private String clientId;

    public TblTabungan() {
    }

    public TblTabungan(String cif, String productCode) {
        super();
        this.productCode = productCode;
        this.cif = cif;
    }

    @Column(name = "no_buku"/*, nullable = false*/, length = 16)
    public String getNoBuku() {
        return noBuku;
    }

    public void setNoBuku(String noBuku) {
        this.noBuku = noBuku;
    }

    @Id
    @Column(name = "norek", nullable = false, length = 16)
    public String getNorek() {
        return norek;
    }
    public void setNorek(String norek) {
        this.norek = norek;
    }

    @Column(name = "product_code", nullable = false, length = 2)
    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Column(name = "cif", nullable = false, length = 16)
    public String getCif() {
        return cif;
    }
    public void setCif(String cif) {
        this.cif = cif;
    }

    @Column(name = "customer_name", length = 100)
    public String getCustName() {
        return custName;
    }
    public void setCustName(String custName) {
        this.custName = custName;
    }

    @Column(name = "no_kartu", length = 18)
    public String getNoKartu() {
        return noKartu;
    }
    public void setNoKartu(String noKartu) {
        this.noKartu = noKartu;
    }

    @Column(name = "status_rek", length = 1, nullable=false)
    public String getStatusRek() {
        return statusRek;
    }
    public void setStatusRek(String statusRek) {
        this.statusRek = statusRek;
    }

    @Column(name = "doc_checklist", length = 100)
    public String getDocumentChecklist() {
        return documentChecklist;
    }
    public void setDocumentChecklist(String documentChecklist) {
        this.documentChecklist = documentChecklist;
    }

    @Column(name = "referral_code", length = 10)
    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    @Column(name = "default_pin", length = 32)
    public String getDefaultPin() {
        return defaultPin;
    }

    public void setDefaultPin(String defaultPin) {
        this.defaultPin = defaultPin;
    }

    @Column(name = "pin", length = 32)
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Column(name = "fail_pin_count")
    public int getFailPinCount() {
        return failPinCount;
    }

    public void setFailPinCount(int failPinCount) {
        this.failPinCount = failPinCount;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_buka", length = 13)
    public Date getTglBuka() {
        return tglBuka;
    }
    public void setTglBuka(Date tglBuka) {
        this.tglBuka = tglBuka;
    }

    @Column(name = "det_blokir_saldo", length = 200)
    public String getDetailBlokirSaldo() {
        return detailBlokirSaldo;
    }

    public void setDetailBlokirSaldo(String detailBlokirSaldo) {
        this.detailBlokirSaldo = detailBlokirSaldo;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_blokir_rek", length = 13)
    public Date getTglBlokirRek() {
        return tglBlokirRek;
    }

    public void setTglBlokirRek(Date tglBlokirRek) {
        this.tglBlokirRek = tglBlokirRek;
    }

    @Column(name = "ket_blokir_rek", length = 100)
    public String getKetBlokirRek() {
        return ketBlokirRek;
    }

    public void setKetBlokirRek(String ketBlokirRek) {
        this.ketBlokirRek = ketBlokirRek;
    }


    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_tutup", length = 13)
    public Date getTglTutup() {
        return tglTutup;
    }

    public void setTglTutup(Date tglTutup) {
        this.tglTutup = tglTutup;
    }

    @Column(name = "ccy", length = 3)
    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    @Column(name = "branch_code", nullable = false, length=5)
    public String getBranchCode() {
        return branchCode;
    }
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @Column(name = "referral_name", length = 60)
    public String getReferralName() {
        return referralName;
    }

    public void setReferralName(String referralName) {
        this.referralName = referralName;
    }

    @Column(name = "branch_mobile_id", length = 10)
    public String getMobileBranchCode() {
        return mobileBranchCode;
    }

    public void setMobileBranchCode(String mobileBranchCode) {
        this.mobileBranchCode = mobileBranchCode;
    }

    @Column (name = "saldo_buku", precision = 20, scale = 2)
    public BigDecimal getSaldoBuku() {
        return saldoBuku;
    }

    public void setSaldoBuku(BigDecimal saldoBuku) {
        this.saldoBuku = saldoBuku;
    }

    @Column(name = "jurnal_id", length = 16)
    public Long getJurnalId() {
        return jurnalId;
    }

    public void setJurnalId(Long jurnalId) {
        this.jurnalId = jurnalId;
    }

    @Column(name = "no_cetak", length = 3)
    public Integer getNoCetak() {
        return noCetak;
    }

    public void setNoCetak(Integer noCetak) {
        this.noCetak = noCetak;
    }

    @Column(name = "saldo_emas_buku")
    public Double getSaldoEmasBuku() {
        return saldoEmasBuku;
    }

    public void setSaldoEmasBuku(Double saldoEmasBuku) {
        this.saldoEmasBuku = saldoEmasBuku;
    }

    @Column(name = "is_cetak", length = 1)
    public String getIsCetakHeader() {
        return isCetakHeader;
    }

    public void setIsCetakHeader(String isCetakHeader) {
        this.isCetakHeader = isCetakHeader;
    }

    @Column (name = "amount_biaya", precision = 20, scale = 2)
    public BigDecimal getAmountBiaya() {
        return amountBiaya;
    }

    public void setAmountBiaya(BigDecimal amountBiaya) {
        this.amountBiaya = amountBiaya;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_last_amortisasi", length = 13)
    public Date getTglLastAmortisasi() {
        return tglLastAmortisasi;
    }

    public void setTglLastAmortisasi(Date tglLastAmortisasi) {
        this.tglLastAmortisasi = tglLastAmortisasi;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_next_amortisasi", length = 13)
    public Date getTglNextAmortisasi() {
        return tglNextAmortisasi;
    }

    public void setTglNextAmortisasi(Date tglNextAmortisasi) {
        this.tglNextAmortisasi = tglNextAmortisasi;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_last_bayar_biaya", length = 13)
    public Date getTglLastBayarBiaya() {
        return tglLastBayarBiaya;
    }

    public void setTglLastBayarBiaya(Date tglLastBayarBiaya) {
        this.tglLastBayarBiaya = tglLastBayarBiaya;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_jatuh_tempo_biaya", length = 13)
    public Date getTglJatuhTempoBiaya() {
        return tglJatuhTempoBiaya;
    }

    public void setTglJatuhTempoBiaya(Date tglJatuhTempoBiaya) {
        this.tglJatuhTempoBiaya = tglJatuhTempoBiaya;
    }

    @Column(name = "flag_cetak_buku", length = 1)
    public String getFlagCetakBuku() {
        return flagCetakBuku;
    }

    public void setFlagCetakBuku(String flagCetakBuku) {
        this.flagCetakBuku = flagCetakBuku;
    }

    @Column(name = "flag_denda_order", length = 1)
    public String getFlagDendaOrder() {
        return flagDendaOrder;
    }

    public void setFlagDendaOrder(String flagDendaOrder) {
        this.flagDendaOrder = flagDendaOrder;
    }

    @Column(name = "flag_rek_koran", length = 1)
    public String getFlagRekKoran() {
        return flagRekKoran;
    }

    public void setFlagRekKoran(String flagRekKoran) {
        this.flagRekKoran = flagRekKoran;
    }

    @Column(name = "branch_code_blokir", length = 5)
    public String getBranchCodeBlokir() {
        return branchCodeBlokir;
    }

    public void setBranchCodeBlokir(String branchCodeBlokir) {
        this.branchCodeBlokir = branchCodeBlokir;
    }

    @Column(name = "is_rekening_qq", length = 1)
    public String getIsRekeningTambahan() {
        return isRekeningTambahan;
    }

    public void setIsRekeningTambahan(String isRekeningTambahan) {
        this.isRekeningTambahan = isRekeningTambahan;
    }

    @Column(name = "customer_name_qq", length = 100)
    public String getCustNameTambahan() {
        return custNameTambahan;
    }

    public void setCustNameTambahan(String custNameTambahan) {
        this.custNameTambahan = custNameTambahan;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_lahir_qq", length = 13)
    public Date getTglLahirCustTambahan() {
        return tglLahirCustTambahan;
    }

    public void setTglLahirCustTambahan(Date tglLahirCustTambahan) {
        this.tglLahirCustTambahan = tglLahirCustTambahan;
    }

    @Column(name = "id_negara", length = 15)
    public String getIdNegara() {
        return idNegara;
    }

    public void setIdNegara(String idNegara) {
        this.idNegara = idNegara;
    }

    @Column(name = "rekening_induk", length = 16)
    public String getRekeningInduk() {
        return rekeningInduk;
    }

    public void setRekeningInduk(String rekeningInduk) {
        this.rekeningInduk = rekeningInduk;
    }

    @Column(name = "id_agent", length = 12)
    public String getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(String idAgent) {
        this.idAgent = idAgent;
    }

    @Column (name = "client_id", length = 10)
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
