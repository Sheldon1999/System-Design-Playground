package org.ankurGangwar.systemDesign.gate;

import org.ankurGangwar.systemDesign.parkingSpot.*;
import org.ankurGangwar.systemDesign.vehicle.Vehicle;
import org.ankurGangwar.systemDesign.vehicle.VehicleType;

import java.time.LocalDateTime;

public class EntryGate {
    private String gateId;
    private ParkingSpotManagerFactory spotManagerFactory;

    public EntryGate(String gateId, ParkingSpotManagerFactory spotManagerFactory) {
        this.gateId = gateId;
        this.spotManagerFactory = spotManagerFactory;
    }

    private SpotType getSpotTypeForVehicle(Vehicle vehicle){
        if(vehicle.getVehicleType() == VehicleType.FOUR_WHEELER){
            return SpotType.FOUR_WHEELER;
        } else{
            return SpotType.TWO_WHEELER;
        }
    }

    public Ticket getTicket(Vehicle vehicle, AllocationType allocationType) {
        SpotType spotType = getSpotTypeForVehicle(vehicle);
        ParkingSpotManager manager = spotManagerFactory.getParkingSpotManager(spotType);
        if(manager == null) {
            throw new RuntimeException("No spot manager found for vehicle type: "+ spotType);
        }
        ParkingSpot parkingSpot = manager.parkVehicle(vehicle, AllocationStrategyFactory.getStrategy(allocationType));
        if(parkingSpot != null) {
            long currentTime = System.currentTimeMillis();
            return new Ticket(currentTime, vehicle, parkingSpot);
        }
        throw new RuntimeException("No parking spot can be found for vehicle");
    }
}
