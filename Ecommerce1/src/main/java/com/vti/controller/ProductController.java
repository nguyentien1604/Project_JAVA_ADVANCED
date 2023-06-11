/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vti.controller;

import com.vti.dto.AccountDto;
import com.vti.dto.ProductDto;
import com.vti.entity.Account;
import com.vti.entity.Category;
import com.vti.entity.Product;
import com.vti.entity.Supplier;
import com.vti.form.ProductFilterForm;
import com.vti.form.ProductFomForCreating;
import com.vti.form.ProductFormForUpDate;
import com.vti.service.IProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Controller
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping()
    public ResponseEntity<?> getAllProduct(Pageable pageable, @RequestParam(required = false) String search,
            @RequestParam(required = false) ProductFilterForm filterProduct) {
        Page<Product> productpageDB = productService.getAllProduct(pageable, search, filterProduct);
//        List<Product> listproduct = productService.getAllProduct();
//        List<ProductDto> listProductDto = listproduct.stream()
//                .map(product -> {
//                    ProductDto productDto = new ProductDto();
//
//                    productDto.setPRODUCTID(product.getPRODUCTID());
//                    productDto.setProductname(product.getProductname());
//                    productDto.setCategoryName(product.getCategory().getCategoryname().toString());
//                    productDto.setSupplierName(product.getSupplier().getSupplierName().toString());
//                    productDto.setUnitquatity(product.getUnitquatity());
//                    productDto.setUnitPrice(product.getUnitPrice());
//                    productDto.setDescription(product.getDescription());
//                    productDto.setRating(product.getRating());
//                    productDto.setPicture(product.getPicture());
//                    productDto.setType(product.getType());
//
//                    return productDto;
//                })
//                .collect(Collectors.toList());

        Page<ProductDto> pageProductDto = productpageDB.map(new Function<Product, ProductDto>() {
            @Override
            public ProductDto apply(Product t) {
                ProductDto productDto = new ProductDto();
                productDto.setPRODUCTID(t.getPRODUCTID());
                productDto.setProductname(t.getProductname());
                productDto.setCategoryName(t.getCategory().getCategoryname().toString());
                productDto.setSupplierName(t.getSupplier().getSupplierName().toString());
                productDto.setQuantity(t.getQuantity());
                productDto.setUnitPrice(t.getUnitPrice());
                productDto.setDescription(t.getDescription());
                productDto.setRating(t.getRating());
                productDto.setPicture(t.getPicture());
                productDto.setType(t.getType());

                return productDto;
            }

        });
        return new ResponseEntity<>(pageProductDto, HttpStatus.OK);
    }

    //Sản phẩm tìm theo ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductByID(@PathVariable(name = "id") short id) {
        Product product = productService.getProductByID(id);
        ProductDto productDto = new ProductDto();
        productDto.setPRODUCTID(product.getPRODUCTID());
        productDto.setProductname(product.getProductname());
        productDto.setCategoryName(product.getCategory().getCategoryname().toString());
        productDto.setSupplierName(product.getSupplier().getSupplierName().toString());
        productDto.setQuantity(product.getQuantity());
        productDto.setUnitPrice(product.getUnitPrice());
        productDto.setDescription(product.getDescription());
        productDto.setRating(product.getRating());
        productDto.setPicture(product.getPicture());
        productDto.setType(product.getType());

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    //Thêm mới sản phẩm
    @PostMapping()
    public ResponseEntity<?> createNewProduct(@RequestBody ProductFomForCreating productFomForCreating) {
        try {
            Product product = productService.createNewProduct(productFomForCreating);

            ProductDto productDto = new ProductDto();

            productDto.setPRODUCTID(product.getPRODUCTID());
            productDto.setProductname(product.getProductname());
            productDto.setCategoryName(product.getCategory().getCategoryname().toString());
            productDto.setSupplierName(product.getSupplier().getSupplierName().toString());
            productDto.setQuantity(product.getQuantity());
            productDto.setUnitPrice(product.getUnitPrice());
            productDto.setDescription(product.getDescription());
            productDto.setRating(product.getRating());
            productDto.setPicture(product.getPicture());
            productDto.setType(product.getType());
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable(name = "id") short id,
            @RequestBody ProductFormForUpDate productFormForUpDate) {
        Product productUpdate = productService.updateProduct(id, productFormForUpDate);

        ProductDto productDto = new ProductDto();
        productDto.setPRODUCTID(productUpdate.getPRODUCTID());
        productDto.setProductname(productUpdate.getProductname());
        productDto.setCategoryName(productUpdate.getCategory().getCategoryname().toString());
        productDto.setSupplierName(productUpdate.getSupplier().getSupplierName().toString());
        productDto.setQuantity(productUpdate.getQuantity());
        productDto.setUnitPrice(productUpdate.getUnitPrice());
        productDto.setDescription(productUpdate.getDescription());
        productDto.setRating(productUpdate.getRating());
        productDto.setPicture(productUpdate.getPicture());
        productDto.setType(productUpdate.getType());

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") short id) {
        try {
    //			Thực hiện lấy thông tin chi tiết của Product cần xóa, sau khi xóa xong thực hiện trả về thông tin chi tiết
    //			của Product vừa xóa
            Product productDelete = productService.getProductByID(id);

    //			Convert
            ProductDto productDeleteDto = new ProductDto();
            productDeleteDto.setPRODUCTID(productDelete.getPRODUCTID());
            productDeleteDto.setProductname(productDelete.getProductname());
            productDeleteDto.setCategoryName(productDelete.getCategory().getCategoryname().toString());
            productDeleteDto.setSupplierName(productDelete.getSupplier().getSupplierName().toString());
            productDeleteDto.setQuantity(productDelete.getQuantity());
            productDeleteDto.setUnitPrice(productDelete.getUnitPrice());
            productDeleteDto.setDescription(productDelete.getDescription());
            productDeleteDto.setRating(productDelete.getRating());
            productDeleteDto.setPicture(productDelete.getPicture());
            productDeleteDto.setType(productDelete.getType());
//			Xóa Product
            productService.deleteProductById(id);

            return new ResponseEntity<>(productDeleteDto, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }

    }

    
   
}
