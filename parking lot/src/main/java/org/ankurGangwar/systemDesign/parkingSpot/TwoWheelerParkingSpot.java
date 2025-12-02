package org.ankurGangwar.systemDesign.parkingSpot;

import java.util.Map;

public class TwoWheelerParkingSpot extends ParkingSpot{

    public TwoWheelerParkingSpot(int id, Map<ParkingTimeUnit, Double> rates) {
        super(id, SpotType.TWO_WHEELER, rates);
    }
}
