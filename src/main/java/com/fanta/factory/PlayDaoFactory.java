package com.fanta.factory;

import com.fanta.dao.PlayDao;
import com.fanta.dao.RoleDao;

public class PlayDaoFactory {
    public static PlayDao createPlayDao() {
        return PlayDao.getInstance();
    }
}
