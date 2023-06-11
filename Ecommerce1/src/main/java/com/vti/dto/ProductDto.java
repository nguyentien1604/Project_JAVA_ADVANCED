/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vti.dto;

import com.vti.entity.Category;
import com.vti.entity.Supplier;
import java.math.BigDecimal;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ProductDto {

    private short PRODUCTID;
    private String productname;
    private String categoryName;
    private String supplierName;
    private short quantity;
    private BigDecimal unitPrice;
    private String description;
    private short rating;
    private String picture;
    private String type;

    public ProductDto() {
    }

    public ProductDto(short PRODUCTID, String productname, String categoryName, String supplierName, short quantity, BigDecimal unitPrice, String description, short rating, String picture, String type) {
        this.PRODUCTID = PRODUCTID;
        this.productname = productname;
        this.categoryName = categoryName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.description = description;
        this.rating = rating;
        this.picture = picture;
        this.type = type;
    }

 
    public short getPRODUCTID() {
        return PRODUCTID;
    }

    public void setPRODUCTID(short PRODUCTID) {
        this.PRODUCTID = PRODUCTID;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

   
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   

    @Override
    public String toString() {
        return "ProductDto{" + "PRODUCTID=" + PRODUCTID +
                ", productname=" + productname + 
                ", categoryName=" + categoryName + 
                ", supplierName=" + supplierName + 
                ", unitquatity=" + quantity + 
                ", unitPrice=" + unitPrice + 
                ", description=" + description + 
                ", rating=" + rating + 
                ", picture=" + picture + 
                ", type=" + type + '}';
    }
    
    
    
}
