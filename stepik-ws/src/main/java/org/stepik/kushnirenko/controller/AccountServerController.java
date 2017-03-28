package org.stepik.kushnirenko.controller;

import org.stepik.kushnirenko.server.AccountServer;


public class AccountServerController implements AccountServerControllerMBean {

    private final AccountServer accountServer;

    public AccountServerController(AccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    public int getUsers() {
        return accountServer.getUsersCount();
    }

    @Override
    public int getUsersLimit() {
        return accountServer.getUsersLimit();
    }

    @Override
    public void setUsersLimit(int val) {
        accountServer.setUsersLimit(val);
    }
}
