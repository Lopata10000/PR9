package com.fanta.factory;

import com.fanta.dao.PerformanceDao;

public class PerformanceDaoFactory {
    public static PerformanceDao createPerformanceDao() {
        return PerformanceDao.getInstance();
    }
}
