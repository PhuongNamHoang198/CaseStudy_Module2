package service;

import model.Position;
import model.Vehicle;
import java.util.Comparator;
import java.util.List;

public class ProxyManage implements Manage,Runnable{
    private VehicleManage vehicleManage;
    private Position position;
    public ProxyManage(VehicleManage vehicleManage, Position position){
        this.vehicleManage=vehicleManage;
        this.position=position;
    }

    @Override
    public void setVehicleList(List<Vehicle> vehicleList) {
        if (position==Position.MANAGE) vehicleManage.setVehicleList(vehicleList);
    }

    @Override
    public List<Vehicle> getVehicleList() {
        if (position==Position.MANAGE)return vehicleManage.getVehicleList();
        return null;
    }

    @Override
    public List<Vehicle> getListAvaiable() {
        if (position==Position.CLIENT) return vehicleManage.getListAvaiable();
        return null;
    }

    @Override
    public void addNew(Vehicle vehicle) {
        if (position==Position.MANAGE) vehicleManage.addNew(vehicle);
    }

    @Override
    public String editName(String licensePlate, String name) {
        if (position==Position.MANAGE) return vehicleManage.editName(licensePlate,name);
        return null;
    }

    @Override
    public String remove(String licensePlate) {
        if (position==Position.MANAGE) return vehicleManage.remove(licensePlate);
        return null;
    }

    @Override
    public void takeAVehicle(int distance, String type) {
        if (position==Position.CLIENT) vehicleManage.takeAVehicle(distance,type);
    }

    @Override
    public void releaseVehicle() {
        if (position==Position.CLIENT) vehicleManage.releaseVehicle();
    }

    @Override
    public List<Vehicle> sort(Comparator<Vehicle> comparator) {
        if (position==Position.MANAGE) return vehicleManage.sort(comparator);
        return null;
    }

    @Override
    public void show(List<Vehicle> list){
        if (list.size()==0) System.out.println("Danh sách trống");
        else vehicleManage.show(list);
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.releaseVehicle();
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
