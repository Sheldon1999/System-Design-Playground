package org.ankurGangwar.systemDesign.gate;

import org.ankurGangwar.systemDesign.parkingSpot.ParkingSpot;
import org.ankurGangwar.systemDesign.vehicle.Vehicle;

import java.time.LocalDateTime;

public class Ticket {
    private String ticketId;
    private long entryTime;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;

    public Ticket(long entryTime, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketId = "T-" + System.currentTimeMillis();
        this.entryTime = entryTime;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(long entryTime) {
        this.entryTime = entryTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
}
