package com.fanta.dao;

import com.fanta.entity.Entity;

import java.util.List;

public interface Dao {

    void saveNewEntity(Entity entity);
    void updateEntityById(int id, Entity entity);
    void deleteEntity(int id);
    <T extends Entity> T findById(int id);
    List<? extends Entity> findAll();

}
