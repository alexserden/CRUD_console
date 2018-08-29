package com.alexander.view;


import com.alexander.controller.AccountController;
import com.alexander.model.Account;

import java.io.IOException;

public class AccountsView {
    AccountController accountController = new AccountController();

    public AccountsView() throws IOException {
    }
    public void showAccounts() throws IOException {
        System.out.println("------------accounts------------");

        for (Account account : accountController.getAll()) {
            System.out.printf("%16s%n",account.getAccount());
        }
    }
}
