package com.alexander;

import com.alexander.view.AccountsView;
import com.alexander.view.DevelopersView;
import com.alexander.view.SkillsView;

import java.io.IOException;

public class ChekInputParametr {
    DevelopersView developersView = new DevelopersView();
    AccountsView accountsView = new AccountsView();
    SkillsView skillsView = new SkillsView();

    public ChekInputParametr() throws IOException {
    }

    public void showAll(String[] args) throws IOException {
        switch (args[0]) {
            case "showAll":
                developersView.showAll();
            case "showAccounts":
                accountsView.showAccounts();
            case "showSkills":
                skillsView.showSkills();
        }
        switch (args[1]) {
            case "delete":
                developersView.delete();
                developersView.showAll();
            case "update":
                developersView.updateDeveloper();
                developersView.showAll();
            case "add":
                developersView.addDeveloper();
                developersView.showAll();
        }

    }
}
