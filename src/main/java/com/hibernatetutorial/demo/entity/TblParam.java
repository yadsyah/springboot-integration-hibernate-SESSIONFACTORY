package com.hibernatetutorial.demo.entity;

import com.hibernatetutorial.demo.entity.audit.AuditTrail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_param")
public class TblParam extends AuditTrail {

    @Id
    @Column(name = "KEY_", nullable = false,length = 100)
    private String key;

    @Column(name = "VALUE_",nullable = false,length = 600)
    private String value;
    @Column(name = "description_",nullable = false,length = 200)
    private String description;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
