package com.fanta.services;

import com.fanta.dao.PerformanceDao;
import com.fanta.entity.PerformanceEntity;

import java.util.List;

public final class PerformanceService {
    private static PerformanceService performanceService = new PerformanceService();
    private PerformanceService(){}

    public static PerformanceService getInstance() {
        return performanceService;
    }

    public void addNewPerformance(PerformanceEntity performanceEntity) {
        PerformanceDao.getInstance().saveNewEntity(performanceEntity);
    }

    public void updatePerformanceById(int id, PerformanceEntity performanceEntity){
        PerformanceDao.getInstance().updateEntityById(id, performanceEntity);
    }

    public void deletePerformanceById(int id){
        PerformanceDao.getInstance().deleteEntity(id);
    }

    public PerformanceEntity getPerformanceById(int id){
        return PerformanceDao.getInstance().findById(id);
    }

    public List<PerformanceEntity> getAllPerformances(){
        return PerformanceDao.getInstance().findAll();
    }
}
