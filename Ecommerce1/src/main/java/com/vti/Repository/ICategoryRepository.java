/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vti.Repository;

import com.vti.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface ICategoryRepository  extends JpaRepository<Category, Short>{

    
    
}
