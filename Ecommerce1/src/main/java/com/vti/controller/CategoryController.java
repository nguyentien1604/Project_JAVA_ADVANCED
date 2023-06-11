/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.vti.controller;

import com.vti.Repository.ICategoryRepository;
import com.vti.dto.CategoryDto;
import com.vti.entity.Category;
import com.vti.service.ICategoryService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Controller
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

@GetMapping()
public ResponseEntity<?> getAllCategory()
{
    List<Category>listcategories=categoryService.getAllCategory();
    List<CategoryDto> listCategoryDto=new ArrayList<>();
    for(Category listCategory: listcategories)
    {
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setCategoryid(listCategory.getCategoryid());
        categoryDto.setCategoryname(listCategory.getCategoryname());
        listCategoryDto.add(categoryDto);
    }
    return new ResponseEntity<>(listCategoryDto, HttpStatus.OK);
}

}
