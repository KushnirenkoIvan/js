package org.stepik.kushnirenko.service;

import org.stepik.kushnirenko.dao.UserProfileDao;
import org.stepik.kushnirenko.dao.UserProfileDaoImpl;
import org.stepik.kushnirenko.domain.UserProfile;
import org.stepik.kushnirenko.exception.InvalidUserProfileException;

import java.util.HashMap;
import java.util.Map;


public class AccountService {

    private static Map<String, UserProfile> session_map = new HashMap<>(10);

    private UserProfileDao dao = new UserProfileDaoImpl();

    public UserProfile signUpUser(UserProfile user) throws InvalidUserProfileException {
        if (user == null || user.getLogin().isEmpty() || user.getPassword().isEmpty())
            throw new InvalidUserProfileException("User login and password cannot be null!");

        dao.create(user);

        return user;
    }

    public UserProfile getUserByLogin(String login) {
        if (login == null || login.isEmpty()) throw new IllegalArgumentException();

        return dao.readByLogin(login);
    }

    public UserProfile addSession(String sessionId, UserProfile user) {
        if (sessionId == null || sessionId.isEmpty() || user == null) throw new IllegalArgumentException();

        synchronized (this) {
            return session_map.put(sessionId, user);
        }
    }

    public UserProfile getUserBySessionId(String sessionId) {
        if (sessionId == null || sessionId.isEmpty()) throw new IllegalArgumentException();

        return session_map.get(sessionId);
    }

    public UserProfile deleteSession(String sessionId) {
        if (sessionId == null || sessionId.isEmpty()) throw new IllegalArgumentException();
        synchronized (this) {
            return session_map.remove(sessionId);
        }
    }
}

