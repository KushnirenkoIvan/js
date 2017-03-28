package org.stepik.kushnirenko.server;

public class AccountServerImpl implements AccountServer {

    public static final int DEFAULT_LIMIT = 10;

    private int usersLimit;
    private int usersCount;

    public AccountServerImpl() {
        this.usersCount = 0;
        this.usersLimit = DEFAULT_LIMIT;
    }

    public AccountServerImpl(int usersLimit) {
        this.usersCount = 0;
        this.usersLimit = usersLimit;
    }

    @Override
    public void addNewUser() {
        usersCount += 1;
    }

    @Override
    public void removeUser() {
        usersCount -= 1;
    }

    @Override
    public int getUsersLimit() {
        return usersLimit;
    }

    @Override
    public void setUsersLimit(int val) {
        this.usersLimit = val;
    }

    @Override
    public int getUsersCount() {
        return usersCount;
    }
}
