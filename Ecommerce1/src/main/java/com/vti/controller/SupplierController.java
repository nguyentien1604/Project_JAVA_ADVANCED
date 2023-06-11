/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.vti.controller;

import com.vti.dto.SupplierDto;
import com.vti.entity.Supplier;
import com.vti.service.ISupplierService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowire;
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
@RequestMapping("/suppliers")
@CrossOrigin("*")
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;
    @GetMapping()
    public ResponseEntity<?>getAllSupplier()
    {
        List<Supplier> supplierlist=supplierService.getAllSupplier();
        List<SupplierDto> listSupplierDto=new ArrayList<>();
        for(Supplier sp: supplierlist)
        {
            SupplierDto supplierDto=new SupplierDto();
            supplierDto.setSupplierId(sp.getSupplierId());
            supplierDto.setSupplierName(sp.getSupplierName());
            listSupplierDto.add(supplierDto);
        }
        return new ResponseEntity<>(listSupplierDto,HttpStatus.OK);
    }
            
}
