package com.pluralsight;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Dealerships {
    public static void main(String[] args) {
        displayDealerships();
    }
    public static void displayDealerships() {
        Properties properties = new Properties();

        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(input);
            String dbUrl = properties.getProperty("db.url");
            String dbUser = properties.getProperty("db.user");
            String dbPassword = properties.getProperty("db.password");

            // Establish database connection
            try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT \n" +
                         "\tv.*,\n" +
                         "    d.name\n" +
                         "FROM Vehicles v\n" +
                         "JOIN Inventory i ON v.VIN = i.VIN\n" +
                         "JOIN Dealership d ON i.dealership_id = d.dealership_id;")) {


                // print header
                System.out.printf("%-5s %-10s %-10s %-8s %-10b %-50s%n", "VIN", "make", "model", "color", "sold", "name");
                System.out.println("------------------------------------------------------------------------");
                while (resultSet.next()) {
                    System.out.printf("%-5s %-10s %-10s %-8s %-10b %-50s%n",
                            resultSet.getString("VIN"),
                            resultSet.getString("make"),
                            resultSet.getString("model"),
                            resultSet.getString("color"),
                            resultSet.getBoolean("sold"),
                            resultSet.getString("name"));
                }
            }

        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
    }
}
