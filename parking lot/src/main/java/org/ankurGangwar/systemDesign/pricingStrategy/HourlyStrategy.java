package org.ankurGangwar.systemDesign.pricingStrategy;

import org.ankurGangwar.systemDesign.parkingSpot.ParkingSpot;
import org.ankurGangwar.systemDesign.parkingSpot.ParkingTimeUnit;

public class HourlyStrategy implements PricingStrategy{

    @Override
    public double calculatePrice(ParkingSpot parkingSpot, long durationInMinutes) {
        double billableHours = Math.ceil(durationInMinutes / 60.0);

        if(parkingSpot.hasParkingTimeUnit(ParkingTimeUnit.HOUR)){
            return billableHours * parkingSpot.getRate(ParkingTimeUnit.HOUR);
        }
        throw new IllegalArgumentException("This spot does not support hourly billing.");
    }
}
