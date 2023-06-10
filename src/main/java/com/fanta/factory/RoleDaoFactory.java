package com.fanta.factory;

import com.fanta.dao.RoleDao;

public class RoleDaoFactory {
    public static RoleDao createRoleDao() {
        return RoleDao.getInstance();
    }
}
