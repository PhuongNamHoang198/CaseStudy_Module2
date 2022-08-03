package service;

import model.*;
import java.util.Comparator;
import java.util.List;

public interface Manage {
    void setVehicleList(List<Vehicle> vehicleList);
    List<Vehicle> getVehicleList();
    List<Vehicle> getListAvaiable();
    void addNew(Vehicle vehicle);
    String editName(String licensePlate, String name);
    String remove(String licensePlate);
    void takeAVehicle(int distance, String type);
    void releaseVehicle();
    List<Vehicle> sort(Comparator<Vehicle> comparator);
    void show(List<Vehicle> list);
}
