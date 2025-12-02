package org.ankurGangwar.systemDesign.pricingStrategy;

import org.ankurGangwar.systemDesign.parkingSpot.ParkingSpot;

public interface PricingStrategy {
    double calculatePrice(ParkingSpot parkingSpot, long durationInMinutes);
}
