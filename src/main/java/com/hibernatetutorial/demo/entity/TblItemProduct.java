package com.hibernatetutorial.demo.entity;

import com.hibernatetutorial.demo.entity.audit.AuditTrail;
import com.hibernatetutorial.demo.utility.SequenceGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
@Table(name = "tbl_item_product")
public class TblItemProduct extends AuditTrail implements Serializable {

    static Calendar calendar = Calendar.getInstance();
    public static int monthPrefix = calendar.get(Calendar.MONTH);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @GenericGenerator(
            name = "product_seq",
            strategy = "com.hibernatetutorial.demo.utility.SequenceGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = SequenceGenerator.INCREMENT_PARAM,value = "2"),
                    @org.hibernate.annotations.Parameter(name = SequenceGenerator.VALUE_PREFIX_PARAMETER,value = "P_"),
                    @org.hibernate.annotations.Parameter(name = SequenceGenerator.NUMBER_FORMAT_PARAMETER,value = "%05d")
            }
    )
    private String idItem;

    private String nameProduct;

    private int stockProduct;

    private int idKategoryProduct;

    private BigDecimal price;


    public TblItemProduct() {
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
