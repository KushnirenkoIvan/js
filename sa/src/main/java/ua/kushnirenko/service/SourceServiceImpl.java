package ua.kushnirenko.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kushnirenko.dao.SourceDao;
import ua.kushnirenko.entity.SourceEntity;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;


@Service
@Transactional
public class SourceServiceImpl implements SourceService {

    public static Logger log = Logger.getLogger(SourceServiceImpl.class);

    @Autowired
    private SourceDao dao;

    public SourceEntity addEntity(@NotNull SourceEntity sr) {
        sr.setId(dao.create(sr));
        log.info("New entity: \n\t" + sr + "\n  - is created.");
        return sr;
    }

    public SourceEntity getEntity(@NotNull Long id) {
        SourceEntity sr = dao.read(id);
        log.info("Entity: \n\t" + sr + "\n  - is obtained from db.");
        return sr;
    }

    @Override
    public void updateEntity(@NotNull SourceEntity sr) {
        dao.update(sr);
        log.info("Entity: \n\t" + sr + "\n  - is updated.");
    }

    @Override
    public SourceEntity deleteEntity(@NotNull Long id) {
        SourceEntity sr = dao.delete(id);
        log.info("Entity: \n\t" + sr + "\n  - is deleted from db.");
        return sr;
    }

    @Override
    public List<SourceEntity> getAll() {
        List<SourceEntity> sourceEntity_lst = dao.getAll();
        log.info("All entities: \n" + sourceEntity_lst + "\n\t is obtained from db.");
        return sourceEntity_lst;
    }

    @Override
    public List getPlatformStatistics() {
        List<SourceEntity> sourceEntity_lst = dao.getAll();
        log.info("Platform statistics is obtained from db.");
        return sourceEntity_lst;
    }
}
