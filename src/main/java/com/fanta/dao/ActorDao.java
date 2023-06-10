package com.fanta.dao;

import com.fanta.entity.ActorEntity;
import com.fanta.entity.Entity;
import com.fanta.exception.DaoException;
import com.fanta.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActorDao implements Dao {

    private static ActorDao actorDao = new ActorDao();

    private ActorDao(){}

    public static ActorDao getInstance() {
        return actorDao;
    }

    @Override
    public void saveNewEntity(Entity entity) {
        ActorEntity actorEntity = (ActorEntity) entity;
        String sqlSave = "INSERT INTO Actors (First_Name, Last_Name, Email, Password, Birth_Date) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlSave)){
            statement.setString(1, actorEntity.getFirstName());
            statement.setString(2, actorEntity.getLastName());
            statement.setString(3, actorEntity.getEmail());
            statement.setString(4, actorEntity.getPassword());
            statement.setObject(5, actorEntity.getBirthDate());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateEntityById(int id, Entity entity) {
        ActorEntity actorEntity = (ActorEntity) entity;
        String sqlUpdate = "UPDATE Actors SET First_Name = ?, Last_Name = ?, Email = ?, Password = ?, Birth_Date = ? WHERE Id = ?";

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlUpdate)){
            statement.setString(1, actorEntity.getFirstName());
            statement.setString(2, actorEntity.getLastName());
            statement.setString(3, actorEntity.getEmail());
            statement.setString(4, actorEntity.getPassword());
            statement.setObject(5, actorEntity.getBirthDate());
            statement.setInt(6, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteEntity(int id) {
        String sqlDelete = "DELETE FROM Actors WHERE Id = ?";
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlDelete)){
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public ActorEntity findById(int id) {
        String sqlFindById = "SELECT Id, First_Name, Last_Name, Email, Password, Birth_Date FROM Actors WHERE Id = ?";

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlFindById)){
            statement.setInt(1, id);
            ResultSet data = statement.executeQuery();

            if(data.next()){
                return ActorEntity
                        .builder()
                        .id(data.getInt("Id"))
                        .firstName(data.getString("First_Name"))
                        .lastName(data.getString("Last_Name"))
                        .email(data.getString("Email"))
                        .password(data.getString("Password"))
                        .birthDate(data.getObject("Birth_Date", LocalDate.class))
                        .build();
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<ActorEntity> findAll() {
        String sqlFindAll = "SELECT Id, First_Name, Last_Name, Email, Password, Birth_Date FROM Actors";
        List<ActorEntity> actorEntities = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlFindAll)){
            ResultSet data = statement.executeQuery();

            while (data.next()){
                actorEntities.add(ActorEntity
                        .builder()
                        .id(data.getInt("Id"))
                        .firstName(data.getString("First_Name"))
                        .lastName(data.getString("Last_Name"))
                        .email(data.getString("Email"))
                        .password(data.getString("Password"))
                        .birthDate(data.getObject("Birth_Date", LocalDate.class))
                        .build()
                );
            }

            return actorEntities;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
