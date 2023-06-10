package com.fanta.dao;

import com.fanta.entity.Entity;
import com.fanta.entity.PerformanceEntity;
import com.fanta.exception.DaoException;
import com.fanta.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PerformanceDao implements Dao {

    private static PerformanceDao performanceDao = new PerformanceDao();

    private PerformanceDao(){}

    public static PerformanceDao getInstance() {
        return performanceDao;
    }

    @Override
    public void saveNewEntity(Entity entity) {
        PerformanceEntity performanceEntity = (PerformanceEntity) entity;
        String sqlSave = "INSERT INTO Performances (Play_Id, Performance_Date) VALUES (?, ?)";

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlSave)){
            statement.setInt(1, performanceEntity.getPlay().getId());
            statement.setObject(2, performanceEntity.getDateTime());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateEntityById(int id, Entity entity) {
        PerformanceEntity performanceEntity = (PerformanceEntity) entity;
        String sqlUpdate = "UPDATE Performances SET Play_Id = ?, Performance_Date = ? WHERE Id = ?";

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlUpdate)){
            statement.setInt(1, performanceEntity.getPlay().getId());
            statement.setObject(2, performanceEntity.getDateTime());
            statement.setInt(3, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteEntity(int id) {
        String sqlDelete = "DELETE FROM Performances WHERE Id = ?";
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlDelete)){
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PerformanceEntity findById(int id) {
        String sqlFindById = "SELECT Id, Play_Id, Performance_Date FROM Performances WHERE Id = ?";

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlFindById)){
            statement.setInt(1, id);
            ResultSet data = statement.executeQuery();

            if(data.next()){
                return PerformanceEntity
                        .builder()
                        .id(data.getInt("Id"))
                        .play( PlayDao.getInstance().findById( data.getInt("Play_Id") ) )
                        .dateTime( data.getObject("Performance_Date", LocalDateTime.class) )
                        .build();
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PerformanceEntity> findAll() {
        String sqlFindAll = "SELECT Id, Play_Id, Performance_Date FROM Performances";
        List<PerformanceEntity> performanceEntities = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlFindAll)){
            ResultSet data = statement.executeQuery();

            while (data.next()){
                performanceEntities.add(PerformanceEntity
                        .builder()
                        .id(data.getInt("Id"))
                        .play( PlayDao.getInstance().findById( data.getInt("Play_Id") ) )
                        .dateTime(data.getObject("Performance_Date", LocalDateTime.class))
                        .build()
                );
            }

            return performanceEntities;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
