/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vti.service;

import com.vti.entity.Category;
import com.vti.entity.Product;
import com.vti.entity.Supplier;
import com.vti.form.ProductFilterForm;
import com.vti.form.ProductFomForCreating;
import com.vti.form.ProductFormForUpDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface IProductService {

//    public List<Product> getAllProduct();

    public Product getProductByID(short id);

    public Product createNewProduct(ProductFomForCreating productFomForCreating);

    public Product updateProduct(short id, ProductFormForUpDate productFormForUpDate);

    public void deleteProductById(short id);

    public Page<Product> getAllProduct(Pageable pageable, String search, ProductFilterForm filterProduct);

  

 

  

   

    

}
