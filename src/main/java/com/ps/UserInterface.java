package com.ps;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;

    public void display() {
        init();
        Scanner scanner = new Scanner(System.in);
        int command;

        do {
            displayMenu();
            command = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (command) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 0:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Command not found.");
            }
        } while (command != 0);
    }

    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager("inventory.txt");
        this.dealership = fileManager.getDealership();
    }

    private void displayMenu() {
        System.out.println("------ Dealership Application ------");
        System.out.println("1. Find vehicles within a price range");
        System.out.println("2. Find vehicles by make/model");
        System.out.println("3. Find vehicles by year range");
        System.out.println("4. Find vehicles by color");
        System.out.println("5. Find vehicles by mileage range");
        System.out.println("6. Find vehicles by type");
        System.out.println("7. List all vehicles");
        System.out.println("8. Add a vehicle");
        System.out.println("9. Remove a vehicle");
        System.out.println("0. Quit");
        System.out.print("Enter your choice: ");
    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            System.out.println("------ Vehicles ------");
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }
    private void processGetByPriceRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum price: ");
        double minPrice = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double maxPrice = scanner.nextDouble();

        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByPrice(minPrice,maxPrice);
        displayVehicles(filteredVehicles);
    }
    private void processGetByMakeModelRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(filteredVehicles);
    }
    private void processGetByYearRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum year: ");
        int minYear = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int maxYear = scanner.nextInt();

        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByYear(minYear, maxYear);
        displayVehicles(filteredVehicles);
    }
    private void processGetByColorRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByColor(color);
        displayVehicles(filteredVehicles);
    }
    private void processGetByMileageRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter maximum mileage: ");
        int maxMileage = scanner.nextInt();

        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByMileage(maxMileage);
        displayVehicles(filteredVehicles);
    }
    private void processGetByVehicleTypeRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter vehicle type: ");
        String type = scanner.nextLine();

        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByType(type);
        displayVehicles(filteredVehicles);
    }
    private void processAllVehiclesRequest() {
        ArrayList<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }
    private void processAddVehicleRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your vehicle's details:");

        System.out.print("VIN: ");
        int vin = scanner.nextInt();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Vehicle Type: ");
        String vehicleType = scanner.nextLine();
        System.out.print("Mileage: ");
        int odometer = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, odometer, color, price);
        dealership.addVehicle(vehicle);

        System.out.println("Vehicle added successfully.");
    }

    private void processRemoveVehicleRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter VIN of vehicle to remove: ");
        int vin = scanner.nextInt();

        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        Vehicle vehicleToRemove = null;

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin() == vin) {
                vehicleToRemove = vehicle;
                break;
            }
        }

        if (vehicleToRemove != null) {
            dealership.removeVehicle(vehicleToRemove);
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Vehicle with VIN " + vin + " not found.");
        }
    }
}
