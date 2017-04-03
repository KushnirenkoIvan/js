package ua.kushnirenko.service;

import ua.kushnirenko.entity.SourceEntity;

import java.util.List;

public interface SourceService {

    SourceEntity addEntity(SourceEntity sr);

    SourceEntity getEntity(Long id);

    void updateEntity(SourceEntity sr);

    SourceEntity deleteEntity(Long id);

    List<SourceEntity> getAll();

    List getPlatformStatistics();
}
