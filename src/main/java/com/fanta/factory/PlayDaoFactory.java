package com.fanta.factory;

import com.fanta.dao.PlayDao;

public class PlayDaoFactory {
    public static PlayDao createPlayDao() {
        return PlayDao.getInstance();
    }
}
