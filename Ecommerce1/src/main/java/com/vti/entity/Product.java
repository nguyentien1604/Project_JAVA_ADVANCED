/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vti.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Entity
@Table(name = "PRODUCT", catalog = "ECOMMERCE1")
public class Product implements Serializable {

    @Id
    @Column(name = "PRODUCTID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short PRODUCTID;

    @Column(name = "PRODUCTNAME", length = 50, nullable = false)
    private String productname;

    @ManyToOne
    @JoinColumn(name = "CATEGORYID", nullable = false)

    private Category category;
//        @ManyToOne
//    @JoinColumn(name = "CATEGORYID", nullable = false)
//    private Category category;

    @ManyToOne
    @JoinColumn(name = "SUPPLIERID", nullable = false)
    
    private Supplier supplier;

    @Column(name = "UNITQUANTITY", nullable = false)
    private short quantity;

    @Column(name = "UnitPrice")
    private BigDecimal unitPrice;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    @Column(name = "RATING")
    private short rating;
    @Column(name = "PICTURE", length = 255)
    private String picture;

    @Column(name = "TYPE", length = 50)
    private String type;

    public Product() {
    }

    public Product(short PRODUCTID, String productname, Category category, Supplier supplier, short quantity, BigDecimal unitPrice, String description, short rating, String picture, String type) {
        this.PRODUCTID = PRODUCTID;
        this.productname = productname;
        this.category = category;
        this.supplier = supplier;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.description = description;
        this.rating = rating;
        this.picture = picture;
        this.type = type;
    }

    /**
     * @return the PRODUCTID
     */
    public short getPRODUCTID() {
        return PRODUCTID;
    }

    /**
     * @param PRODUCTID the PRODUCTID to set
     */
    public void setPRODUCTID(short PRODUCTID) {
        this.PRODUCTID = PRODUCTID;
    }

    /**
     * @return the productname
     */
    public String getProductname() {
        return productname;
    }

    /**
     * @param productname the productname to set
     */
    public void setProductname(String productname) {
        this.productname = productname;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * @return the quantity
     */
    public short getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the unitPrice
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the rating
     */
    public short getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(short rating) {
        this.rating = rating;
    }

    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product{" + "PRODUCTID=" + PRODUCTID
                + ", productname=" + productname
                + ", category=" + category
                + ", supplier=" + supplier
                + ", quantity=" + quantity
                + ", unitPrice=" + unitPrice
                + ", description=" + description
                + ", rating=" + rating
                + ", picture=" + picture
                + ", type=" + type + '}';
    }

}
