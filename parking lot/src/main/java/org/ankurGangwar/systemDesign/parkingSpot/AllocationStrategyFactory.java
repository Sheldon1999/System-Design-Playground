package org.ankurGangwar.systemDesign.parkingSpot;

import org.ankurGangwar.systemDesign.gate.AllocationType;
import org.ankurGangwar.systemDesign.parkingSpot.parkingSpotStrategy.DefaultParkingSpotStrategy;
import org.ankurGangwar.systemDesign.parkingSpot.parkingSpotStrategy.NearElevatorSpotStrategy;
import org.ankurGangwar.systemDesign.parkingSpot.parkingSpotStrategy.NearExitParkingSpotStrategy;
import org.ankurGangwar.systemDesign.parkingSpot.parkingSpotStrategy.ParkingSpotStrategy;

public class AllocationStrategyFactory {
    public static ParkingSpotStrategy getStrategy(AllocationType type) {
        switch (type) {
            case NEAR_ELEVATOR:
                return new NearElevatorSpotStrategy();
            case NEAR_EXIT:
                return new NearExitParkingSpotStrategy();
            case DEFAULT:
            default:
                return new DefaultParkingSpotStrategy();
        }
    }
}
