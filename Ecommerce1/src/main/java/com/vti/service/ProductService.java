/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vti.service;

import static com.sun.tools.javac.code.Type.filter;
import com.vti.Repository.ICategoryRepository;
import com.vti.Repository.IProductRepository;
import com.vti.Repository.ISupplierRepository;
import com.vti.entity.Category;
import com.vti.entity.Product;
import com.vti.entity.Supplier;
import com.vti.form.ProductFilterForm;
import com.vti.form.ProductFomForCreating;
import com.vti.form.ProductFormForUpDate;
import com.vti.specification.ProductSpecification;
import java.util.List;
import static java.util.Locale.filter;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.cglib.core.CollectionUtils.filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productrepository;
    @Autowired
    private ISupplierRepository supplierRepository;
    @Autowired
    private ICategoryRepository categoryRepository;

//    @Override
//    public List<Product> getAllProduct() {
//        List<Product> listproduct = productrepository.findAll();
//        return listproduct;
//    }
    @Override
    public Product getProductByID(short id) {
        return productrepository.findById(id).get();
    }

    @Override
    public Product createNewProduct(ProductFomForCreating productFomForCreating) {
        Supplier supplier = supplierRepository.getReferenceById(productFomForCreating.getSupplierID());
        Category category = categoryRepository.getReferenceById(productFomForCreating.getCategoryID());

        Product product = new Product();
        product.setProductname(productFomForCreating.getProductname());
        product.setCategory(category);
        product.setSupplier(supplier);
        product.setQuantity(productFomForCreating.getQuantity());
        product.setUnitPrice(productFomForCreating.getUnitPrice());
        product.setDescription(productFomForCreating.getDescription());
        product.setRating(productFomForCreating.getRating());
        product.setPicture(productFomForCreating.getPicture());
        product.setType(productFomForCreating.getType());

        Product productNew = productrepository.save(product);

        return productNew;
    }

    @Override
    public Product updateProduct(short id, ProductFormForUpDate productFormForUpDate) {
        Product product = productrepository.getById(id);
        Supplier supplier = supplierRepository.getById(productFormForUpDate.getSupplierID());
        Category category = categoryRepository.getById(productFormForUpDate.getCategoryID());

        product.setProductname(productFormForUpDate.getProductname());
        product.setCategory(category);
        product.setSupplier(supplier);
        product.setQuantity(productFormForUpDate.getQuantity());
        product.setUnitPrice(productFormForUpDate.getUnitPrice());
        product.setDescription(productFormForUpDate.getDescription());
        product.setRating(productFormForUpDate.getRating());
        product.setPicture(productFormForUpDate.getPicture());
        product.setType(productFormForUpDate.getType());

        Product productUpdateProduct = productrepository.save(product);
        return productUpdateProduct;
    }

    @Override
    public void deleteProductById(short id) {
        productrepository.deleteById(id);
    }

    @Override
    public Page<Product> getAllProduct(Pageable pageable, String search,  ProductFilterForm filterProduct) {
        Specification<Product> where = null;

    if (!StringUtils.isEmpty(search)) {
        ProductSpecification productnameSpecification = new ProductSpecification("productname", "LIKE", search);
        ProductSpecification typeSpecification = new ProductSpecification("type", "LIKE", search);
        where = Specification.where(productnameSpecification).or(typeSpecification);
    }

    if (filterProduct != null && filterProduct.getSupplierName()!= null && !StringUtils.isEmpty(filterProduct.getSupplierName())) {
        ProductSpecification supplierSpecification = new ProductSpecification("supplierName", "LIKE", filterProduct.getSupplierName());
        where = where != null ? where.and(supplierSpecification) : Specification.where(supplierSpecification);
    }
//
    if (filterProduct != null && filterProduct.getCatagoryName()!= null && !StringUtils.isEmpty(filterProduct.getCatagoryName())) {
        ProductSpecification categorySpecification = new ProductSpecification("categoryName", "LIKE", filterProduct.getCatagoryName());
        where = where != null ? where.and(categorySpecification) : Specification.where(categorySpecification);
    }

        return productrepository.findAll(where, pageable);
        
    }

   

    

}
