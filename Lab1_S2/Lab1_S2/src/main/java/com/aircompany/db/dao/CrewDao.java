package com.aircompany.db.dao;

import com.aircompany.db.entity.Crewmate;
import com.aircompany.db.entity.Entity;
import com.aircompany.db.entity.Plane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrewDao extends EntityDao{
    public CrewDao(Connection connection) {
        super(connection, DaoManager.CREW_TABLE);
    }

    public void create(Crewmate entity) throws Exception{
        PreparedStatement statement = connection.prepareStatement("INSERT INTO " + table
                + " (name, qualification, brigade, id) VALUES (?, ?, ?, ?)");
        statement.setString(1,entity.getName());
        statement.setString(2,entity.getQualification().toString());
        statement.setString(3,entity.getBrigadeId().toString());
        statement.setString(4,entity.getId());
        statement.executeUpdate();
    }

    public Entity read(UUID id) throws Exception{
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE id = " + id.toString());
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            Crewmate entity = new Crewmate(UUID.fromString(resultSet.getString("id")));
            entity.setName(resultSet.getString("name"));
            entity.setQualification(Crewmate.Qualification.valueOf(resultSet.getString("qualification")));
            entity.setBrigadeId(resultSet.getString("brigade"));
            return entity;
        }
        return null;
    }

    public void update(Crewmate entity) throws Exception{
        PreparedStatement statement = connection.prepareStatement("UPDATE " + table
                + " SET name = " + entity.getName()
                + ", qualification = " + entity.getQualification()
                + ", brigade = " + entity.getBrigadeId()
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
            Crewmate entity = new Crewmate(UUID.fromString(resultSet.getString("id")));
            entity.setName(resultSet.getString("name"));
            entity.setQualification(Crewmate.Qualification.valueOf(resultSet.getString("qualification")));
            entity.setBrigadeId(resultSet.getString("brigade"));
            entities.add(entity);
        }
        return entities;
    }
}
