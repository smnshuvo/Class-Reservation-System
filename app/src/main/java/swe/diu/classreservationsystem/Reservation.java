package swe.diu.classreservationsystem;

public class Reservation {
    int date;
    String roomNo;
    int status;

    public Reservation(int date, String roomNo, int status) {
        this.date = date;
        this.roomNo = roomNo;
        this.status = status;
    }

    public Reservation() {
    }

    public int getDate() {
        return date;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public int getStatus() {
        return status;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
