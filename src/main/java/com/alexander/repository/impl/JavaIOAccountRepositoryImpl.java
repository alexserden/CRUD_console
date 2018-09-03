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
        List<String> list = new ArrayList<>();
        BufferedReader bufferedReader = Files.newBufferedReader(paths);

        while (bufferedReader.ready()) {
            list.add(bufferedReader.readLine());
        }
        bufferedReader.close();
        BufferedWriter writer = Files.newBufferedWriter(paths);

        for (String s : list) {
            if (Long.parseLong(s.split(" ")[0]) == account.getId()) {
                writer.write(account.getId()+" "+account.getAccount());
                writer.newLine();
            }else{
                writer.write(s.split(" ")[0]+" "+s.split(" ")[1]);
                writer.newLine();
            }
        }
        writer.close();
    }


    @Override
    public void delete(Long id) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader bufferedReader = Files.newBufferedReader(paths);
        while (bufferedReader.ready()) {
            list.add(bufferedReader.readLine());
        }
        bufferedReader.close();
        BufferedWriter writer = Files.newBufferedWriter(paths);

        for (String a : list) {
            if (Long.parseLong(a.split(" ")[0]) != id) {
                writer.write(a.split(" ")[0] + " " + a.split(" ")[1] + "");
                writer.newLine();
            }
            writer.close();
        }
    }

    @Override
    public Account getById(Long id) throws IOException {
        BufferedReader bufferedReader = Files.newBufferedReader(paths);
        List<String> list = new ArrayList<>();
        while (bufferedReader.ready()){
            list.add(bufferedReader.readLine());
        }
        bufferedReader.close();

        for(String s : list){
            if(Long.parseLong(s.split(" ")[0])==id){
                return new Account(id,s.split(" ")[1]);
            }
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
