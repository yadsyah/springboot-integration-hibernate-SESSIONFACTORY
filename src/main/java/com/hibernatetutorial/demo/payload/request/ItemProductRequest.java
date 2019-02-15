package com.hibernatetutorial.demo.payload.request;

import java.math.BigDecimal;

public class ItemProductRequest {

    private String nameProduct;

    private int stockProduct;

    private int idKategoryProduct;

    private String price;

    public ItemProductRequest() {
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getStockProduct() {
        return stockProduct;
    }

    public void setStockProduct(int stockProduct) {
        this.stockProduct = stockProduct;
    }

    public int getIdKategoryProduct() {
        return idKategoryProduct;
    }

    public void setIdKategoryProduct(int idKategoryProduct) {
        this.idKategoryProduct = idKategoryProduct;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
