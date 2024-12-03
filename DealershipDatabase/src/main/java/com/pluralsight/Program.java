package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.Scanner;

public class Program {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl("jdbc:mysql://localhost:3306/CarDealershipDatabase");
        bds.setUsername(args[0]);
        bds.setPassword(args[1]);

        VehicleDao vehicleDao = new VehicleDao(bds);

        List<Vehicles> vehicles;
        int answer;

        System.out.println("""
                Please choose options below:\
                
                Look for Vehicles BY:\
                
                (1) - Price Range\
                
                (2) - Make and Model\
                
                (3) - Year Range\
                
                (4) - By Color\
                
                (5) - Mileage Range\
                
                (6) - By Type\
                
                (0) - Exit""");

        answer = scanner.nextInt();
        scanner.nextLine();

        switch (answer) {
            case 1 -> priceRange(vehicleDao);
            case 2 -> makeModel(vehicleDao);
            case 3 -> searchByYearRange(vehicleDao);
            case 4 -> searchByColor(vehicleDao);
            case 5 -> searchByMileageRange(vehicleDao);
            case 6 -> searchByType(vehicleDao);
            case 0 -> System.exit(0);
            default -> System.out.println("Please enter the correct options.");
        }

    }

    public static void priceRange(VehicleDao vehicleDao) {
        System.out.println("Please enter minimum price range: ");
        int minPrice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please enter maximum price range: ");
        int maxPrice = scanner.nextInt();
        scanner.nextLine();

        List<Vehicles> vehicles = vehicleDao.getVehiclesByPriceRange(minPrice, maxPrice);
        displayVehicles(vehicles);

    }

    public static void makeModel(VehicleDao vehicleDao) {
        System.out.print("Please enter the make: ");
        String make = scanner.nextLine();
        System.out.print("Please enter the model: ");
        String model = scanner.nextLine();

        List<Vehicles> vehicles = vehicleDao.getByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    private static void searchByYearRange(VehicleDao vehicleDao) {
        System.out.print("Please enter minimum year: ");
        int minYear = scanner.nextInt();
        System.out.print("Please enter maximum year: ");
        int maxYear = scanner.nextInt();

        List<Vehicles> vehicles = vehicleDao.getByYearRange(minYear, maxYear);
        displayVehicles(vehicles);
    }

    private static void searchByColor(VehicleDao vehicleDao) {
        System.out.print("Please enter the color: ");
        String color = scanner.nextLine();

        List<Vehicles> vehicles = vehicleDao.getByColor(color);
        displayVehicles(vehicles);
    }

    private static void searchByMileageRange(VehicleDao vehicleDao) {
        System.out.print("Please enter minimum mileage: ");
        int minMileage = scanner.nextInt();
        System.out.print("Please enter maximum mileage: ");
        int maxMileage = scanner.nextInt();

        List<Vehicles> vehicles = vehicleDao.getByMileageRange(minMileage, maxMileage);
        displayVehicles(vehicles);
    }

    private static void searchByType(VehicleDao vehicleDao) {
        System.out.print("Please enter the type: ");
        String type = scanner.nextLine();

        List<Vehicles> vehicles = vehicleDao.getByType(type);
        displayVehicles(vehicles);
    }

    private static void displayVehicles(List<Vehicles> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found matching your criteria.");
        } else {
            for (Vehicles vehicle : vehicles) {
                System.out.println(vehicle); // Ensure Vehicles class has a proper toString() implementation
            }
        }
    }
}
