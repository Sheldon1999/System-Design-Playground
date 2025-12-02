package org.ankurGangwar.systemDesign.pricingStrategy.pricingDecorator;

import org.ankurGangwar.systemDesign.parkingSpot.ParkingSpot;
import org.ankurGangwar.systemDesign.pricingStrategy.PricingStrategy;

public abstract class PricingDecorator implements PricingStrategy {
    protected PricingStrategy pricingStrategy;

    public PricingDecorator(PricingStrategy strategy){
        this.pricingStrategy = strategy;
    }

    @Override
    public double calculatePrice(ParkingSpot parkingSpot, long durationInMinutes){
        return pricingStrategy.calculatePrice(parkingSpot, durationInMinutes);
    }
}
