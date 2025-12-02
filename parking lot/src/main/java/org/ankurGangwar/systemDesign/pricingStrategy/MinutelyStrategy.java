package org.ankurGangwar.systemDesign.pricingStrategy;

import org.ankurGangwar.systemDesign.parkingSpot.ParkingSpot;
import org.ankurGangwar.systemDesign.parkingSpot.ParkingTimeUnit;

public class MinutelyStrategy implements PricingStrategy{

    @Override
    public double calculatePrice(ParkingSpot parkingSpot, long durationInMinutes) {
        if(parkingSpot.hasParkingTimeUnit(ParkingTimeUnit.MINUTE)){
            return durationInMinutes * parkingSpot.getRate(ParkingTimeUnit.MINUTE);
        }
        throw new IllegalArgumentException("This spot does not support minutely billing");
    }
}
