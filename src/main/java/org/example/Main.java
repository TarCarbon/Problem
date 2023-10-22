package org.example;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class Main {
    private static final String QUERY = "SELECT residents.name, residents.sname, residents.email,  apartments.number, apartments.area, builders.adress\n" +
                    "FROM residents\n" +
            "         INNER JOIN residents_to_apartments ON residents.id = residents_to_apartments.resident_id\n" +
            "         INNER JOIN apartments ON apartments.id = residents_to_apartments.apartment_id\n" +
            "         INNER JOIN builders_to_apartments ON builders_to_apartments.apartment_id = apartments.id\n" +
            "         INNER JOIN builders ON builders.id = builders_to_apartments.builder_id\n" +
            "         INNER JOIN property_rights_to_residents ON property_rights_to_residents.resident_id = residents.id\n" +
            "         INNER JOIN property_rights ON property_rights_to_residents.property_right_id = property_rights_to_residents.id\n" +
                    "WHERE residents.drive_into_the_territory = 1 AND property_rights_to_residents.property_right_id <= 3\n" +
            "  AND (SELECT COUNT(property_rights_to_residents.property_right_id) FROM property_rights_to_residents) = 1;\n";
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) throws SQLException {
        logger.info("The program hase started");
        ConnectionDB connection = new ConnectionDB();
        connection.getConnection();
        connection.getQuery(QUERY);
    }
}