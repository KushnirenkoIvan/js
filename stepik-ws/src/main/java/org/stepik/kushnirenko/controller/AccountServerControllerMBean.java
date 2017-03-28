package org.stepik.kushnirenko.controller;

public interface AccountServerControllerMBean {

    int getUsers();

    int getUsersLimit();

    void setUsersLimit(int val);

}
