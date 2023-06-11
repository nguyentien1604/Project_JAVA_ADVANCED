/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.vti.dto;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class CategoryDto {
    private short categoryid;
    private String categoryname;

    public CategoryDto() {
    }

    public short getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(short categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    @Override
    public String toString() {
        return "CategoryDto{" + "categoryid=" + categoryid + 
                ", categoryname=" + categoryname + '}';
    }
    
    

}
