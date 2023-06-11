/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vti.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Entity
@Table(name = "SUPPLIER", catalog = "ECOMMERCE1")
public class Supplier {

    @Id
    @Column(name = "SUPPLIERID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short supplierId;

    @Column(name = "SUPPLIERNAME", length = 50, nullable = false, unique = true)
    private String supplierName;
    
    
    @OneToMany(mappedBy = "supplier")
    
    private List<Product> productlist;

    public Supplier() {
    }

    /**
     * @return the supplierId
     */
    public short getSupplierId() {
        return supplierId;
    }

    /**
     * @param supplierId the supplierId to set
     */
    public void setSupplierId(short supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * @return the supplierName
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * @param supplierName the supplierName to set
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "Supplier{" + "supplierId=" + supplierId + ""
                + ", supplierName=" + supplierName + '}';
    }

}
