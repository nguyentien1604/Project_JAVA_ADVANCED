/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vti.specification;

import com.vti.entity.Category;
import com.vti.entity.Product;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import static org.springframework.http.ContentDisposition.builder;
import static org.springframework.web.method.HandlerTypePredicate.builder;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ProductSpecification implements Specification<Product> {

    private String field;
    private String operator;
    private Object value;

    public ProductSpecification(String field, String operator, Object value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (operator.equalsIgnoreCase("LIKE")) {
            if (field.equalsIgnoreCase("Product.supplier")) {
                return criteriaBuilder.like((root.get("Product").get("Suppiler").get("SupplierName")),
                        "%" + value.toString().toLowerCase() + "%");
            }  else {
                return criteriaBuilder.like(root.get(field), "%" + value.toString() + "%");
            }

        }
//        if (operator.equalsIgnoreCase("LIKE")) {
//            if (field.equalsIgnoreCase("ProductFilterForm.categoryName")) {
//                return criteriaBuilder.like(root.get("type"), "%" + value.toString() + "%");
//            }  else {
//                return criteriaBuilder.like(root.get(field), "%" + value.toString() + "%");
//            }
//
//        }
         
        return null;

    }
}
