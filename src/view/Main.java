package view;

import model.Position;
import model.Vehicle;
import service.DecreaseSortByRenvenueVehicle;
import service.IncreaseSortByRevenueVehicle;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String TAXI_TYPE = "Taxi";
    public static final String MOTORBIKE_TYPE = "Motorbike";
    public static final String REGEX_PLATE = "[A-Z]{2}-[0-9]{5}";
    public static final int MAX_TIMES = 3;
    public static VehicleManage vehicleManage=VehicleManage.getInstance();
    public static IncreaseSortByRevenueVehicle increase=new IncreaseSortByRevenueVehicle();
    public static DecreaseSortByRenvenueVehicle decrease=new DecreaseSortByRenvenueVehicle();
    public static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        ReadAndWrite.creatNewFile(VehicleManage.FILE_NAME);
        Object obj=ReadAndWrite.readFile(VehicleManage.FILE_NAME);
        List<Vehicle> list;
        list=(obj==null)?new ArrayList<>():((List<Vehicle>)obj);
        ProxyManage manage=new ProxyManage(vehicleManage, Position.MANAGE);
        ProxyManage client=new ProxyManage(vehicleManage,Position.CLIENT);
        Thread thread=new Thread(client);
        thread.start();
        manage.setVehicleList(list);

    }
}
