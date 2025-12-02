package org.ankurGangwar.systemDesign;

import org.ankurGangwar.systemDesign.gate.*;
import org.ankurGangwar.systemDesign.parkingSpot.*;
import org.ankurGangwar.systemDesign.pricingStrategy.HourlyStrategy;
import org.ankurGangwar.systemDesign.pricingStrategy.MinutelyStrategy;
import org.ankurGangwar.systemDesign.pricingStrategy.PricingStrategy;
import org.ankurGangwar.systemDesign.vehicle.Vehicle;
import org.ankurGangwar.systemDesign.vehicle.VehicleType;

import java.util.*;

public class Main {
    // Simulating a database to hold tickets between Entry and Exit
    private static final Map<String, Ticket> activeTickets = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // 1. BOOTSTRAP THE SYSTEM
        ParkingSpotManagerFactory factory = initializeSystem();
        EntryGate entryGate = new EntryGate("Entry-Gate-01", factory);
        ExitGate exitGate = new ExitGate("Exit-Gate-01", factory);

        while (true) {
            clearScreen();
            printHeader("PARKING LOT TERMINAL v1.0");
            System.out.println(" 1. Vehicle Entry  (Generate Ticket)");
            System.out.println(" 2. Vehicle Exit   (Pay Bill)");
            System.out.println(" 3. View System Status");
            System.out.println(" 4. Quit");
            printDashedLine();
            System.out.print(" Select Option > ");

            int choice = safeIntInput();

            switch (choice) {
                case 1:
                    handleEntry(entryGate);
                    break;
                case 2:
                    handleExit(exitGate);
                    break;
                case 3:
                    System.out.println("\n[ Active Tickets: " + activeTickets.size() + " ]");
                    activeTickets.values().forEach(t -> System.out.println(" - " + t.getTicketId()));
                    pause();
                    break;
                case 4:
                    System.out.println("Shutting down...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
                    pause();
            }
        }
    }

    // ==========================================
    // USER FLOW HANDLERS
    // ==========================================

    private static void handleEntry(EntryGate gate) {
        printHeader("VEHICLE ENTRY SCREEN");

        // 1. ASK VEHICLE DETAILS
        System.out.print(" Enter Vehicle Number (e.g., KA-01-1234): ");
        String number = scanner.next();

        System.out.println("\n Select Vehicle Type:");
        System.out.println("  1. Car (4-Wheeler)");
        System.out.println("  2. Bike (2-Wheeler)");
        System.out.print(" Choice > ");
        int typeChoice = safeIntInput();
        VehicleType vType = (typeChoice == 2) ? VehicleType.TWO_WHEELER : VehicleType.FOUR_WHEELER;

        Vehicle vehicle = new Vehicle(number, vType);

        // 2. ASK ALLOCATION STRATEGY
        System.out.println("\n Select Parking Preference:");
        System.out.println("  1. Standard Spot (First Available)");
        System.out.println("  2. VIP Spot (Near Exit - Extra Charges)");
        System.out.println("  3. VIP Spot (Near Elevator - Extra Charges)");
        System.out.print(" Choice > ");
        int allocChoice = safeIntInput();
        AllocationType allocType = AllocationType.DEFAULT;

        switch (allocChoice) {
            case 2: allocType = AllocationType.NEAR_EXIT; break;
            case 3: allocType = AllocationType.NEAR_ELEVATOR; break;
            case 1: allocType = AllocationType.DEFAULT; break;
            default: System.out.println("Invalid choice, defaulting to Standard.");
        }

        // 3. PROCESS ENTRY
        System.out.print("\n Processing...");
        simulateLoading();

        Ticket ticket = gate.getTicket(vehicle, allocType);

        if (ticket != null) {
            activeTickets.put(ticket.getTicketId(), ticket);
            printTicketUI(ticket);
        } else {
            System.out.println("\n [ERROR] Parking Full or No Suitable Spot Found!");
        }
        pause();
    }

    private static void handleExit(ExitGate gate) {
        printHeader("VEHICLE EXIT SCREEN");

        // 1. IDENTIFY TICKET
        System.out.print(" Enter Ticket ID: ");
        String ticketId = scanner.next();

        if (!activeTickets.containsKey(ticketId)) {
            System.out.println("\n [ERROR] Invalid Ticket ID. Please check again.");
            pause();
            return;
        }

        Ticket ticket = activeTickets.get(ticketId);

        // 2. ASK PRICING STRATEGY
        System.out.println("\n How would you like to be billed?");
        System.out.println("  1. Hourly (Standard - Rounded Up)");
        System.out.println("  2. Minutely (Exact Usage)");
        System.out.print(" Choice > ");
        int billingChoice = safeIntInput();

        PricingStrategy strategy;
        if (billingChoice == 2) {
            strategy = new MinutelyStrategy();
        } else {
            strategy = new HourlyStrategy();
        }

        // --- AUTOMATIC DURATION CALCULATION ---
        // We no longer ask the user. The ExitGate will calculate:
        // Duration = System.currentTimeMillis() - ticket.getEntryTime()

        System.out.print("\n Calculating Bill based on actual time...");
        simulateLoading();

        // Pass only ticket and strategy. The ExitGate calculates time internally.
        PaymentReceipt receipt = gate.processExit(ticket, strategy);

        // Remove from "database"
        activeTickets.remove(ticketId);

        printReceiptUI(receipt);
        pause();
    }

    // ==========================================
    // SYSTEM SETUP
    // ==========================================
    private static ParkingSpotManagerFactory initializeSystem() {
        // 1. BIKE SPOTS
        List<ParkingSpot> bikeSpots = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ParkingSpot p = new TwoWheelerParkingSpot(i, new HashMap<>());
            p.setRate(ParkingTimeUnit.HOUR, 10.0);
            p.setRate(ParkingTimeUnit.MINUTE, 0.2);

            if (i % 3 == 1) p.addSpotLocationFeature(SpotLocationFeature.NEAR_EXIT);
            else if (i % 3 == 2) p.addSpotLocationFeature(SpotLocationFeature.NEAR_ELEVATOR);

            bikeSpots.add(p);
        }

        // 2. CAR SPOTS
        List<ParkingSpot> carSpots = new ArrayList<>();
        for (int i = 101; i <= 110; i++) {
            ParkingSpot p = new FourWheelerParkingSpot(i, new HashMap<>());
            p.setRate(ParkingTimeUnit.HOUR, 20.0);
            p.setRate(ParkingTimeUnit.MINUTE, 0.5);

            if (i % 2 == 0) p.addSpotLocationFeature(SpotLocationFeature.NEAR_EXIT);
            else if (i % 3 == 0) p.addSpotLocationFeature(SpotLocationFeature.NEAR_ELEVATOR);

            carSpots.add(p);
        }

        // 3. MANAGERS
        ParkingSpotManager bikeManager = new ParkingSpotManager(bikeSpots);
        ParkingSpotManager carManager = new ParkingSpotManager(carSpots);

        // 4. FACTORY
        ParkingSpotManagerFactory factory = new ParkingSpotManagerFactory();
        factory.registerManager(SpotType.TWO_WHEELER, bikeManager);
        factory.registerManager(SpotType.FOUR_WHEELER, carManager);

        return factory;
    }

    // ==========================================
    // UI HELPERS
    // ==========================================
    private static void printHeader(String title) {
        System.out.println("\n+------------------------------------------+");
        System.out.printf("| %-40s |\n", title);
        System.out.println("+------------------------------------------+");
    }

    private static void printDashedLine() {
        System.out.println("--------------------------------------------");
    }

    private static void printTicketUI(Ticket t) {
        System.out.println("\n  SUCCESS! TICKET GENERATED");
        printDashedLine();
        System.out.printf("  Ticket ID  : %s\n", t.getTicketId());
        System.out.printf("  Vehicle No : %s\n", t.getVehicle().getVehicleNumber());
        System.out.printf("  Spot ID    : %d\n", t.getParkingSpot().getId());
        printDashedLine();
        System.out.println("  * Please keep this ticket safe *");
    }

    private static void printReceiptUI(PaymentReceipt r) {
        System.out.println(r.toString());
    }

    private static void simulateLoading() {
        try {
            for(int i=0; i<3; i++) { System.out.print("."); Thread.sleep(300); }
            System.out.println();
        } catch (InterruptedException e) {}
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pause() {
        System.out.println("\nPress Enter to continue...");
        try { System.in.read(); } catch (Exception e) {}
    }

    private static int safeIntInput() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next();
            return -1;
        }
    }
}