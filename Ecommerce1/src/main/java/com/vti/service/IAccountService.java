/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vti.service;

import com.vti.entity.Account;
import com.vti.form.AccountForFormCreating;
import com.vti.form.AccountFormForLogin;
import com.vti.form.AccountFormForUpdate;
import com.vti.form.AccountRegisterFormForCreating;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface IAccountService extends UserDetailsService {

//    public Account registerCustomer(AccountForFormCreating accountForFormCreating);
//
//    public Account loginCustomer(String username, String password);
    public List<Account> getAllAccount();

    public Account getAccountByID(short id);

    public Account creatNewAccount(AccountForFormCreating accountForFormCreating);

    public Account updateAccount(short id, AccountFormForUpdate accountFormForUpdate);

    public void deleteAccountById(short id);

    public void createAccountRegister(AccountRegisterFormForCreating accountRegisterFormForCreating);

    public Account getAccountByUsername(String username);

}
