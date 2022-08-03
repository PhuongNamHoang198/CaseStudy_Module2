package model;

import java.io.Serializable;

public class Taxi extends Vehicle implements Serializable {
    public static final int PRICE_PER_1_KM = 10;

    public Taxi(String licensePlate, String driverName) {
        super(licensePlate, driverName);
    }

    @Override
    public int getRevenue() {
        return this.getDistance()*PRICE_PER_1_KM;
    }
}
