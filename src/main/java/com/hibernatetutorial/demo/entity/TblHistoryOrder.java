package com.hibernatetutorial.demo.entity;


import com.hibernatetutorial.demo.entity.audit.AuditTrail;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tbl_history_order")
public class TblHistoryOrder extends AuditTrail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistoryOrderPK;
    private String idHistoryOrder;
    private String idProductItem;
    private BigDecimal price;
    private Long quantity;

    public TblHistoryOrder() {
    }

    public Long getIdHistoryOrderPK() {
        return idHistoryOrderPK;
    }

    public void setIdHistoryOrderPK(Long idHistoryOrderPK) {
        this.idHistoryOrderPK = idHistoryOrderPK;
    }

    public String getIdHistoryOrder() {
        return idHistoryOrder;
    }

    public void setIdHistoryOrder(String idHistoryOrder) {
        this.idHistoryOrder = idHistoryOrder;
    }

    public String getIdProductItem() {
        return idProductItem;
    }

    public void setIdProductItem(String idProductItem) {
        this.idProductItem = idProductItem;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
