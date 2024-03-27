package com.aircompany.db.dao;

import java.sql.Connection;

public class EntityDao {
    protected final Connection connection;
    protected final String table;

    public EntityDao(Connection connection, String table){
        this.connection = connection;
        this.table = table;
    }
}
