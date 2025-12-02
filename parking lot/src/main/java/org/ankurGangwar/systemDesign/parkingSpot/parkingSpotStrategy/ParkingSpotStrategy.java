package org.ankurGangwar.systemDesign.parkingSpot.parkingSpotStrategy;

import org.ankurGangwar.systemDesign.parkingSpot.ParkingSpot;

import java.util.List;

public interface ParkingSpotStrategy {
    ParkingSpot findSpot(List<ParkingSpot> spots);
}
