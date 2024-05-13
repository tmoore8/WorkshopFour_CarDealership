package com.ps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DealershipFileManager {

    public DealershipFileManager(String file) {
    }

    public Dealership getDealership() {
        Dealership dealership = null;
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.txt"))) {
            // Read dealership information from the first line
            String dealershipInfo = reader.readLine();
            String[] dealershipData = dealershipInfo.split("\\|");
            String name = dealershipData[0];
            String address = dealershipData[1];
            String phone = dealershipData[2];
            dealership = new Dealership(name, address, phone);
            // Read vehicle information from subsequent lines
            String line;
            while ((line = reader.readLine()) != null) {
                String[] vehicleData = line.split("\\|");
                Vehicle vehicle = new Vehicle(Integer.parseInt(vehicleData[0]), Integer.parseInt(vehicleData[1]),
                        vehicleData[2], vehicleData[3], vehicleData[4], vehicleData[5], Integer.parseInt(vehicleData[6]),
                         Double.parseDouble(vehicleData[7]));
                vehicles.add(vehicle);
            }
            dealership.getAllVehicles().addAll(vehicles);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return dealership;
    }
    public void saveDealership(Dealership dealership) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.txt"))) {
            // Write dealership information to the first line
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            writer.newLine();
            // Write vehicle information to subsequent lines
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                writer.write(vehicle.getMake() + "|" + vehicle.getModel() + "|" + vehicle.getYear() + "|"
                        + vehicle.getColor() + "|" + vehicle.getOdometer() + "|" + vehicle.getPrice() + "|"
                        + vehicle.getVehicleType());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing file");
        }
    }

}
