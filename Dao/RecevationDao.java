package Dao;

import CommonData.Constants;
import Entity.Guest;
import Entity.Recevation;
import Entity.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecevationDao {


    public static boolean save(Recevation recevation) {
        boolean x = false;

        Guest guest = recevation.getGuest();
        Room room = recevation.getRoom();
        try {
            FileWriter fw = new FileWriter(Constants.RESERVATION, true);
            BufferedWriter bw = new BufferedWriter(fw);

            String rec = guest.getpType() + "," + guest.getName() + "," + guest.getSurname() + "," + guest.getID() + "," + guest.getContactAddress() +
                    "," + guest.getPhone() + "," + guest.getMail() + "," + room.getRoomType() + "," + room.getRoomNo() + "," + room.getNightStay() + "," + room.getRoomPrice();
            bw.write(rec);
            bw.newLine();
            x = true;
            bw.close();

        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        return x;
    }

    public static boolean getByroom(int roomNo) {
        boolean roomsts = false;


        try {

            File file = new File(Constants.RESERVATION);
            BufferedReader br = new BufferedReader(new FileReader(file));


            while (br.readLine() != null) {

                String[] l = br.readLine().split(",");
                br.readLine();
                if (roomNo == Integer.parseInt(l[8])) {
                    roomsts = true;
                } else {
                    roomsts = false;
                }

            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return roomsts;

    }


    public static void getRecevedRooms() {

        List<Room> rooms = new ArrayList<>();
        Room room = null;
        try {

            File file = new File(Constants.RESERVATION);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null){

                String[] data = line.split(",");

                System.out.println(data[8]);

            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(rooms);


    }

    public static Room getRoom(int roomNo) {
        Room room = null;
        try {

            File file = new File(Constants.RESERVATION);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null){
                String[] data = line.split(",");
                if (roomNo == Integer.parseInt(data[8]) ){
                    room = new Room();
                        room.setRoomNo(Integer.parseInt(data[8]));
                        room.setRoomType(data[7]);
                        room.setNightStay(Integer.parseInt(data[9]));
                        room.setRoomPrice(Integer.parseInt(data[10]));
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return room;
    }
    public static void saveCheckIn(String date, int roomNo) {

        try {

            FileWriter fw = new FileWriter(Constants.CHECKIN, true);
            BufferedWriter bw = new BufferedWriter(fw);
            String rec = date + "," + roomNo;
            bw.write(rec);
            bw.newLine();
            bw.close();

        } catch (Exception e) {
            System.out.println("Error : " + e);
        }

    }
    public static void sowCheckIn() {

        try {

            File file = new File(Constants.CHECKIN);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line = "";
            while ((line = br.readLine()) != null){
                String[] data = line.split(",");
                System.out.println("Checked-in date :-"+data[0]);
                System.out.println("Entity.Room number :-"+data[1]);
            }
            br.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean getCheckInRoom(int roomNo) {

        boolean roomsts = true;

        try {

            File file = new File(Constants.CHECKIN);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (roomNo == Integer.parseInt(data[1])) {

                   roomsts = true;

                }else {
                    roomsts = false;
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return roomsts;
    }
    public static void saveCheckOut(String s, String date, int roomNo) {

        try {

            FileWriter fw = new FileWriter(Constants.CHECKOUT, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(s);
            bw.newLine();
            String rec = date + "," + roomNo;
            bw.write(rec);
            bw.newLine();
            bw.close();

        } catch (Exception e) {
            System.out.println("Error : " + e);
        }

    }

}
