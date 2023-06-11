/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vti.service;

import com.vti.entity.Account;
import com.vti.form.AccountForFormCreating;
import com.vti.form.AccountFormForLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vti.Repository.IAccountRepository;
import com.vti.form.AccountFormForUpdate;
import com.vti.form.AccountRegisterFormForCreating;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;
     @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public List<Account> getAllAccount() {
        List<Account> listAccounts = accountRepository.findAll();
        return listAccounts;
    }

    @Override
    public Account getAccountByID(short id) {

        return accountRepository.findById(id).get();
    }

    @Override
    public Account creatNewAccount(AccountForFormCreating accountForFormCreating) {
         Account account = new Account();
      
        account.setUsername(accountForFormCreating.getUsername());
        account.setPassword(accountForFormCreating.getPassword());
        account.setFullname(accountForFormCreating.getFullname());
        account.setEmail(accountForFormCreating.getEmail());
        account.setPhone(accountForFormCreating.getPhone());
        account.setAddress(accountForFormCreating.getAddress());
        account.setCreateDate(accountForFormCreating.getCreateDate());
        account.setCity(accountForFormCreating.getCity());
        account.setCountry(accountForFormCreating.getCountry());
        
        Account accountNewAccount=accountRepository.save(account);
        return accountNewAccount;
    }

    @Override
    public Account updateAccount(short id, AccountFormForUpdate accountFormForUpdate) {
         Account account = accountRepository.getReferenceById(id);
      
        account.setUsername(accountFormForUpdate.getUsername());
        account.setPassword(accountFormForUpdate.getPassword());
        account.setFullname(accountFormForUpdate.getFullname());
        account.setEmail(accountFormForUpdate.getEmail());
        account.setPhone(accountFormForUpdate.getPhone());
        account.setAddress(accountFormForUpdate.getAddress());
        account.setCreateDate(accountFormForUpdate.getCreateDate());
        account.setCity(accountFormForUpdate.getCity());
        account.setCountry(accountFormForUpdate.getCountry());
        
        Account accountNewAccount=accountRepository.save(account);
        return accountNewAccount;
    }

    @Override
    public void deleteAccountById(short id) {
          accountRepository.deleteById(id);
    }

    @Override
    public void createAccountRegister(AccountRegisterFormForCreating accountRegisterFormForCreating) {
        Account account = new Account();
      
        account.setUsername(accountRegisterFormForCreating.getUsername());
        
        account.setFullname(accountRegisterFormForCreating.getFullname());
        account.setEmail(accountRegisterFormForCreating.getEmail());
        account.setPhone(accountRegisterFormForCreating.getPhone());
        account.setAddress(accountRegisterFormForCreating.getAddress());
        
        account.setCity(accountRegisterFormForCreating.getCity());
        account.setCountry(accountRegisterFormForCreating.getCountry());
        account.setPassword(passwordEncoder.encode(accountRegisterFormForCreating.getPassword()));

        
        accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if(account==null)
        {
            throw new  UsernameNotFoundException(username);
        }
        UserDetails userDetails = new User(account.getUsername(), account.getPassword(),
                AuthorityUtils.createAuthorityList("user"));
        return userDetails;
    }

    @Override
    public Account getAccountByUsername(String username) {
       return accountRepository.findByUsername( username);
    }

   
   
    

}
