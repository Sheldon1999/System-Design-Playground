package org.ankurGangwar.systemDesign.pricingStrategy;

import org.ankurGangwar.systemDesign.parkingSpot.ParkingSpot;
import org.ankurGangwar.systemDesign.parkingSpot.ParkingTimeUnit;
import org.ankurGangwar.systemDesign.parkingSpot.SpotLocationFeature;
import org.ankurGangwar.systemDesign.pricingStrategy.pricingDecorator.NearElevatorPricingDecorator;
import org.ankurGangwar.systemDesign.pricingStrategy.pricingDecorator.NearExitPricingDecorator;

public class PricingFactory {

    public static PricingStrategy getPricingStrategy(ParkingSpot parkingSpot, ParkingTimeUnit preferredTimeUnit) {
        PricingStrategy strategy = null;

        if(preferredTimeUnit == ParkingTimeUnit.HOUR){
            strategy = new HourlyStrategy();
        } else {
            strategy = new MinutelyStrategy();
        }

        for(SpotLocationFeature spotLocationFeature: parkingSpot.getSpotLocationFeatureList()){
            switch (spotLocationFeature) {
                case NEAR_ELEVATOR:
                    strategy = new  NearElevatorPricingDecorator(strategy);
                    break;
                case NEAR_EXIT:
                    strategy = new NearExitPricingDecorator(strategy);
                    break;
            }
        }

        return strategy;
    }
}
