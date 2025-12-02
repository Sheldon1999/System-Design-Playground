package org.ankurGangwar.systemDesign.parkingSpot.parkingSpotStrategy;

import org.ankurGangwar.systemDesign.parkingSpot.ParkingSpot;
import org.ankurGangwar.systemDesign.parkingSpot.SpotLocationFeature;

import java.util.List;

public class NearExitParkingSpotStrategy implements ParkingSpotStrategy{

    @Override
    public ParkingSpot findSpot(List<ParkingSpot> spots) {
        for(ParkingSpot parkingSpot: spots){
            if(parkingSpot.getSpotLocationFeatureList().contains(SpotLocationFeature.NEAR_EXIT) && parkingSpot.isEmpty()){
                return parkingSpot;
            }
        }
        return null;
    }
}
