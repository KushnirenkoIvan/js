package ua.kushnirenko.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.kushnirenko.entity.SourceEntity;

import java.util.List;


@Repository
public class SourceDaoImpl implements SourceDao {

    @Autowired
    private SessionFactory sessionFactory;

    public long create(SourceEntity sr) {
        Session session = sessionFactory.openSession();
        long id = (Long) session.save(sr);
        session.close();
        return id;
    }

    public SourceEntity read(long id) {
        Session session = sessionFactory.openSession();
        SourceEntity sr = (SourceEntity) session.get(SourceEntity.class, id);
        session.close();
        return sr;
    }

    public void update(SourceEntity sr) {
        Session session = sessionFactory.openSession();
        session.update(sr);
        session.close();
    }

    public SourceEntity delete(long id) {
        Session session = sessionFactory.openSession();
        SourceEntity sr = (SourceEntity) session.get(SourceEntity.class, id);
        session.delete(sr);
        session.close();
        return sr;
    }

    @Override
    public List<SourceEntity> getAll() {
        Session session = sessionFactory.openSession();
        String hql = "FROM SourceEntity";
        Query query = session.createQuery(hql);
        return query.list();
    }

    @Override
    public List getPlatformStatistics() {
        Session session = sessionFactory.openSession();
        String hql = "SELECT S.renderingEngine, count (S.renderingEngine) FROM SourceEntity " +
                "S GROUP BY S.renderingEngine";
        Query query = session.createQuery(hql);
        return query.list();
    }
}
