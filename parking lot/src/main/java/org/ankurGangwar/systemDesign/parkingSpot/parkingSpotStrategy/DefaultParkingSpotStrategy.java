package org.ankurGangwar.systemDesign.parkingSpot.parkingSpotStrategy;

import org.ankurGangwar.systemDesign.parkingSpot.ParkingSpot;

import java.util.List;

public class DefaultParkingSpotStrategy implements ParkingSpotStrategy{

    @Override
    public ParkingSpot findSpot(List<ParkingSpot> spots) {
        for(ParkingSpot parkingSpot: spots){
            if(parkingSpot.isEmpty()){
                return parkingSpot;
            }
        }
        return null;
    }
}
