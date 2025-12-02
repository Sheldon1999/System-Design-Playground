package org.ankurGangwar.systemDesign.pricingStrategy.pricingDecorator;

import org.ankurGangwar.systemDesign.parkingSpot.ParkingSpot;
import org.ankurGangwar.systemDesign.pricingStrategy.PricingStrategy;

public class NearElevatorPricingDecorator extends PricingDecorator{

    private final double NearElevatorPrice;

    public NearElevatorPricingDecorator(PricingStrategy strategy) {
        super(strategy);
        this.NearElevatorPrice = 5.0;
    }

    @Override
    public double calculatePrice(ParkingSpot parkingSpot, long durationInMinutes){
        return super.calculatePrice(parkingSpot, durationInMinutes) + this.NearElevatorPrice;
    }
}
