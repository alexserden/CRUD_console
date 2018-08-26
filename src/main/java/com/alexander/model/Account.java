package com.alexander.model;

public class Account extends General_Id {
    private String account;

    public Account(Long id,String account) {
        super(id);
        this.account = account;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
