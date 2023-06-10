package com.fanta.services;

import com.fanta.dao.RoleDao;
import com.fanta.entity.RoleEntity;

import java.util.List;

public final class RoleService {
    private static RoleService roleService = new RoleService();
    private RoleService(){}

    public static RoleService getInstance() {
        return roleService;
    }

    public void addNewRole(RoleEntity roleEntity) {
        RoleDao.getInstance().saveNewEntity(roleEntity);
    }

    public void updateRoleById(int id, RoleEntity roleEntity){
        RoleDao.getInstance().updateEntityById(id, roleEntity);
    }

    public void deleteRoleById(int id){
        RoleDao.getInstance().deleteEntity(id);
    }

    public RoleEntity getRoleById(int id){
        return RoleDao.getInstance().findById(id);
    }

    public List<RoleEntity> getAllRoles(){
        return RoleDao.getInstance().findAll();
    }
}
