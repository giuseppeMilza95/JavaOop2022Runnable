package org.example.task12.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DataBaseConnection {


    public Connection connect() throws SQLException{
        String url = DatabaseConfig.DATABASE_LOCAL_URL;
        Properties props = new Properties();
        props.setProperty("user",DatabaseConfig.USERNAME);
        props.setProperty("password",DatabaseConfig.PASS);
        return DriverManager.getConnection(url, props);
    }
}
