package org.ankurGangwar.systemDesign.gate;

import org.ankurGangwar.systemDesign.parkingSpot.ParkingSpot;
import org.ankurGangwar.systemDesign.parkingSpot.ParkingSpotManager;
import org.ankurGangwar.systemDesign.parkingSpot.ParkingSpotManagerFactory;
import org.ankurGangwar.systemDesign.pricingStrategy.PricingStrategy;

public class ExitGate {
    private String gateId;
    private ParkingSpotManagerFactory managerFactory;

    public ExitGate(String gateId, ParkingSpotManagerFactory managerFactory) {
        this.gateId = gateId;
        this.managerFactory = managerFactory;
    }

    public PaymentReceipt processExit(Ticket ticket, PricingStrategy pricingStrategy){
        long exitTime = System.currentTimeMillis();
        long entryTime = ticket.getEntryTime();

        long durationMillis = exitTime - entryTime;
        long durationMinutes = durationMillis / (1000 * 60);

        ParkingSpot parkingSpot = ticket.getParkingSpot();

        ParkingSpotManager manager = managerFactory.getParkingSpotManager(parkingSpot.getSpotType());

        double price = pricingStrategy.calculatePrice(parkingSpot, durationMinutes);

        parkingSpot.removeVehicle();

        return new PaymentReceipt(entryTime, exitTime, price);
    }
}
