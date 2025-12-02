package org.ankurGangwar.systemDesign.parkingSpot;

import org.ankurGangwar.systemDesign.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ParkingSpot {
    private int id;
    private boolean isEmpty;
    private Vehicle vehicle;
    private SpotType spotType;
    private Map<ParkingTimeUnit, Double> rates;
    private List<SpotLocationFeature> spotLocationFeatureList;

    public ParkingSpot(int id, SpotType spotType, Map<ParkingTimeUnit, Double> rates) {
        this.id = id;
        this.isEmpty = true;
        this.vehicle = null;
        this.spotType = spotType;
        this.rates = rates;
        this.spotLocationFeatureList = new ArrayList<>();
    }

    public void addSpotLocationFeature(SpotLocationFeature spotLocationFeature) {
        this.spotLocationFeatureList.add(spotLocationFeature);
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isEmpty = false;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.isEmpty = true;
    }

    public void setRate(ParkingTimeUnit unit, Double rate) {
        this.rates.put(unit, rate);
    }

    public Double getRate(ParkingTimeUnit unit) {
        return rates.get(unit);
    }

    public boolean hasParkingTimeUnit(ParkingTimeUnit unit) {
        return rates.containsKey(unit);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public List<SpotLocationFeature> getSpotLocationFeatureList() {
        return spotLocationFeatureList;
    }

    public void setSpotLocationFeatureList(List<SpotLocationFeature> spotLocationFeatureList) {
        this.spotLocationFeatureList = spotLocationFeatureList;
    }
}
