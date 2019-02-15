create table bank_account
(
	id bigint auto_increment
		primary key,
	Balance double not null,
	Full_Name varchar(128) not null
)
engine=MyISAM
;

create table customer
(
	customerId bigint auto_increment
		primary key,
	customer_det_id bigint null,
	email varchar(255) null,
	name varchar(255) null,
	constraint UK_3qgg01qojcmbdp47dkaom9x45
		unique (email)
)
engine=MyISAM
;

create table customeralamat
(
	customerAlamatId bigint auto_increment
		primary key,
	customerId bigint null,
	kodePos int null,
	namaAlamat varchar(255) null,
	negara varchar(255) null
)
engine=MyISAM
;

create table customerdetail
(
	customerDetailId bigint auto_increment
		primary key,
	isAdult bit not null,
	jenisKelamin varchar(255) null,
	noKTP varchar(255) null,
	tglLahir datetime null
)
engine=MyISAM
;


create table tbl_golongan
(
	kode_golongan varchar(2) not null
		primary key,
	createdAt datetime null,
	createdBy varchar(255) null,
	updatedAt datetime null,
	updatedBy varchar(255) null,
	jenis_pembulatan varchar(1) null,
	keterangan varchar(100) null,
	maksimal_up decimal(12,2) null,
	minimal_up decimal(12,2) null,
	pembulatan_up decimal(12,2) null
)
engine=MyISAM
;

create table tbl_param
(
	KEY_ varchar(100) not null
		primary key,
	createdAt datetime not null,
	createdBy varchar(255) null,
	updatedAt datetime not null,
	updatedBy varchar(255) null,
	description_ varchar(200) not null,
	VALUE_ varchar(600) not null
)
engine=MyISAM
;

create table tbl_product
(
	kode_product varchar(2) not null
		primary key,
	createdAt datetime null,
	createdBy varchar(255) null,
	updatedAt datetime null,
	updatedBy varchar(255) null,
	basis varchar(1) not null,
	bea_pembeli double null,
	bea_penjual double null,
	day_rate_denda bigint null,
	document_chklist varchar(500) null,
	is_active varchar(1) not null,
	jangka_waktu_max varchar(4) null,
	janka_waktu_min varchar(4) null,
	keterangan_no varchar(20) null,
	collection_of_kode_biaya varchar(200) null,
	nama_product varchar(100) not null,
	norek_bea_lelang_pembeli varchar(7) null,
	norek_bea_lelang_penjual varchar(7) null,
	norek_biaya_penyisihan_pyd varchar(7) null,
	norek_denda_tgk_pokok_ymhd varchar(7) null,
	norek_denda_tgk_sm_ymhd varchar(7) null,
	norek_fee_subrogasi varchar(7) null,
	norek_htg_klaim_asuransi varchar(7) null,
	norek_hutang_subrogasi varchar(7) null,
	no_coa_ia varchar(5) not null,
	norek_kpyd varchar(7) null,
	norek_ktgr varchar(7) null,
	norek_kerugian_ayd varchar(7) null,
	norek_memo_db_pinj_hk varchar(7) null,
	norek_memo_db_trm_hk varchar(7) null,
	norek_memo_cr_pinj_hk varchar(7) null,
	norek_memo_cr_trm_hk varchar(7) null,
	norek_mutasi_barang varchar(7) null,
	norek_pendapatan_ayd varchar(7) null,
	norek_pendapatan_denda varchar(7) null,
	norek_pendapatan_sm varchar(7) null,
	norek_pendapatan_sm_accrue varchar(7) null,
	norek_pendapatan_sm_ymhd varchar(7) null,
	norek_pendptn_subrogasi varchar(7) null,
	norek_penyisihan_kpyd varchar(7) null,
	norek_tag_denda_tgk_pkk varchar(7) null,
	norek_tag_denda_tgk_pkk_ktr varchar(7) null,
	norek_tag_denda_tgk_sm varchar(7) null,
	norek_tag_denda_tgk_sm_ktr varchar(7) null,
	norek_tag_sm_npl varchar(7) null,
	norek_tag_sm_npl_ktr varchar(7) null,
	norek_tgk_pokok varchar(7) null,
	norek_ukel varchar(7) null,
	pinjaman_max decimal(12,2) not null,
	pinjaman_min decimal(12,2) not null,
	proses_kredit varchar(1) not null,
	proses_lpbj varchar(1) null,
	proses_pembayaran varchar(1) not null,
	proses_scoring varchar(1) null,
	rate_denda double not null,
	scoring_scheme varchar(100) null,
	sewa_modal_percent double not null,
	sewa_modal_percent_anuitas double not null,
	sewa_modal_periode varchar(4) null,
	status_bj varchar(1) null,
	status_ijp varchar(1) null,
	status_mulia varchar(1) null,
	tenor_diterima varchar(100) null,
	tipe_bunga varchar(1) not null,
	tipe_jaminan_diterima varchar(100) null,
	tipe_nasabah varchar(1) not null
)
engine=MyISAM
;

create table tbl_rasio_tarif
(
	id_rasio_tarif bigint not null
		primary key,
	createdAt datetime null,
	createdBy varchar(255) null,
	updatedAt datetime null,
	updatedBy varchar(255) null,
	jenis_nasabah varchar(1) null,
	keterangan_no varchar(20) null,
	kode_biaya varchar(16) null,
	kode_golongan varchar(10) null,
	kode_product varchar(2) null,
	min_days int null,
	min_persent_up double null,
	min_rate double null,
	no_rek varchar(16) null,
	pembulatan_tarif decimal(12,2) null,
	kode_rubrik varchar(2) null,
	tarif_rasio double null,
	tenor varchar(3) null,
	tgl_berlaku date not null,
	tipe_jaminan varchar(4) null
)
engine=MyISAM
;

create table tbl_rekening_emas
(
	norek varchar(16) not null
		primary key,
	createdAt datetime null,
	createdBy varchar(255) null,
	updatedAt datetime null,
	updatedBy varchar(255) null,
	branch_code varchar(5) null,
	description varchar(200) null,
	last_trx_date date null,
	mutasi_d decimal(12,4) null,
	mutasi_k decimal(12,4) null,
	saldo_akhir decimal(12,4) null,
	saldo_awal decimal(12,4) null,
	saldo_blokir decimal(12,4) null,
	saldo_normal varchar(1) null
)
engine=MyISAM
;

create table tbl_rekening_nasabah
(
	norek varchar(16) not null
		primary key,
	createdAt datetime null,
	createdBy varchar(255) null,
	updatedAt datetime null,
	updatedBy varchar(255) null,
	branch_code varchar(5) null,
	cif varchar(10) null,
	description varchar(200) null,
	last_trx_date date null,
	mutasi_d decimal(12,2) null,
	mutasi_k decimal(12,2) null,
	no_coa varchar(15) not null,
	norek_pendamping varchar(16) null,
	saldo_akhir decimal(12,2) null,
	saldo_awal decimal(12,2) null,
	saldo_blokir decimal(12,2) null,
	saldo_minimal decimal(12,2) null,
	saldo_normal varchar(1) null
)
engine=MyISAM
;

create table tbl_standard_harga
(
	id bigint not null
		primary key,
	createdAt datetime not null,
	createdBy varchar(255) null,
	updatedAt datetime not null,
	updatedBy varchar(255) null,
	description varchar(255) null,
	flag_guk varchar(1) null,
	harga_berlian decimal(12,2) null,
	harga_berlian_2 decimal(12,2) null,
	harga_emas decimal(12,2) null,
	harga_lantakan_emas decimal(12,2) null,
	harga_mutiara decimal(12,2) null,
	harga_perak decimal(12,2) null,
	tgl_berlaku date null
)
engine=MyISAM
;

create table tbl_tabungan
(
	norek varchar(16) not null
		primary key,
	createdAt datetime null,
	createdBy varchar(255) null,
	updatedAt datetime null,
	updatedBy varchar(255) null,
	amount_biaya decimal(20,2) null,
	branch_code varchar(5) not null,
	branch_code_blokir varchar(5) null,
	ccy varchar(3) null,
	cif varchar(16) not null,
	client_id varchar(10) null,
	customer_name varchar(100) null,
	customer_name_qq varchar(100) null,
	default_pin varchar(32) null,
	det_blokir_saldo varchar(200) null,
	doc_checklist varchar(100) null,
	fail_pin_count int null,
	flag_cetak_buku varchar(1) null,
	flag_denda_order varchar(1) null,
	flag_rek_koran varchar(1) null,
	id_agent varchar(12) null,
	id_negara varchar(15) null,
	is_cetak varchar(1) null,
	is_rekening_qq varchar(1) null,
	jurnal_id bigint null,
	ket_blokir_rek varchar(100) null,
	branch_mobile_id varchar(10) null,
	no_buku varchar(16) null,
	no_cetak int null,
	no_kartu varchar(18) null,
	pin varchar(32) null,
	product_code varchar(2) not null,
	referral_code varchar(10) null,
	referral_name varchar(60) null,
	rekening_induk varchar(16) null,
	saldo_buku decimal(20,2) null,
	saldo_emas_buku double null,
	status_rek varchar(1) not null,
	tgl_blokir_rek date null,
	tgl_buka date null,
	tgl_jatuh_tempo_biaya date null,
	tgl_lahir_qq date null,
	tgl_last_amortisasi date null,
	tgl_last_bayar_biaya date null,
	tgl_next_amortisasi date null,
	tgl_tutup date null
)
engine=MyISAM
;

create table tbl_tipe_jaminan
(
	id_tipe_jaminan varchar(4) not null
		primary key,
	createdAt datetime null,
	createdBy varchar(255) null,
	updatedAt datetime null,
	updatedBy varchar(255) null,
	nama varchar(100) not null,
	no_urut bigint null,
	rubrik varchar(2) null
)
engine=MyISAM
;

