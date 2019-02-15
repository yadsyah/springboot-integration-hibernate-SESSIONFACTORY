package com.hibernatetutorial.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_kategori_product")
public class TblKategoriProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idKategory;

    private String nameKategory;

    public TblKategoriProduct() {
    }

    public Long getIdKategory() {
        return idKategory;
    }

    public void setIdKategory(Long idKategory) {
        this.idKategory = idKategory;
    }

    public String getNameKategory() {
        return nameKategory;
    }

    public void setNameKategory(String nameKategory) {
        this.nameKategory = nameKategory;
    }
}
