package com.alexander.repository.impl;

import com.alexander.model.Account;
import com.alexander.repository.AccountRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JavaIOAccountRepositoryImpl implements AccountRepository {
    BufferedReader bufferedReader;
    private Long countId  = 0L;
    List<Account> accounts;

    public JavaIOAccountRepositoryImpl() throws IOException {
        accounts = new ArrayList<>();
        bufferedReader = Files.newBufferedReader(Paths.get("src/main/resource/accounts.txt"));
    }

    @Override
    public Account create(Account account) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Account getById(Long id) throws IOException {

        for (Account a : getAll()) {
            if (a.getId()==id) return a;
        }
        return null;
    }

    @Override
    public List<Account> getAll() throws IOException {


        while(bufferedReader.ready()){
            String account = bufferedReader.readLine();
            accounts.add(new Account(countId++,account));
        }
        return accounts;
    }

    @Override
    public void clearAll() {

    }
}
