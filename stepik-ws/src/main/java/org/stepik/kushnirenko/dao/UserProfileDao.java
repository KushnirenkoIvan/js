package org.stepik.kushnirenko.dao;

import org.stepik.kushnirenko.domain.UserProfile;


public interface UserProfileDao {

    long create(UserProfile up);

    UserProfile read(long id);

    UserProfile readByLogin(String login);

    boolean update(UserProfile up);

    boolean delete(long id);
}
