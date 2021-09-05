package com.rogers.vehicle.model;

public class Vehicle {

    private int vehicleId;
    private String vin;
    private String model;
    private int days;

    public Vehicle() { }

    public Vehicle(String vin, String model, int days) {
        this.vin = vin;
        this.model = model;
        this.days = days;
    }

    public Vehicle(int vehicleId, String vin, String model, int days) {
        this.vehicleId = vehicleId;
        this.vin = vin;
        this.model = model;
        this.days = days;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int i) {
        this.days = i;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", vin='" + vin + '\'' +
                ", model='" + model + '\'' +
                ", days='" + days + '\'' +
                '}';
    }
}