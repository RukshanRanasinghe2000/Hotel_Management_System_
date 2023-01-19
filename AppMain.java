import CommonData.Constants;
import Controller.FoodController;
import Controller.RecevationController;
import Dao.RecevationDao;
import Entity.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AppMain {
    Guest guest;
    Room room;
    Scanner input;

    final String header = Constants.RECEPTiION_LIST_HEADER;

    AppMain() {


        try {

            String whichPerson;
            Scanner input = new Scanner(System.in);

            System.out.println("\n*** *** Welcome *** ***");
            System.out.println("__Are you a guest__ ?\n[ Y/y ] or [ N/n ]");

            whichPerson = input.next();

            if (whichPerson.equals("Y") || whichPerson.equals("y")) {
                guestOperations();

            } else if (whichPerson.equals("N") || whichPerson.equals("n")) {
                noneGuestOperation();
            } else {
                System.out.println("Invalid Choice !");
            }

        } catch (Exception e) {

            System.out.println("Error : " + e);
        }

    }

    public void guestOperations() {

        try {
            int operationChoice;
            String firstName, lastName, nicNo, address, mobile, mail;
            String roomType;
            int roomNo, nightStay, roomPrice;

             input = new Scanner(System.in);


            System.out.println("Enter 1 to Reservation");
            System.out.println("Enter 2 for Food Order");
            operationChoice = input.nextInt();

            if (operationChoice == 1) {
                System.out.println("Enter your name ");
                firstName = input.next();

                System.out.println("Enter your surname ");
                lastName = input.next();

                System.out.println("Enter your NIC number ");
                nicNo = input.next();

                System.out.println("Enter your  address ");
                address = input.next();

                System.out.println("Enter your phone number ");
                mobile = input.next();

                System.out.println("Enter your e-mail ");
                mail = input.next();

                guest = new Guest();
                guest.setName(firstName);
                guest.setSurname(lastName);
                guest.setID(nicNo);
                guest.setContactAddress(address);
                guest.setPhone(mobile);
                guest.setMail(mail);
                guest.setpType("Entity.Guest");

                boolean roomsts;
                System.out.println("\n> Enter your room no: " +
                        "\n- Luxury - 1111    Price per Day - 3000" +
                        "\n- Luxury - 2222    Price per Day - 3000" +
                        "\n- Suit   - 3333    Price per Day - 2000" +
                        "\n- Suit   - 4444    Price per Day - 2000" +
                        "\n- Deluxe - 55555   Price per Day - 1000" +
                        "\n- Deluxe - 6666    Price per Day - 1000\n");

               do{

                   System.out.println("Enter room type ");
                   roomType = input.next();
                   System.out.println("Enter room number ");
                   roomNo = input.nextInt();
                   roomsts = RecevationController.checkRoomStatus(roomNo);

               }while(roomsts);


                if (roomsts) {
                    System.out.println("The room is already reserved !!");
                    System.out.println("\n Enter your room no \n- Luxury - 1111\n- Suit - 2222\n- Deluxe - 3333\n");
                    roomNo = input.nextInt();

                }

                System.out.println("\n Enter your the night stay ");
                nightStay = input.nextInt();

                System.out.println(" Enter the room price ( Rs ) \n- Luxury - 3000\n- Suit   - 2000\n- Deluxe - 1000\n");
                roomPrice = input.nextInt();

                room = new Room();
                room.setRoomNo(roomNo);
                room.setRoomType(roomType);
                room.setNightStay(nightStay);
                room.setRoomPrice(roomPrice);

                Recevation res = new Recevation();
                res.setGuest(guest);
                res.setRoom(room);

                if (RecevationController.saveRecevation(res)) {
                    System.out.println("\nYour reservation operation is successful..!\n");
                    System.out.println("**** Your reservation detail ****");
                    System.out.println("ReservationType :- Entity.Guest\nName :-"+firstName+"\nSurname :-"+lastName+"\nNIC Number :-"+nicNo+"\nAddres :-"+address+"\nPhone Number :-"+mobile+"\nE-Mail :-"+mail+"\nEntity.Room Type :-"+roomType+"\nEntity.Room No :-"+roomNo+"\nNight Stay :-"+nightStay+"\nEntity.Room Price ( Rs ) :-"+roomPrice);


                }else {
                    System.out.println("Can't to Save");
                }
            }

            if (operationChoice == 2) {
               OrderFood();
            }



        } catch (Exception e) {
            System.out.println("Error :" + e);
        }


    }
    public void OrderFood(){
        System.out.println("\n==========\n   Menu:  \n==========\n\n1.Sandwich\tRs.50\n2.Pasta\t\tRs.60\n3.Noodles\tRs.70\n4.Coke\t\tRs.30\n");
        System.out.println("Please select a item number");
        int itm = input.nextInt();
        System.out.println("Please select a item quantity");

        int qty = input.nextInt();
        FoodOrder food = new FoodOrder();
        food.setItemno(itm);
        food.setQuantity(qty);
        FoodController.saveFoodOrder(food);
    }




    public void noneGuestOperation() {

        try {

            int operationChoice, roomNo;

            Scanner input = new Scanner(System.in);


            System.out.println("*** Receptionist ***\nEnter 1 to Reservation and Food Orders");
            System.out.println("Enter 2 to Check-In");
            System.out.println("Enter 3 to Check-Out");

            operationChoice = input.nextInt();

            if (operationChoice == 1) {
                guestOperations();
                System.out.println("\nYour reservation operation is successful !!\n");

            } else if (operationChoice == 2) {
                checkInOperation();
            } else if (operationChoice == 3) {
                checkOutOperation();
            } else {
                System.out.println("Wrong operation choice !!");
            }


        } catch (Exception e) {
            System.out.println("Error:++++++ " + e);
        }


    }

    public boolean checkInOperation() // check in
    {

        int roomNo;
        Scanner input = new Scanner(System.in);

        System.out.println("\n*** Reserved Rooms *** \n");
        System.out.println(Constants.CHECKIN_HEADER);
        RecevationDao.getRecevedRooms();

        System.out.println("\n> Enter the room no :");
        roomNo = input.nextInt();
        if(RecevationController.checkRoomStatus(roomNo))
        {
            System.out.println("\nCheck-in operation is succesfull !!\n");

            Room room =  RecevationController.getRoomDetail(roomNo);
            System.out.println("Entity.Room No :-"+room.getRoomNo()+"\nEntity.Room Type :-"+room.getRoomType()+"\nNight Stay :-"+room.getNightStay()+"\nEntity.Room Price per day:-"+room.getRoomPrice());

            String date= getDate();

            RecevationController.saveCheackIn(date, roomNo);
            return true;

        }
        else {
            System.out.println("Check-in operation is not successfull !");
            return false;
        }
    }

    public boolean checkOutOperation()
    {
        int roomNo;
        Scanner input = new Scanner(System.in);

        System.out.println("\n*** Reserved Rooms In the Hotel *** \n");
        RecevationDao.sowCheckIn();
        System.out.println("\n> Enter the room no :");
        roomNo = input.nextInt();

        if (RecevationController.checkInRoomByRoomNo(roomNo))
        {
            System.out.println("\nCheck-Out operation is succesfull !!\n");
            String date =getDate();
            RecevationController.saveCheackOut("Check-Out Date,"+ Constants.CHECKIN_HEADER,date,roomNo);
            System.out.println("Checked-out date :-"+date+"\nEntity.Room Number :-"+roomNo);

            return true;
        }

        else {
            System.out.println("Check-out operation is not successfull !");
            return  false;
        }
    }


    public String getDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static void main(String[] args) {

        while (true) {
            new AppMain();
        }
    }

}
