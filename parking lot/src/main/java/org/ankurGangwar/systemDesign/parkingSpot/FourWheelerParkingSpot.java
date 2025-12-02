package org.ankurGangwar.systemDesign.parkingSpot;

import java.util.Map;

public class FourWheelerParkingSpot extends ParkingSpot{

    public FourWheelerParkingSpot(int id, Map<ParkingTimeUnit, Double> rates) {
        super(id, SpotType.FOUR_WHEELER, rates);
    }
}
