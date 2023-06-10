package com.fanta.factory;

import com.fanta.dao.ActorDao;

public class ActorDaoFactory {
    public static ActorDao createActorDao() {
        return ActorDao.getInstance();
    }
}
