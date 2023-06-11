/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.vti.controller;

import com.vti.entity.Account;
import com.vti.service.IAccountService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */


/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@RestController
@RequestMapping(value = "/login")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private IAccountService accountService;

    @GetMapping()
    public ResponseEntity<?> login(Principal principal) {
        String username = principal.getName();
        Account entity = accountService.getAccountByUsername(username);


       	return new ResponseEntity<>("Login OK", HttpStatus.OK);
    }

}

