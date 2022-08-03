package model;

import java.io.Serializable;

public class Motorbike extends Vehicle implements Serializable {
     public static final int PRICE_PER_1_KM = 5;

    public Motorbike(String licensePlate, String driverName) {
        super(licensePlate, driverName);
    }

    @Override
    public int getRevenue() {
        return this.getDistance()*PRICE_PER_1_KM;
    }
}
