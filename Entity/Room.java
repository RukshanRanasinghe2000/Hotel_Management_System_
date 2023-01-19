package Entity;

public class Room {

    private String roomType;
    private int roomNo;
    private int roomPrice;
    private int nightStay;
//    private PersonInterface personObject;


    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getNightStay() {
        return nightStay;
    }

    public void setNightStay(int nightStay) {
        this.nightStay = nightStay;
    }
}
