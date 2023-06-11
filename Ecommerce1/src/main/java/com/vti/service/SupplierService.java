/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.vti.service;

import com.vti.Repository.ISupplierRepository;
import com.vti.entity.Supplier;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Service
public class SupplierService implements ISupplierService{
    
    @Autowired ISupplierRepository supplierrepository;
    
    @Override
    public List<Supplier> getAllSupplier() {
       List<Supplier> listSuppliers=supplierrepository.findAll();
       return listSuppliers;
    }

}
