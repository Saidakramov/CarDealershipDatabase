package com.pluralsight;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private List<Vehicles> fetchVehicles(String query, List<Object> parameters) {
        List<Vehicles> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String vin = rs.getString("VIN");
                    String make = rs.getString("make");
                    String model = rs.getString("model");
                    String color = rs.getString("color");
                    double mileage = rs.getDouble("mileage");
                    double price = rs.getDouble("price");
                    String type = rs.getString("type");
                    boolean sold = rs.getBoolean("sold");
                    vehicles.add(new Vehicles(vin, make, model, color, mileage, price, type, sold));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vehicles;
    }

    public List<Vehicles> getVehiclesByPriceRange(int minPrice, int maxPrice) {
//        System.out.println("Please enter the minimum price for the vehicle: ");
//        minPrice = scanner.nextInt();
//        scanner.nextLine();
//
//        System.out.println("Please enter the maximum price for the vehicle: ");
//        maxPrice = scanner.nextInt();
//        scanner.nextLine();

        if (minPrice <=0 || maxPrice <= 0) {
            System.out.println("No min or max price provided.");
            return Collections.emptyList();
        }

        String query = ("SELECT * FROM vehicles WHERE price BETWEEN ? AND ?");
        List<Object> parameters = Arrays.asList(minPrice, maxPrice);

        return fetchVehicles(query, parameters);
    }

    public List<Vehicles> getByMakeModel(String make, String model) {
        if (make == null || make.trim().isEmpty() || model == null || model.trim().isEmpty()) {
            System.out.println("No make or model provided");
            return Collections.emptyList();
        }

        String query = ("SELECT * FROM vehicles WHERE make LIKE ?  AND model LIKE ?");
        List<Object> parameters = Arrays.asList("%" + make + "%", "%" + model + "%");

        return fetchVehicles(query, parameters);
    }

    public List<Vehicles> getByYearRange(int minYear, int maxYear) {
        if (minYear <= 0 || maxYear <= 0) {
            System.out.println("No minimum year or maximum year provided.");
            return Collections.emptyList();
        }

        String query = ("SELECT * FROM vehicles WHERE year BETWEEN ? AND ?");
        List<Object> parameters = Arrays.asList(minYear, maxYear);

        return fetchVehicles(query, parameters);
    }

    public List<Vehicles> getByColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            System.out.println("No color provided.");
            return Collections.emptyList();
        }

        String query = "SELECT * FROM vehicles WHERE color LIKE ?";
        List<Object> parameter = List.of("%" + color + "%");

        return fetchVehicles(query, parameter);
    }

    public List<Vehicles> getByMileageRange(int minMileage, int maxMileage) {
        if (minMileage <= 0 || maxMileage <= 0) {
            System.out.println("No minimum or maximum mileage provided.");
            return Collections.emptyList();
        }

        String query = ("SELECT * FROM vehicles WHERE mileage BETWEEN ? AND ?");
        List<Object> parameters = Arrays.asList(minMileage, maxMileage);

        return fetchVehicles(query, parameters);
    }

    public List<Vehicles> getByType(String type) {
        if (type == null || type.trim().isEmpty()) {
            System.out.println("No type provided.");
            return Collections.emptyList();
        }

        String query = "SELECT * FROM vehicles WHERE type LIKE ?";
        List<Object> parameter = List.of("%" + type + "%");

        return fetchVehicles(query, parameter);
    }
}
