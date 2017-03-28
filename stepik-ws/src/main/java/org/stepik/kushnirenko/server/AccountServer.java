package org.stepik.kushnirenko.server;

public interface AccountServer {

    void addNewUser();

    void removeUser();

    int getUsersLimit();

    void setUsersLimit(int val);

    int getUsersCount();
}
