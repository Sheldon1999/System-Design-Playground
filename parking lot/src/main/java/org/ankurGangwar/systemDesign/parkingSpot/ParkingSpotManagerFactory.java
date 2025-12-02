package org.ankurGangwar.systemDesign.parkingSpot;

import java.util.HashMap;
import java.util.Map;

public class ParkingSpotManagerFactory {
    private Map<SpotType, ParkingSpotManager> managerMap;

    public ParkingSpotManagerFactory() {
        this.managerMap = new HashMap<>();
    }

    public void registerManager(SpotType spotType, ParkingSpotManager manager) {
        this.managerMap.put(spotType, manager);
    }

    public ParkingSpotManager getParkingSpotManager(SpotType spotType) {
        return this.managerMap.get(spotType);
    }
}
