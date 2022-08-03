package view;

import model.Position;
import model.Vehicle;
import model.VehicleType;
import service.*;
import storage.ReadAndWrite;

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
        int choice;
        do {
            choice = Menu();
            switch (choice){
                case 1:
                    client.show(client.getListAvaiable());
                    break;
                case 2:
                    takeAVehicle(client);
                    break;
                case 3:
                    Vehicle newVehicle=getVehicle();
                    manage.addNew(newVehicle);
                    break;
                case 4:
                    String licensePlateEdit=getPlate();
                    String newName=getName();
                    manage.editName(licensePlateEdit,newName);
                    break;
                case 5:
                    String licensePlateRemove=getPlate();
                    System.out.println(manage.remove(licensePlateRemove));
                    break;
                case 6:
                    showTop3Max(manage);
                    break;
                case 7:
                    showTop3Min(manage);
                    break;
                case 8:
                    manage.show(manage.getVehicleList());
                    break;
                case 0:
                    System.exit(0);
            }
        }while (choice!=0);
    }

    private static int Menu() {
        int choice;
        System.out.println("--- Client ---");
        System.out.println("1. Hiện thị danh sách xe đang rảnh");
        System.out.println("2. Đặt xe tự động");
        System.out.println("--- Manage ---");
        System.out.println("3. Thêm mới");
        System.out.println("4. Thay lái xe");
        System.out.println("5. Xóa");
        System.out.println("6. Hiển thị top 3 có doanh thu cao nhât");
        System.out.println("7. Hiển thị top 3 có doanh thu thấp nhât");
        System.out.println("8. Hiển thị toàn bộ danh sách");
        System.out.println("0. Thoát chương trình");
        System.out.println("--------------------");
        choice= scanner.nextInt();
        return choice;
    }
    public static String getPlate() {
        String licensePlate;
        Scanner input=new Scanner(System.in);
        do {
            System.out.println("Nhập biển số xe");
            licensePlate=input.nextLine();
        }while (!licensePlate.matches(REGEX_PLATE));
        return licensePlate;
    }
    public static String getName(){
        Scanner input=new Scanner(System.in);
        System.out.println("Nhập tên lái xe");
        String name=input.nextLine();
        return name;
    }
    public static Vehicle getVehicle(){
        String licensePlate=getPlate();
        String name=getName();
        int type;
        do {
            System.out.println("Chọn loại phương tiện: 1. Taxi 2.Xe ôm");
            type=scanner.nextInt();
        }while (type!=1&&type!=2);
        if (type==1) return VehicleFactory.getVehicle(VehicleType.Taxi,name,licensePlate);
        else return VehicleFactory.getVehicle(VehicleType.Motorbike,name,licensePlate);
    }

    public static void takeAVehicle(ProxyManage client) {
        int distance;
        do {
            System.out.println("Nhập khoảng cách cần đi");
            distance=scanner.nextInt();
        }while (distance<0);
        int type;
        do {
            System.out.println("Chọn loại phương tiện: 1. Taxi 2.Xe ôm");
            type=scanner.nextInt();
        }while (type!=1&&type!=2);
        if (type==1) client.takeAVehicle(distance, TAXI_TYPE);
        else client.takeAVehicle(distance, MOTORBIKE_TYPE);
    }
    public static void showTop3Max(Manage manage){
        List<Vehicle> list=manage.sort(decrease);
        int count=0;
        for (Vehicle v:list) {
            if (count<MAX_TIMES){
                System.out.println(v);
                count++;
            }
        }
    }
    public static void showTop3Min(Manage manage){
        List<Vehicle> list=manage.sort(increase);
        int count=0;
        for (Vehicle v:list) {
            if (count< MAX_TIMES){
                System.out.println(v);
                count++;
            }
        }
    }
}
