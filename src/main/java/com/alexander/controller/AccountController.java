package com.alexander.controller;

import com.alexander.model.Account;
import com.alexander.repository.AccountRepository;
import com.alexander.repository.impl.JavaIOAccountRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class AccountController {
    AccountRepository  accountRepository;

    public AccountController() throws IOException {
        accountRepository = new JavaIOAccountRepositoryImpl();
    }

    public Account create(Account account) {
        return accountRepository.create(account);
    }


    public void delete(Long id) {
        accountRepository.delete(id);
    }


    public Account getById(Long id) throws IOException {
        return accountRepository.getById(id);
    }


    public List<Account> getAll() throws IOException {
        return accountRepository.getAll();
    }


    public void clearAll() {
        accountRepository.clearAll();
    }
}
