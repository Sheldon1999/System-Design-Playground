package org.ankurGangwar.systemDesign.pricingStrategy.pricingDecorator;

import org.ankurGangwar.systemDesign.parkingSpot.ParkingSpot;
import org.ankurGangwar.systemDesign.pricingStrategy.PricingStrategy;

public class NearExitPricingDecorator extends PricingDecorator{

    private final double NearExitPrice;

    public NearExitPricingDecorator(PricingStrategy strategy) {
        super(strategy);
        this.NearExitPrice = 10.0;
    }

    @Override
    public double calculatePrice(ParkingSpot parkingSpot, long durationInMinutes){
        return super.calculatePrice(parkingSpot, durationInMinutes) + this.NearExitPrice;
    }
}
