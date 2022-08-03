package model;


import com.sun.jdi.Method;

import java.io.Serializable;

public abstract class Vehicle implements Serializable {
    public static final int BEGIN_DISTANCE = 0;
    public static final boolean DEFAULT_STATUS = true;
    private String licensePlate;
    private String driverName;
    private int distance = BEGIN_DISTANCE;
    private boolean isAvaiable= DEFAULT_STATUS;

    public Vehicle(String licensePlate, String driverName) {
        this.licensePlate = licensePlate;
        this.driverName = driverName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isAvaiable() {
        return isAvaiable;
    }

    public void setAvaiable(boolean avaiable) {
        isAvaiable = avaiable;
    }

    @Override
    public String toString() {
        return Method.getClassName(this) +
                " { licensePlate='" + licensePlate + '\'' +
                ", driverName='" + driverName + '\'' +
                ", distance=" + distance +" revenue = "+getRevenue()+
                ", isAvaiable=" + isAvaiable +
                '}';
    }
    public abstract int getRevenue();
}
