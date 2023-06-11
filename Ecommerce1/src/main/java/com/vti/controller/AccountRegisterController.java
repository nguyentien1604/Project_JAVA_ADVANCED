/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.vti.controller;

import com.vti.entity.Account;
import com.vti.form.AccountRegisterFormForCreating;
import com.vti.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@RestController
@RequestMapping(value = "/accountRegisters")
@CrossOrigin("*")
public class AccountRegisterController {
@Autowired
	private IAccountService accountService;

	@PostMapping()
	public ResponseEntity<?> createAccount(@RequestBody AccountRegisterFormForCreating accountRegisterFormForCreating) {
		accountService.createAccountRegister(accountRegisterFormForCreating);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.CREATED);
	}

}

