package com.fanta.dao;

import com.fanta.entity.Entity;
import com.fanta.entity.RoleEntity;
import com.fanta.exception.DaoException;
import com.fanta.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDao implements Dao {

    private static RoleDao roleDao = new RoleDao();

    private RoleDao(){}

    public static RoleDao getInstance() {
        return roleDao;
    }

    @Override
    public void saveNewEntity(Entity entity) {
        RoleEntity roleEntity = (RoleEntity) entity;
        String sqlSave = "INSERT INTO Roles (Actor_Id, Performance_Id, Role_Name) VALUES (?, ?, ?)";

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlSave)){
            statement.setInt(1, roleEntity.getActor().getId());
            statement.setInt(2, roleEntity.getPerformance().getId());
            statement.setString(3, roleEntity.getRoleName());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateEntityById(int id, Entity entity) {
        RoleEntity roleEntity = (RoleEntity) entity;
        String sqlUpdate = "UPDATE Roles SET Actor_Id = ?, Performance_Id = ?, Role_Name = ? WHERE Id = ?";

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlUpdate)){
            statement.setInt(1, roleEntity.getActor().getId());
            statement.setInt(2, roleEntity.getPerformance().getId());
            statement.setString(3, roleEntity.getRoleName());
            statement.setInt(4, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteEntity(int id) {
        String sqlDelete = "DELETE FROM Roles WHERE Id = ?";
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlDelete)){
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public RoleEntity findById(int id) {
        String sqlFindById = "SELECT Actor_Id, Performance_Id, Role_Name FROM Roles WHERE Id = ?";

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlFindById)){
            statement.setInt(1, id);
            ResultSet data = statement.executeQuery();

            if(data.next()){
                return RoleEntity
                        .builder()
                        .actor( ActorDao.getInstance().findById( data.getInt("Actor_Id") ) )
                        .performance( PerformanceDao.getInstance().findById( data.getInt("Performance_Id") ) )
                        .roleName(data.getString("Role_Name"))
                        .build();
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<RoleEntity> findAll() {
        String sqlFindAll = "SELECT Actor_Id, Performance_Id, Role_Name FROM Roles";
        List<RoleEntity> roleEntities = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlFindAll)){
            ResultSet data = statement.executeQuery();

            while (data.next()){
                roleEntities.add(RoleEntity
                        .builder()
                        .actor( ActorDao.getInstance().findById( data.getInt("Actor_Id") ) )
                        .performance( PerformanceDao.getInstance().findById( data.getInt("Performance_Id") ) )
                        .roleName(data.getString("Role_Name"))
                        .build()
                );
            }

            return roleEntities;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
