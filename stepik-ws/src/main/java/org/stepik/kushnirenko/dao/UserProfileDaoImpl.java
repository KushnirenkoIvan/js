package org.stepik.kushnirenko.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.stepik.kushnirenko.config.HibernateConfig;
import org.stepik.kushnirenko.domain.UserProfile;


public class UserProfileDaoImpl implements UserProfileDao {

    private SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

    @Override
    public long create(UserProfile up) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        long id = (long) session.save(up);
        trx.commit();
        session.close();
        return id;
    }

    @Override
    public UserProfile read(long id) {
        Session session = sessionFactory.openSession();
        UserProfile up = (UserProfile) session.load(UserProfile.class, id);
        session.close();
        return up;
    }

    @Override
    public UserProfile readByLogin(String login) {
        Session session = sessionFactory.openSession();
        Criteria cr = session.createCriteria(UserProfile.class);
        UserProfile up = (UserProfile) cr.add(Restrictions.eq("login", login)).uniqueResult();
        return up;
    }

    @Override
    public boolean update(UserProfile up) {
        return true;
    }

    @Override
    public boolean delete(long id) {
        return true;
    }
}
