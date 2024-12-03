package com.pluralsight;

public class Vehicles {
    private String vin;
    private String make;
    private String model;
    private String color;
    private double mileage;
    private double price;
    private String type;
    private boolean sold;

    public Vehicles(String vin, String make, String model, String color, double mileage, double price, String type, boolean sold) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.price = price;
        this.type = type;
        this.sold = sold;
    }

    public Vehicles() {

    }

    public String toFormatVehicles() {
        return String.format("%-5s %-10s %-10s %-8s %-15d %-15d %-10s %-5b%n", "VIN", "make", "model", "color", "mileage", "price", "type", "sold");
    }

    @Override
    public String toString() {
        return String.format("%-5s %-10s %-10s %-8s %-15s %-15s %-10s %-5b%n", vin, make, model, color, mileage, price, type, sold);
    }
}
