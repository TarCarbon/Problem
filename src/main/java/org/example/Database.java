package org.example;

import org.flywaydb.core.Flyway;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {

    //private static final String DB_Driver = "jdbc:mariadb://localhost:3306/osbb";
    private Connection connection;
    private static ConnectionDB instance;

    public static ConnectionDB getInstance() throws SQLException, ClassNotFoundException, IOException {
        Properties properties = getProperties();
        if (instance == null) {
            Class.forName(properties.getProperty("flyway.dbDriver"));
            instance.connection = DriverManager.getConnection(
                    properties.getProperty("flyway.url"),
                    properties.getProperty("flyway.user"),
                    properties.getProperty("flyway.password"));
        }
        return instance;
    }
    public Connection getConnection(){
        return connection;
    }
    public static void flywayMigration(Properties properties){
        Flyway.configure()
                .dataSource((properties.getProperty("flyway.url")), properties.getProperty("flyway.user"), properties.getProperty("flyway.password"))
                .locations("classpath:migration")
                .load()
                .migrate();
    }
    private static Properties getProperties() throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader("flyway.conf");
        properties.load(fileReader);
        return properties;
    }


}

