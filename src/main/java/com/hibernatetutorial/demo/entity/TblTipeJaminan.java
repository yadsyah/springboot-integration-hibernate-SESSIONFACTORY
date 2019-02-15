package com.hibernatetutorial.demo.entity;

import com.hibernatetutorial.demo.entity.audit.AuditTrail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tbl_tipe_jaminan")
public class TblTipeJaminan extends AuditTrail implements Serializable {

    private String idTipeJaminan;
    private String nama;
    private String rubrik;
    private long noUrut;

    public TblTipeJaminan() {
    }

    public TblTipeJaminan(String idTipeJaminan, String nama) {
        this.idTipeJaminan = idTipeJaminan;
        this.nama = nama;

    }

    public TblTipeJaminan(String idTipeJaminan, String nama, String rubrik, long noUrut) {
        this.idTipeJaminan = idTipeJaminan;
        this.nama = nama;
        this.rubrik = rubrik;
        this.noUrut = noUrut;
    }

    @Id
    @Column(name = "id_tipe_jaminan", nullable = false, length = 4)
    public String getIdTipeJaminan() {
        return this.idTipeJaminan;
    }

    public void setIdTipeJaminan(String idTipeJaminan) {
        this.idTipeJaminan = idTipeJaminan;
    }

    @Column(name = "nama", nullable = false, length = 100)
    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Column(name = "rubrik", length = 2)
    public String getRubrik() {
        return this.rubrik;
    }

    public void setRubrik(String rubrik) {
        this.rubrik = rubrik;
    }

    @Column(name = "no_urut", length = 3)
    public long getNoUrut() {
        return noUrut;
    }

    public void setNoUrut(long noUrut) {
        this.noUrut = noUrut;
    }


}
