package org.example;

import org.flywaydb.core.Flyway;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {

    private static final String DB_Driver = "jdbc:mariadb://localhost:3306/osbb";
    private Connection connection;
    private static ConnectionDB instance;

    public static ConnectionDB getConnection(String jdbcUrl) throws SQLException, IOException, ClassNotFoundException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader("flyway.conf");
        properties.load(fileReader);
        if (instance == null) {
            Class.forName(DB_Driver);
            String userName = properties.getProperty("flyway.user");
            String password = properties.getProperty("flyway.password");
            instance.connection = DriverManager.getConnection(jdbcUrl, userName, password);
        }

        Flyway.configure()
                .dataSource((properties.getProperty("flyway.url")), properties.getProperty("flyway.user"), properties.getProperty("flyway.password"))
                .locations("classpath:migration")
                .load()
                .migrate();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void getQuery(String query) throws SQLException {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
            System.out.println("Query success");
        }
    }
}

