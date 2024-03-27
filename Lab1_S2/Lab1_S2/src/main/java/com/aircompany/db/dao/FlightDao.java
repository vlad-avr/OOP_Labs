package com.aircompany.db.dao;

import com.aircompany.db.entity.Brigade;
import com.aircompany.db.entity.Entity;
import com.aircompany.db.entity.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FlightDao extends EntityDao{
    public FlightDao(Connection connection) {
        super(connection, DaoManager.FLIGHTS_TABLE);
    }

    public void create(Flight entity) throws Exception{
        PreparedStatement statement = connection.prepareStatement("INSERT INTO " + table
                + " (race_id, brigade_id, plane_id, id) VALUES ("
                + entity.getRaceId() + ", "
                + entity.getBrigadeId() + ", "
                + entity.getPlaneId() + ", "
                + entity.getId().toString() + ")");
        statement.executeUpdate();
    }

    public Entity read(UUID id) throws Exception{
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE id = " + id.toString());
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            Flight entity = new Flight(UUID.fromString(resultSet.getString("id")));
            entity.setRaceId(UUID.fromString(resultSet.getString("race_id")));
            entity.setBrigadeId(UUID.fromString(resultSet.getString("brigade_id")));
            entity.setPlaneId(UUID.fromString(resultSet.getString("plane_id")));
            return entity;
        }
        return null;
    }

    public void update(Flight entity) throws Exception{
        PreparedStatement statement = connection.prepareStatement("UPDATE " + table
                + " SET race_id = " + entity.getRaceId()
                + ", brigade_id = " + entity.getBrigadeId()
                + ", plane_id = " + entity.getPlaneId()
                + " WHERE id = " + entity.getId().toString());
        statement.executeUpdate();
    }

    public void delete(UUID id) throws Exception{
        PreparedStatement statement = connection.prepareStatement("DELETE FROM " + table + " WHERE id = " + id.toString());
        statement.executeUpdate();
    }

    public List<Entity> readAll() throws Exception{
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table);
        ResultSet resultSet = statement.executeQuery();
        List<Entity> entities = new ArrayList<>();
        while (resultSet.next()){
            Flight entity = new Flight(UUID.fromString(resultSet.getString("id")));
            entity.setRaceId(UUID.fromString(resultSet.getString("race_id")));
            entity.setBrigadeId(UUID.fromString(resultSet.getString("brigade_id")));
            entity.setPlaneId(UUID.fromString(resultSet.getString("plane_id")));
            entities.add(entity);
        }
        return entities;
    }
}
