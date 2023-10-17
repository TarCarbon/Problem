package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class connectionToDB {

    public static Connection getConnection (String jdbcUrl, String userName, String password) throws SQLException{
        Connection connection = DriverManager.getConnection(jdbcUrl, userName, password);
            return connection;
    }

    private static void getQuery (Connection connection, String query) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            System.out.println("Query success");
        }
    }
}
