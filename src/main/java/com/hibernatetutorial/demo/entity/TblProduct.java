package com.hibernatetutorial.demo.entity;

import com.hibernatetutorial.demo.entity.audit.AuditTrail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tbl_product")
public class TblProduct extends AuditTrail implements Serializable {

    private String kodeProduct;
    private String norekIA;
    private String namaProduct;
    private String isActive;
    private String basis;
    private String tipeNasabah;
    private String prosesKredit; // Proses Survey
    private BigDecimal pinjamanMin;
    private BigDecimal pinjamanMax;
    private String sewaModalPeriode;
    private double sewaModalPercent;
    private double sewaModalPercentAnuitas;
    private double rateDenda;
    private long dayRateDenda;
    private String jankaWaktuMin;
    private String jangkaWaktuMax;
    private String prosesPembayaran;
    private String tipeBunga;
    private String documentChecklist;
    private String scoringScheme;
    private String kodeBiayas;
    private String keteranganNo;
    private String statusIjp;
    private String prosesScoring;
    private String prosesLPBJ;
    private String statusBJ; // Membutuhkan BJ apa tidak
    private String statusMulia; // Untuk Produk Mulai atau bukan


    /**
     * IA MIKRO
     */
    /** Accrue **/
    private String norekPendapatanSMYMHD, norekPendapatanSM, norekPendapatanSMAccrue;

    /** Denda EOM **/
    private String norekPendapatanDenda, norekDendaTunggakanPokokYMHD,norekDendaTunggakanSMYMHD;

    /** Tunggakan **/
    private String norekTunggakanPokok;

    /** Tagihan Kontigen **/
    private String norekTagKonSMNPL,norekTagKonSMNPLKontra,norekTagKonDendaTunggakanPokok,norekTagKonDendaTunggakanPokokKontra,norekTagKonDendaTunggakanSM,
            norekTagKonDendaTunggakanSMKontra;

    /**Perusahaan Asuransi**/
    //private String perusahaanAsuransi;
    private String norekHutangSubrogasi, norekFeeSubrogasi, norekPendapatanSubrogasi, norekHutangKlaimAsuransi, norekBiayaPenyisihanPYD, norekMemoDebitTerimaHK, norekMemoKreditTerimaHK,norekMemoDebitPinjamanHK,norekMemoKreditPinjamanHK;
    /**
     * IA GADAI
     */
    private String norekKPYD, norekPenyisihanKPYD, norekKTGR;

    /**  Laba / Rugi  */
    private String norekPendapatanAYD, norekKerugianAYD,norekMutasiBarang;
    private String norekBeaLelangPenjual, norekBeaLelangPembeli, norekUkel;

    /** Bea Penjual/ Pembeli */
    private double beaPenjual;
    private double beaPembeli;

    /**
     * separated by comma
     */
    private String tipeJaminanDiterima;
    private String tenorDiterima;

    /**
     * temporary field, not persisted
     */
//	private List<String> listOfTipeJaminan=new ArrayList<String>();
//	private List<String> listOfKodeBiaya=new ArrayList<String>();

    public TblProduct() {
    }

    @Id
    @Column(name = "kode_product", nullable = false, length = 2)
    public String getKodeProduct() {
        return this.kodeProduct;
    }

    public void setKodeProduct(String kodeProduct) {
        this.kodeProduct = kodeProduct;
    }

    @Column(name = "no_coa_ia", nullable = false, length = 5)
    public String getNorekIA() {
        return this.norekIA;
    }

    public void setNorekIA(String norekIA) {
        this.norekIA = norekIA;
    }

    @Column(name = "nama_product", nullable = false, length = 100)
    public String getNamaProduct() {
        return this.namaProduct;
    }

    public void setNamaProduct(String namaProduct) {
        this.namaProduct = namaProduct;
    }

    @Column(name = "is_active", nullable = false, length = 1)
    public String getIsActive() {
        return this.isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    /**
     * '1'=>gadai<br/>
     * '2'=>fidusia
     * @param getBasis
     * @return
     */
    @Column(name = "basis", nullable = false, length = 1)
    public String getBasis() {
        return this.basis;
    }

    /**
     * '1'=>gadai<br/>
     * '2'=>fidusia
     * @param setBasis
     * @return
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    @Column(name = "tipe_nasabah", nullable = false, length = 1)
    public String getTipeNasabah() {
        return this.tipeNasabah;
    }

    public void setTipeNasabah(String tipeNasabah) {
        this.tipeNasabah = tipeNasabah;
    }

    @Column(name = "proses_kredit", nullable = false, length = 1)
    public String getProsesKredit() {
        return this.prosesKredit;
    }

    public void setProsesKredit(String prosesKredit) {
        this.prosesKredit = prosesKredit;
    }

    @Column(name = "pinjaman_min", nullable = false, precision = 12,scale=2)
    public BigDecimal getPinjamanMin() {
        return this.pinjamanMin;
    }

    public void setPinjamanMin(BigDecimal pinjamanMin) {
        this.pinjamanMin = pinjamanMin;
    }

    @Column(name = "pinjaman_max", nullable = false, precision = 12,scale=2)
    public BigDecimal getPinjamanMax() {
        return this.pinjamanMax;
    }

    public void setPinjamanMax(BigDecimal pinjamanMax) {
        this.pinjamanMax = pinjamanMax;
    }

    @Column(name = "sewa_modal_periode", length = 4)
    public String getSewaModalPeriode() {
        return this.sewaModalPeriode;
    }

    public void setSewaModalPeriode(String sewaModalPeriode) {
        this.sewaModalPeriode = sewaModalPeriode;
    }

    @Column(name = "sewa_modal_percent", nullable = false, precision = 17, scale = 17)
    public double getSewaModalPercent() {
        return this.sewaModalPercent;
    }

    public void setSewaModalPercentAnuitas(double sewaModalPercentAnuitas) {
        this.sewaModalPercentAnuitas = sewaModalPercentAnuitas;
    }

    @Column(name = "sewa_modal_percent_anuitas", nullable = false, precision = 17, scale = 17)
    public double getSewaModalPercentAnuitas() {
        return this.sewaModalPercentAnuitas;
    }

    public void setSewaModalPercent(double sewaModalPercent) {
        this.sewaModalPercent = sewaModalPercent;
    }

    @Column(name = "rate_denda", nullable = false, precision = 17, scale = 17)
    public double getRateDenda() {
        return this.rateDenda;
    }

    public void setRateDenda (double rateDenda) {
        this.rateDenda = rateDenda;
    }

    @Column(name = "day_rate_denda")
    public long getDayRateDenda() {
        return dayRateDenda;
    }

    public void setDayRateDenda(long dayRateDenda) {
        this.dayRateDenda = dayRateDenda;
    }

    @Column(name = "janka_waktu_min", length = 4)
    public String getJankaWaktuMin() {
        return this.jankaWaktuMin;
    }

    public void setJankaWaktuMin(String jankaWaktuMin) {
        this.jankaWaktuMin = jankaWaktuMin;
    }

    @Column(name = "jangka_waktu_max", length = 4)
    public String getJangkaWaktuMax() {
        return this.jangkaWaktuMax;
    }

    public void setJangkaWaktuMax(String jangkaWaktuMax) {
        this.jangkaWaktuMax = jangkaWaktuMax;
    }

    @Column(name = "proses_pembayaran", nullable = false, length = 1)
    public String getProsesPembayaran() {
        return this.prosesPembayaran;
    }

    public void setProsesPembayaran(String prosesPembayaran) {
        this.prosesPembayaran = prosesPembayaran;
    }

    /**
     * tipe bunga:<br/>
     * 1-> Flat <br/>
     * 2-> Efektif
     *
     */
    @Column(name = "tipe_bunga", nullable = false, length = 1)
    public String getTipeBunga() {
        return this.tipeBunga;
    }

    /**
     * tipe bunga:<br/>
     * 1-> Flat <br/>
     * 2-> Efektif
     *
     * @param tipeBunga
     */
    public void setTipeBunga(String tipeBunga) {
        this.tipeBunga = tipeBunga;
    }

    /**
     * document checklist format:<br/>
     * [document-1]=[0/1],....[document-n]=[0/1]<br/>
     * document= document yg di display<br/>
     * 0=tidak wajib, 1 =wajib<br/>
     * contoh:<br/>
     * KTP=1,NPWP=0 ==> ktp wajib, npwp tidak wajib
     *
     * @return
     */
    @Column(name = "document_chklist", length = 500)
    public String getDocumentChecklist() {
        return this.documentChecklist;
    }

    /**
     * document checklist format:<br/>
     * [document-1]=[0/1],....[document-n]=[0/1]<br/>
     * document= document yg di display<br/>
     * 0=tidak wajib, 1 =wajib<br/>
     * contoh:<br/>
     * KTP=1,NPWP=0 ==> ktp wajib, npwp tidak wajib
     *
     */
    public void setDocumentChecklist(String documentChecklist) {
        this.documentChecklist = documentChecklist;
    }

    @Column(name = "tipe_jaminan_diterima", length = 100)
    public String getTipeJaminanDiterima() {
        return this.tipeJaminanDiterima;
    }

    public void setTipeJaminanDiterima(String tipeJaminanDiterima) {
        this.tipeJaminanDiterima = tipeJaminanDiterima;
    }

//	@Transient
//	public List<String> getListOfTipeJaminan() {
//		if(tipeJaminanDiterima!=null){
//			listOfTipeJaminan.clear();
//			listOfTipeJaminan.addAll(Arrays.asList(tipeJaminanDiterima.split(",")));
//		}
//		return listOfTipeJaminan;
//	}
//
//	public void setListOfTipeJaminan(List<String> listOfTipeJaminan) {
//		this.listOfTipeJaminan = listOfTipeJaminan;
//	}

    @Column(name = "scoring_scheme", length = 100)
    public String getScoringScheme() {
        return this.scoringScheme;
    }

    public void setScoringScheme(String scoringScheme) {
        this.scoringScheme = scoringScheme;
    }

    @Column(name = "collection_of_kode_biaya", length = 200)
    public String getKodeBiayas() {
        return this.kodeBiayas;
    }

    public void setKodeBiayas(String kodeBiayas) {
        this.kodeBiayas = kodeBiayas;
    }

//	@Transient
//	public List<String> getListOfKodeBiaya() {
//		if(listOfKodeBiaya.size()==0){
//			listOfKodeBiaya.addAll(Arrays.asList(kodeBiayas.split(",")));
//		}
//		return listOfKodeBiaya;
//	}
//
//	public void setListOfKodeBiaya(List<String> listOfKodeBiaya) {
//		this.listOfKodeBiaya = listOfKodeBiaya;
//	}

    @Column(name = "norek_kpyd", length = 7)
    public String getNorekKPYD() {
        return this.norekKPYD;
    }

    public void setNorekKPYD(String norekKPYD) {
        this.norekKPYD = norekKPYD;
    }

    @Column(name = "norek_penyisihan_kpyd", length = 7)
    public String getNorekPenyisihanKPYD() {
        return this.norekPenyisihanKPYD;
    }

    public void setNorekPenyisihanKPYD(String norekPenyisihanKPYD) {
        this.norekPenyisihanKPYD = norekPenyisihanKPYD;
    }

    @Column(name = "norek_ktgr", length = 7)
    public String getNorekKTGR() {
        return this.norekKTGR;
    }

    public void setNorekKTGR(String norekKTGR) {
        this.norekKTGR = norekKTGR;
    }

    @Column(name = "norek_mutasi_barang", length = 7)
    public String getNorekMutasiBarang() {
        return this.norekMutasiBarang;
    }

    public void setNorekMutasiBarang(String norekMutasiBarang) {
        this.norekMutasiBarang = norekMutasiBarang;
    }

    @Column(name = "norek_pendapatan_sm_ymhd", length = 7)
    public String getNorekPendapatanSMYMHD() {
        return this.norekPendapatanSMYMHD;
    }

    public void setNorekPendapatanSMYMHD(String norekPendapatanSMYMHD) {
        this.norekPendapatanSMYMHD = norekPendapatanSMYMHD;
    }

    @Column(name = "norek_pendapatan_sm", length = 7)
    public String getNorekPendapatanSM() {
        return this.norekPendapatanSM;
    }

    public void setNorekPendapatanSM(String norekPendapatanSM) {
        this.norekPendapatanSM = norekPendapatanSM;
    }

    @Column(name = "norek_pendapatan_sm_accrue", length = 7)
    public String getNorekPendapatanSMAccrue() {
        return this.norekPendapatanSMAccrue;
    }

    public void setNorekPendapatanSMAccrue(String norekPendapatanSMAccrue) {
        this.norekPendapatanSMAccrue = norekPendapatanSMAccrue;
    }

    @Column(name = "norek_pendapatan_denda", length = 7)
    public String getNorekPendapatanDenda() {
        return this.norekPendapatanDenda;
    }

    public void setNorekPendapatanDenda(String norekPendapatanDenda) {
        this.norekPendapatanDenda = norekPendapatanDenda;
    }

    @Column(name = "norek_denda_tgk_pokok_ymhd", length = 7)
    public String getNorekDendaTunggakanPokokYMHD() {
        return this.norekDendaTunggakanPokokYMHD;
    }

    public void setNorekDendaTunggakanPokokYMHD(String norekDendaTunggakanPokokYMHD) {
        this.norekDendaTunggakanPokokYMHD = norekDendaTunggakanPokokYMHD;
    }

    @Column(name = "norek_denda_tgk_sm_ymhd", length = 7)
    public String getNorekDendaTunggakanSMYMHD() {
        return this.norekDendaTunggakanSMYMHD;
    }

    public void setNorekDendaTunggakanSMYMHD(String norekDendaTunggakanSMYMHD) {
        this.norekDendaTunggakanSMYMHD = norekDendaTunggakanSMYMHD;
    }

    @Column(name = "norek_tgk_pokok", length = 7)
    public String getNorekTunggakanPokok() {
        return this.norekTunggakanPokok;
    }

    public void setNorekTunggakanPokok(String norekTunggakanPokok) {
        this.norekTunggakanPokok = norekTunggakanPokok;
    }

    @Column(name = "norek_tag_sm_npl", length = 7)
    public String getNorekTagKonSMNPL() {
        return this.norekTagKonSMNPL;
    }

    public void setNorekTagKonSMNPL(String norekTagKonSMNPL) {
        this.norekTagKonSMNPL = norekTagKonSMNPL;
    }

    @Column(name = "norek_tag_sm_npl_ktr", length = 7)
    public String getNorekTagKonSMNPLKontra() {
        return this.norekTagKonSMNPLKontra;
    }

    public void setNorekTagKonSMNPLKontra(String norekTagKonSMNPLKontra) {
        this.norekTagKonSMNPLKontra = norekTagKonSMNPLKontra;
    }

    @Column(name = "norek_tag_denda_tgk_pkk", length = 7)
    public String getNorekTagKonDendaTunggakanPokok() {
        return this.norekTagKonDendaTunggakanPokok;
    }

    public void setNorekTagKonDendaTunggakanPokok(String norekTagKonDendaTunggakanPokok) {
        this.norekTagKonDendaTunggakanPokok = norekTagKonDendaTunggakanPokok;
    }

    @Column(name = "norek_tag_denda_tgk_pkk_ktr", length = 7)
    public String getNorekTagKonDendaTunggakanPokokKontra() {
        return this.norekTagKonDendaTunggakanPokokKontra;
    }

    public void setNorekTagKonDendaTunggakanPokokKontra(String norekTagKonDendaTunggakanPokokKontra) {
        this.norekTagKonDendaTunggakanPokokKontra = norekTagKonDendaTunggakanPokokKontra;
    }

    @Column(name = "norek_tag_denda_tgk_sm", length = 7)
    public String getNorekTagKonDendaTunggakanSM() {
        return this.norekTagKonDendaTunggakanSM;
    }

    public void setNorekTagKonDendaTunggakanSM(String norekTagKonDendaTunggakanSM) {
        this.norekTagKonDendaTunggakanSM = norekTagKonDendaTunggakanSM;
    }

    @Column(name = "norek_tag_denda_tgk_sm_ktr", length = 7)
    public String getNorekTagKonDendaTunggakanSMKontra() {
        return this.norekTagKonDendaTunggakanSMKontra;
    }

    public void setNorekTagKonDendaTunggakanSMKontra(String norekTagKonDendaTunggakanSMKontra) {
        this.norekTagKonDendaTunggakanSMKontra = norekTagKonDendaTunggakanSMKontra;
    }

    @Column(name = "norek_pendapatan_ayd", length = 7)
    public String getNorekPendapatanAYD() {
        return this.norekPendapatanAYD;
    }

    public void setNorekPendapatanAYD(String norekPendapatanAYD) {
        this.norekPendapatanAYD = norekPendapatanAYD;
    }

    @Column(name = "norek_kerugian_ayd", length = 7)
    public String getNorekKerugianAYD() {
        return this.norekKerugianAYD;
    }

    public void setNorekKerugianAYD(String norekKerugianAYD) {
        this.norekKerugianAYD = norekKerugianAYD;
    }

    @Column(name = "norek_bea_lelang_penjual", length = 7)
    public String getNorekBeaLelangPenjual() {
        return this.norekBeaLelangPenjual;
    }

    public void setNorekBeaLelangPenjual(String norekBeaLelangPenjual) {
        this.norekBeaLelangPenjual = norekBeaLelangPenjual;
    }

    @Column(name = "norek_bea_lelang_pembeli", length = 7)
    public String getNorekBeaLelangPembeli() {
        return this.norekBeaLelangPembeli;
    }

    public void setNorekBeaLelangPembeli(String norekBeaLelangPembeli) {
        this.norekBeaLelangPembeli = norekBeaLelangPembeli;
    }

    @Column(name = "norek_ukel", length = 7)
    public String getNorekUkel() {
        return this.norekUkel;
    }

    public void setNorekUkel(String norekUkel) {
        this.norekUkel = norekUkel;
    }

    @Column(name = "bea_penjual", precision = 17, scale = 17)
    public Double getBeaPenjual() {
        return this.beaPenjual;
    }

    public void setBeaPenjual(Double beaPenjual) {
        this.beaPenjual = beaPenjual;
    }

    @Column(name = "bea_pembeli", precision = 17, scale = 17)
    public Double getBeaPembeli() {
        return this.beaPembeli;
    }

    public void setBeaPembeli(Double beaPembeli) {
        this.beaPembeli = beaPembeli;
    }

	/*@Column(name = "perusahaan_asuransi", length = 10)
	public String getPerusahaanAsuransi() {
		return this.perusahaanAsuransi;
	}

	public void setPerusahaanAsuransi(String perusahaanAsuransi) {
		this.perusahaanAsuransi = perusahaanAsuransi;
	}*/

    @Column(name = "norek_hutang_subrogasi", length = 7)
    public String getNorekHutangSubrogasi() {
        return this.norekHutangSubrogasi;
    }

    public void setNorekHutangSubrogasi(String norekHutangSubrogasi) {
        this.norekHutangSubrogasi = norekHutangSubrogasi;
    }

    @Column(name = "norek_fee_subrogasi", length = 7)
    public String getNorekFeeSubrogasi() {
        return this.norekFeeSubrogasi;
    }

    public void setNorekFeeSubrogasi(String norekFeeSubrogasi) {
        this.norekFeeSubrogasi = norekFeeSubrogasi;
    }

    @Column(name = "norek_pendptn_subrogasi", length = 7)
    public String getNorekPendapatanSubrogasi() {
        return this.norekPendapatanSubrogasi;
    }

    public void setNorekPendapatanSubrogasi(String norekPendapatanSubrogasi) {
        this.norekPendapatanSubrogasi = norekPendapatanSubrogasi;
    }

    @Column(name = "norek_htg_klaim_asuransi", length = 7)
    public String getNorekHutangKlaimAsuransi() {
        return this.norekHutangKlaimAsuransi;
    }

    public void setNorekHutangKlaimAsuransi(String norekHutangKlaimAsuransi) {
        this.norekHutangKlaimAsuransi = norekHutangKlaimAsuransi;
    }

    @Column(name = "norek_biaya_penyisihan_pyd", length = 7)
    public String getNorekBiayaPenyisihanPYD() {
        return this.norekBiayaPenyisihanPYD;
    }

    public void setNorekBiayaPenyisihanPYD(String norekBiayaPenyisihanPYD) {
        this.norekBiayaPenyisihanPYD = norekBiayaPenyisihanPYD;
    }

    @Column(name = "norek_memo_db_trm_hk", length = 7)
    public String getNorekMemoDebitTerimaHK() {
        return this.norekMemoDebitTerimaHK;
    }

    public void setNorekMemoDebitTerimaHK(String norekMemoDebitTerimaHK) {
        this.norekMemoDebitTerimaHK = norekMemoDebitTerimaHK;
    }

    @Column(name = "norek_memo_cr_trm_hk", length = 7)
    public String getNorekMemoKreditTerimaHK() {
        return this.norekMemoKreditTerimaHK;
    }

    public void setNorekMemoKreditTerimaHK(String norekMemoKreditTerimaHK) {
        this.norekMemoKreditTerimaHK = norekMemoKreditTerimaHK;
    }

    @Column(name = "norek_memo_db_pinj_hk", length = 7)
    public String getNorekMemoDebitPinjamanHK() {
        return this.norekMemoDebitPinjamanHK;
    }

    public void setNorekMemoDebitPinjamanHK(String norekMemoDebitPinjamanHK) {
        this.norekMemoDebitPinjamanHK = norekMemoDebitPinjamanHK;
    }

    @Column(name = "norek_memo_cr_pinj_hk", length = 7)
    public String getNorekMemoKreditPinjamanHK() {
        return this.norekMemoKreditPinjamanHK;
    }

    public void setNorekMemoKreditPinjamanHK(String norekMemoKreditPinjamanHK) {
        this.norekMemoKreditPinjamanHK = norekMemoKreditPinjamanHK;
    }

    @Column(name = "keterangan_no", length = 20)
    public String getKeteranganNo() {
        return this.keteranganNo;
    }

    public void setKeteranganNo(String keteranganNo) {
        this.keteranganNo = keteranganNo;
    }

    @Column(name = "status_ijp", length = 1)
    public String getStatusIjp() {
        return statusIjp;
    }

    public void setStatusIjp(String statusIjp) {
        this.statusIjp = statusIjp;
    }

    @Column(name = "proses_scoring", length = 1)
    public String getProsesScoring() {
        return prosesScoring;
    }

    public void setProsesScoring(String prosesScoring) {
        this.prosesScoring = prosesScoring;
    }

    @Column(name = "proses_lpbj", length = 1)
    public String getProsesLPBJ() {
        return prosesLPBJ;
    }

    public void setProsesLPBJ(String prosesLPBJ) {
        this.prosesLPBJ = prosesLPBJ;
    }

    @Column(name = "status_bj", length = 1)
    public String getStatusBJ() {
        return statusBJ;
    }

    public void setStatusBJ(String statusBJ) {
        this.statusBJ = statusBJ;
    }

    @Column(name = "status_mulia", length = 1)
    public String getStatusMulia() {
        return statusMulia;
    }

    public void setStatusMulia(String statusMulia) {
        this.statusMulia = statusMulia;
    }

    @Column(name = "tenor_diterima", length = 100)
    public String getTenorDiterima() {
        return tenorDiterima;
    }

    public void setTenorDiterima(String tenorDiterima) {
        this.tenorDiterima = tenorDiterima;
    }
}
