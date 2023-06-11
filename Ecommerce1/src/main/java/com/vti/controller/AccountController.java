/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vti.controller;

import com.vti.dto.AccountDto;
import com.vti.entity.Account;
import com.vti.form.AccountForFormCreating;
import com.vti.form.AccountFormForUpdate;
import com.vti.service.IAccountService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Controller
@RequestMapping(value = "/accounts")
@CrossOrigin("*")

public class AccountController {

    @Autowired
    private IAccountService accountService;

//    private short customerID;
//    private String username;
//    private String password;
//    private String fullname;
//    private String email;
//    private String phone;
//    private String address;
//    private Date createDate;
//    private String city;
//    private String country;
    @GetMapping()
    public ResponseEntity<?> getAllAccount() {
        List<Account> listAccount = accountService.getAllAccount();
        List<AccountDto> accountListDto = listAccount.stream()
                .map(account -> {
                    AccountDto accountDto = new AccountDto();
                    accountDto.setId(account.getId());
                    accountDto.setUsername(account.getUsername());
                    accountDto.setPassword(account.getPassword());
                    accountDto.setFullname(account.getFullname());
                    accountDto.setEmail(account.getEmail());
                    accountDto.setPhone(account.getPhone());
                    accountDto.setAddress(account.getAddress());
                    accountDto.setCreateDate(account.getCreateDate());
                    accountDto.setCity(account.getCity());
                    accountDto.setCountry(account.getCountry());

                    // Sao chép các thuộc tính khác của Account vào AccountDto
                    return accountDto;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(accountListDto, HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAccountByID(@PathVariable(name = "id") short id) {
        Account account = accountService.getAccountByID(id);
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setUsername(account.getUsername());
        accountDto.setPassword(account.getPassword());
        accountDto.setFullname(account.getFullname());
        accountDto.setEmail(account.getEmail());
        accountDto.setPhone(account.getPhone());
        accountDto.setAddress(account.getAddress());
        accountDto.setCreateDate(account.getCreateDate());
        accountDto.setCity(account.getCity());
        accountDto.setCountry(account.getCountry());
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> creatNewAccount(@RequestBody AccountForFormCreating accountForFormCreating) {

        Account account = accountService.creatNewAccount(accountForFormCreating);

        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setUsername(account.getUsername());
        accountDto.setPassword(account.getPassword());
        accountDto.setFullname(account.getFullname());
        accountDto.setEmail(account.getEmail());
        accountDto.setPhone(account.getPhone());
        accountDto.setAddress(account.getAddress());
        accountDto.setCreateDate(account.getCreateDate());
        accountDto.setCity(account.getCity());
        accountDto.setCountry(account.getCountry());
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable(name = "id") short id,
            @RequestBody AccountFormForUpdate accountFormForUpdate) {
        Account account = accountService.updateAccount(id, accountFormForUpdate);

        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setUsername(account.getUsername());
        accountDto.setPassword(account.getPassword());
        accountDto.setFullname(account.getFullname());
        accountDto.setEmail(account.getEmail());
        accountDto.setPhone(account.getPhone());
        accountDto.setAddress(account.getAddress());
        accountDto.setCreateDate(account.getCreateDate());
        accountDto.setCity(account.getCity());
        accountDto.setCountry(account.getCountry());
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }
    
     @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAccountById(@PathVariable(name = "id")short id)
    {
        accountService.deleteAccountById(id);
        return new ResponseEntity<>("Delete Sucess",HttpStatus.OK);
       
    }
}
