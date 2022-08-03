package service;

import model.Vehicle;

import java.util.Comparator;

public class DecreaseSortByRenvenueVehicle implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        if (o1.getRevenue()<o2.getRevenue())return 1;
        if (o1.getRevenue()>o2.getRevenue())return -1;
        return 0;
    }
}
