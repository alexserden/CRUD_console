package com.alexander;

import com.alexander.view.AccountsView;
import com.alexander.view.DevelopersView;
import com.alexander.view.SkillsView;

import java.io.IOException;

public class ChekInputParametr {
    DevelopersView developersView = new DevelopersView();
//    AccountsView accountsView = new AccountsView();
//    SkillsView skillsView = new SkillsView();

    public ChekInputParametr() throws IOException {
    }

    public void showAll(String[] args) throws IOException {
        switch (args[0]) {
            case "showAll":
              //  showAll.getAll();
                developersView.show();




        }
    }
}