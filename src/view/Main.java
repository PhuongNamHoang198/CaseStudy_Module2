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
        System.out.println("1. Hi???n th??? danh s??ch xe ??ang r???nh");
        System.out.println("2. ?????t xe t??? ?????ng");
        System.out.println("--- Manage ---");
        System.out.println("3. Th??m m???i");
        System.out.println("4. Thay l??i xe");
        System.out.println("5. X??a");
        System.out.println("6. Hi???n th??? top 3 c?? doanh thu cao nh??t");
        System.out.println("7. Hi???n th??? top 3 c?? doanh thu th???p nh??t");
        System.out.println("8. Hi???n th??? to??n b??? danh s??ch");
        System.out.println("0. Tho??t ch????ng tr??nh");
        System.out.println("--------------------");
        choice= scanner.nextInt();
        return choice;
    }
    public static String getPlate() {
        String licensePlate;
        Scanner input=new Scanner(System.in);
        do {
            System.out.println("Nh???p bi???n s??? xe");
            licensePlate=input.nextLine();
        }while (!licensePlate.matches(REGEX_PLATE));
        return licensePlate;
    }
    public static String getName(){
        Scanner input=new Scanner(System.in);
        System.out.println("Nh???p t??n l??i xe");
        String name=input.nextLine();
        return name;
    }
    public static Vehicle getVehicle(){
        String licensePlate=getPlate();
        String name=getName();
        int type;
        do {
            System.out.println("Ch???n lo???i ph????ng ti???n: 1. Taxi 2.Xe ??m");
            type=scanner.nextInt();
        }while (type!=1&&type!=2);
        if (type==1) return VehicleFactory.getVehicle(VehicleType.Taxi,name,licensePlate);
        else return VehicleFactory.getVehicle(VehicleType.Motorbike,name,licensePlate);
    }

    public static void takeAVehicle(ProxyManage client) {
        int distance;
        do {
            System.out.println("Nh???p kho???ng c??ch c???n ??i");
            distance=scanner.nextInt();
        }while (distance<0);
        int type;
        do {
            System.out.println("Ch???n lo???i ph????ng ti???n: 1. Taxi 2.Xe ??m");
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
