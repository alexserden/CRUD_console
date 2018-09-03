package com.alexander;

import com.alexander.view.AccountsView;
import com.alexander.view.DevelopersView;
import com.alexander.view.SkillsView;

import java.io.IOException;

public class CallMethods {
    DevelopersView developersView = new DevelopersView();
    AccountsView accountsView = new AccountsView();
    SkillsView skillsView = new SkillsView();

    public CallMethods() throws IOException {
    }

    public void showAll(String args) throws IOException {
        switch (args) {
            case "showAll":
                developersView.showAll();
                break;
            case "showAccounts":
                accountsView.showAccounts();
                break;
            case "showSkills":
                skillsView.showSkills();
                break;
            case "delete":
                developersView.delete();

                developersView.showAll();
                break;
            case "update":
                developersView.updateDeveloper();
                developersView.showAll();
                break;
            case "add":
                developersView.addDeveloper();
                developersView.showAll();
                break;
        }




    }
}
