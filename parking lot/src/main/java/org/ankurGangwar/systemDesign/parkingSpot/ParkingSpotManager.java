package org.ankurGangwar.systemDesign.parkingSpot;

import org.ankurGangwar.systemDesign.parkingSpot.parkingSpotStrategy.ParkingSpotStrategy;
import org.ankurGangwar.systemDesign.vehicle.Vehicle;

import java.util.List;

public class ParkingSpotManager {
    private List<ParkingSpot> spotList;
//    private ParkingSpotStrategy spotStrategy;

    public ParkingSpotManager(List<ParkingSpot> spotList) {
        this.spotList = spotList;
//        this.spotStrategy = spotStrategy;
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        this.spotList.add(parkingSpot);
    }

    public void addParkingSpotsList(List<ParkingSpot> spotList){
        this.spotList.addAll(spotList);
    }

    public void removeParkingSpot(ParkingSpot parkingSpot) {
        this.spotList.remove(parkingSpot);
    }

    public ParkingSpot findParkingSpot(ParkingSpotStrategy spotStrategy) {
        return spotStrategy.findSpot(spotList);
    }

    public ParkingSpot parkVehicle(Vehicle vehicle, ParkingSpotStrategy spotStrategy) {
        ParkingSpot spot = spotStrategy.findSpot(this.spotList);
        if(spot != null) {
            spot.parkVehicle(vehicle);
            return spot;
        }
        return null;
    }

    public boolean removeVehicle(Vehicle vehicle) {
        for (ParkingSpot spot: spotList){
            if(spot.getVehicle() == vehicle){
                spot.removeVehicle();
                return true;
            }
        }
        return false;
    }

}
