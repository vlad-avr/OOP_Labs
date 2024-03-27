package com.aircompany.db.dao;

import com.aircompany.db.entity.Brigade;
import com.aircompany.db.entity.Entity;
import com.aircompany.db.entity.Race;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RaceDao extends EntityDao{

    public RaceDao(Connection connection) {
        super(connection, DaoManager.RACES_TABLE);
    }
    public void create(Race entity) throws Exception{
        PreparedStatement statement = connection.prepareStatement("INSERT INTO " + table
                + " (departure_place, arrival_place, departure_time, arrival_time, passengers, luggage_weight, id) VALUES ("
                + entity.getDeparturePlace() + ", "
                + entity.getArrivalPlace() + ", "
                + entity.getDepartureTime() + ", "
                + entity.getArrivalTime() + ", "
                + entity.getPassengers() + ", "
                + entity.getLuggageWeight() + ", "
                + entity.getId().toString() + ")");
    }
    public Entity read(UUID id) throws Exception{
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE id = " + id.toString());
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            Race entity = new Race(UUID.fromString(resultSet.getString("id")));
            entity.setDeparturePlace(resultSet.getString("departure_place"));
            entity.setArrivalPlace(resultSet.getString("arrival_place"));
            entity.setDepartureTime(resultSet.getTimestamp("departure_time"));
            entity.setArrivalTime(resultSet.getTimestamp("arrival_time"));
            entity.setPassengers(resultSet.getInt("passengers"));
            entity.setLuggageWeight(resultSet.getDouble("luggage_weight"));
            return entity;
        }
        return null;
    }

    public void update(Race entity) throws Exception{
        PreparedStatement statement = connection.prepareStatement("UPDATE " + table
                + " SET departure_place = " + entity.getDeparturePlace()
                + ", arrival_place = " + entity.getArrivalPlace()
                + ", departure_time = " + entity.getDepartureTime()
                + ", arrival_time = " + entity.getArrivalTime()
                + ", passengers = " + entity.getPassengers()
                + ", luggage_weight = " + entity.getLuggageWeight()
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
            Race entity = new Race(UUID.fromString(resultSet.getString("id")));
            entity.setDeparturePlace(resultSet.getString("departure_place"));
            entity.setArrivalPlace(resultSet.getString("arrival_place"));
            entity.setDepartureTime(resultSet.getTimestamp("departure_time"));
            entity.setArrivalTime(resultSet.getTimestamp("arrival_time"));
            entity.setPassengers(resultSet.getInt("passengers"));
            entity.setLuggageWeight(resultSet.getDouble("luggage_weight"));
            entities.add(entity);
        }
        return entities;
    }
}
