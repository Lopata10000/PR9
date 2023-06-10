package com.fanta.services;

import com.fanta.dao.ActorDao;
import com.fanta.entity.ActorEntity;

import java.util.List;

public final class ActorService {

    private static ActorService actorService = new ActorService();
    public ActorService(){}

    public static ActorService getInstance() {
        return actorService;
    }

    public void addNewActor(ActorEntity actorEntity) {
        ActorDao.getInstance().saveNewEntity(actorEntity);
    }

    public void updateActorById(int id, ActorEntity actorEntity){
        ActorDao.getInstance().updateEntityById(id, actorEntity);
    }

    public void deleteActorById(int id){
        ActorDao.getInstance().deleteEntity(id);
    }

    public ActorEntity getActorById(int id){
        return ActorDao.getInstance().findById(id);
    }

    public List<ActorEntity> getAllActors(){
        return ActorDao.getInstance().findAll();
    }
}
