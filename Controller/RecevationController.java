package Controller;

import Dao.RecevationDao;
import Entity.Recevation;
import Entity.Room;

public class RecevationController {


    public static Room getRoomDetail(int roomNo) {
        Room roomSts = RecevationDao.getRoom(roomNo);
        return roomSts;

    }


    public static boolean checkRoomStatus(int roomNo) {
        Room roomSts = RecevationDao.getRoom(roomNo);
        if (roomSts == null) {
            return false;
        } else {
            return true;
        }
    }

    public static void saveCheackIn(String date, int roomNo) {

        RecevationDao.saveCheckIn(date, roomNo);
    }

    public static void saveCheackOut(String s, String date, int roomNo) {


        RecevationDao.saveCheckOut(s, date, roomNo);

    }


    public static boolean saveRecevation(Recevation res) {
     return   RecevationDao.save(res);
    }



    public static boolean checkInRoomByRoomNo(int roomNo) {
        boolean roomSts = RecevationDao.getCheckInRoom(roomNo);
        if (!roomSts) {
            return false;
        } else {
            return true;
        }
    }
}
