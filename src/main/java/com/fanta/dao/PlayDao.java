package com.fanta.dao;

import com.fanta.entity.Entity;
import com.fanta.entity.PlayEntity;
import com.fanta.exception.DaoException;
import com.fanta.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayDao implements Dao {

    private static PlayDao playDao = new PlayDao();

    private PlayDao(){}

    public static PlayDao getInstance() {
        return playDao;
    }

    @Override
    public void saveNewEntity(Entity entity) {
        PlayEntity playEntity = (PlayEntity) entity;
        String sqlSave = "INSERT INTO Plays (Title, Author, Year_Written) VALUES (?, ?, ?)";

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlSave)){
            statement.setString(1, playEntity.getTitle());
            statement.setString(2, playEntity.getAuthor());
            statement.setInt(3, playEntity.getYearWritten());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateEntityById(int id, Entity entity) {
        PlayEntity playEntity = (PlayEntity) entity;
        String sqlUpdate = "UPDATE Plays SET Title = ?, Author = ?, Year_Written = ? WHERE Id = ?";

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlUpdate)){
            statement.setString(1, playEntity.getTitle());
            statement.setString(2, playEntity.getAuthor());
            statement.setInt(3, playEntity.getYearWritten());
            statement.setInt(4, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteEntity(int id) {
        String sqlDelete = "DELETE FROM Plays WHERE Id = ?";
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlDelete)){
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PlayEntity findById(int id) {
        String sqlFindById = "SELECT Id, Title, Author, Year_Written FROM Plays WHERE Id = ?";

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlFindById)){
            statement.setInt(1, id);
            ResultSet data = statement.executeQuery();

            if(data.next()){
                return PlayEntity
                        .builder()
                        .id(data.getInt("Id"))
                        .title(data.getString("Title"))
                        .author(data.getString("Author"))
                        .yearWritten(data.getInt("Year_Written"))
                        .build();
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<PlayEntity> findAll() {
        String sqlFindAll = "SELECT Id, Title, Author, Year_Written FROM Plays";
        List<PlayEntity> playEntities = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlFindAll)){
            ResultSet data = statement.executeQuery();

            while (data.next()){
                playEntities.add(PlayEntity
                        .builder()
                        .id(data.getInt("Id"))
                        .title(data.getString("Title"))
                        .author(data.getString("Author"))
                        .yearWritten(data.getInt("Year_Written"))
                        .build()
                );
            }

            return playEntities;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
