/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.vti.dto;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class SupplierDto {
 private short supplierId;
 private String supplierName;

    public SupplierDto() {
    }

 
    public short getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(short supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "SupplierDto{" + "supplierId=" + supplierId + ", supplierName=" + supplierName + '}';
    }
 
}
