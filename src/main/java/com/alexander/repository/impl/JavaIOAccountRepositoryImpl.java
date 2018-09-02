package com.alexander.repository.impl;

import com.alexander.model.Account;
import com.alexander.repository.AccountRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class JavaIOAccountRepositoryImpl implements AccountRepository {
    Path paths = Paths.get("src/main/resource/accounts.txt");
    private static Long countId = 0L;

    public JavaIOAccountRepositoryImpl() throws IOException {
        List<Account> addId = getAll();
        for (Account account : addId) {
            if (account.getId() > countId) {
                countId = account.getId();
            }
        }

    }



    @Override
    public void create(Account account) throws IOException {

        String text = "\n"+ (++countId)+" "+ account.getAccount();
        Files.write(paths, text.getBytes(), StandardOpenOption.APPEND);
    }
    @Override
    public void update(Account account) throws IOException {
        List<Account> accounts = getAll();
        for (Account acc : accounts) {
            if (acc.getId().equals(account.getId())) {
                acc.setAccount(account.getAccount());

            }
        }
        BufferedWriter writer = Files.newBufferedWriter(paths);
        for (Account account1 : accounts) {
            writer.write(account1.getId()+" "+account1.getAccount());
            writer.newLine();
        }
        writer.close();
    }


    @Override
    public void delete(Long id) throws IOException {
        List<Account> newAccounts = new ArrayList<>();
        List<Account> accounts = getAll();
        BufferedWriter writer = Files.newBufferedWriter(paths);

        for (Account a : accounts) {
            if (a.getId() != id) newAccounts.add(a);
        }
        for (Account a : newAccounts) {
            writer.write(a.getId()+" "+a.getAccount());
            writer.newLine();
        }
        writer.close();
    }

    @Override
    public Account getById(Long id) throws IOException {
        List<Account> accounts = new ArrayList<>(getAll());
        for (Account a : accounts) {
            if (a.getId() == id) return a;
        }

        return null;
    }

    @Override
    public List<Account> getAll() throws IOException {
        BufferedReader bufferedReader = Files.newBufferedReader(paths);
        List<Account> accounts = new ArrayList<>();

        while (bufferedReader.ready()) {

            String[] account = bufferedReader.readLine().split(" ");

            accounts.add(new Account(Long.parseLong(account[0]), account[1]));
        }

        bufferedReader.close();
        return accounts;
    }

}
