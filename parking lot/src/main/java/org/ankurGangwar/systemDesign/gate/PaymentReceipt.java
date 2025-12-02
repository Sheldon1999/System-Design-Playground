package org.ankurGangwar.systemDesign.gate;

public class PaymentReceipt {
    private String receptionId;
    private long entryTime;
    private long exitTime;
    private double amount;

    public PaymentReceipt(long entryTime, long exitTime, double amount) {
        this.receptionId = "R-" + System.currentTimeMillis();
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.amount = amount;
    }

    public String toString() {
        long duration = (exitTime - entryTime) / (1000 * 60);
        return String.format("Receipt [%s]: %d mins parked. Total: INR %.2f", receptionId, duration, amount);
    }
}
