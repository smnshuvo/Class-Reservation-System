package swe.diu.classreservationsystem;

public class Reservation {
    String date;
    String roomNo;
    int status;
    String period;



    public Reservation(String date, String period, String roomNo, int status) {
        this.date = date;
        this.roomNo = roomNo;
        this.period = period;
        this.status = status;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }


    public Reservation() {
    }

    public String getDate() {
        return date;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public int getStatus() {
        return status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
