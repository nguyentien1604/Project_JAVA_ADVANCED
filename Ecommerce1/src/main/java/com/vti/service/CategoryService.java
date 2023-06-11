/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.vti.service;

import com.vti.Repository.ICategoryRepository;
import com.vti.entity.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        List<Category>listCategories=categoryRepository.findAll();
        return listCategories;
    }
    

}
