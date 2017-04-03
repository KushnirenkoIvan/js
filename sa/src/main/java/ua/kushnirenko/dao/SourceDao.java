package ua.kushnirenko.dao;

import ua.kushnirenko.entity.SourceEntity;

import java.util.List;


public interface SourceDao {

    long create(SourceEntity sr);

    SourceEntity read(long id);

    void update(SourceEntity sr);

    SourceEntity delete(long id);

    List<SourceEntity> getAll();

    List<SourceEntity> getPlatformStatistics();
}
