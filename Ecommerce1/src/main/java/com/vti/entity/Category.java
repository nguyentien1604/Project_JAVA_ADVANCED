/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vti.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Entity
@Table(name = "CATEGORY", catalog = "ECOMMERCE1")
public class Category implements Serializable {

    @Column(name = "CATEGORYID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short categoryid;

    @Column(name = "CATEGORYNAME", length = 100)
    private String categoryname;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category() {
        super();
    }

    /**
     * @return the categoryid
     */
    public short getCategoryid() {
        return categoryid;
    }

    /**
     * @param categoryid the categoryid to set
     */
    public void setCategoryid(short categoryid) {
        this.categoryid = categoryid;
    }

    /**
     * @return the categoryname
     */
    public String getCategoryname() {
        return categoryname;
    }

    /**
     * @param categoryname the categoryname to set
     */
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    @Override
    public String toString() {
        return "Category{" + "categoryid=" + categoryid
                + ", categoryname=" + categoryname + '}';
    }

}
