/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vti.Repository;

import com.vti.entity.Account;
import com.vti.form.AccountFormForLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@EnableJpaRepositories
@Repository
public interface IAccountRepository extends JpaRepository<Account, Short> {
    public Account findByUsernameAndPassword(String username, String password);

//     public Account getAccountById(short id);
    
    public Account findByUsername(String username);
}

