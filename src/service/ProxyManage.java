package service;

import model.Position;
import model.Vehicle;
import storage.ReadAndWrite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static service.VehicleManage.FILE_NAME;
import static service.VehicleManage.FILE_NAME_TEXT;

public class ProxyManage implements Manage,Runnable{
    private VehicleManage vehicleManage;
    private Position position;

    public ProxyManage(VehicleManage vehicleManage, Position position) {
        this.vehicleManage = vehicleManage;
        this.position = position;
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
    public void takeAVehicle(int distance, String type){
        for (Vehicle v:vehicleList) {
            if (Method.getClassName(v).equals(type)){
                if (v.getStatus()){
                    v.setStatus(false);
                    v.setDistance(distance);
                    vehicleList.remove(v);
                    vehicleList.add(v);
                    ReadAndWrite.writeFile(FILE_NAME,vehicleList);
                    ReadAndWrite.writeFileToText(FILE_NAME_TEXT,vehicleList);
                    System.out.println("Đặt xe thành công "+v);
                    return;
                }
            }
        }
        System.out.println("Tất cả các xe "+type+ " đều bận");
    }
    @Override
    public void releaseVehicle(){
        for (Vehicle v:vehicleList) {
            if (!v.getStatus()){
                v.setStatus(true);
                ReadAndWrite.writeFile(FILE_NAME,vehicleList);
                ReadAndWrite.writeFileToText(FILE_NAME_TEXT,vehicleList);
                break;
            }
        }
    }
    @Override
    public List<Vehicle> sort(Comparator<Vehicle> comparator){
        List<Vehicle> list=new ArrayList<>();
        for (Vehicle v:vehicleList) {
            list.add(v);
        }
        Collections.sort(list,comparator);
        return list;
    }
    @Override
    public void show(List<Vehicle> list) {
        for (Vehicle v:list) {
            System.out.println(v.toString());
        }
    }
    @Override
    public void takeAVehicle(int distance, String type){
        for (Vehicle v:vehicleList) {
            if (Method.getClassName(v).equals(type)){
                if (v.getStatus()){
                    v.setStatus(false);
                    v.setDistance(distance);
                    vehicleList.remove(v);
                    vehicleList.add(v);
                    ReadAndWrite.writeFile(FILE_NAME,vehicleList);
                    ReadAndWrite.writeFileToText(FILE_NAME_TEXT,vehicleList);
                    System.out.println("Đặt xe thành công "+v);
                    return;
                }
            }
        }
        System.out.println("Tất cả các xe "+type+ " đều bận");
    }
    @Override
    public void releaseVehicle(){
        for (Vehicle v:vehicleList) {
            if (!v.getStatus()){
                v.setStatus(true);
                ReadAndWrite.writeFile(FILE_NAME,vehicleList);
                ReadAndWrite.writeFileToText(FILE_NAME_TEXT,vehicleList);
                break;
            }
        }
    }
    @Override
    public List<Vehicle> sort(Comparator<Vehicle> comparator){
        List<Vehicle> list=new ArrayList<>();
        for (Vehicle v:vehicleList) {
            list.add(v);
        }
        Collections.sort(list,comparator);
        return list;
    }
    @Override
    public void show(List<Vehicle> list) {
        for (Vehicle v:list) {
            System.out.println(v.toString());
        }
    }
}
