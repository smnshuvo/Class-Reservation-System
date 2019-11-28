package swe.diu.classreservationsystem;

import java.util.Dictionary;
import java.util.Hashtable;

public class Reservation {
    String date;
    String roomNo;
    int status;
    String period;
    String askedBy;
    private final Dictionary periodTimeTable = new Hashtable();



    public Reservation(String date, String period, String roomNo, int status, String askedBy) {
        this.date = date;
        this.roomNo = roomNo;
        this.period = period;
        this.status = status;
        this.askedBy = askedBy;

        periodTimeTable.put("1", "8.30 - 10.00 AM");
        periodTimeTable.put("2", "10.00 - 11.30 AM");
    }



    public String getPeriod() {
        return this.period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }


    public Reservation() {
        periodTimeTable.put("1", "8.30 - 10.00 AM");
        periodTimeTable.put("2", "10.00 - 11.30 AM");
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

    public String getFormattedDetails(){
        String pDuration; // period duration
        try{
            pDuration =  periodTimeTable.get(this.period).toString();
        } catch (Exception e){pDuration = this.period;}
        String details = String.format("\n%s\nRoom: %s\nTime: %s\nDate: %s\n",this.askedBy, this.roomNo, pDuration, this.date);
        return details;
    }


    // I DIDN'T NOTICE THAT
    // I HAVEN'T SET A GETTER FOR THIS
    // IF I HAVE HAD KNOWN THAT
    // I WON'T HAD TO PASS THE STRING AS
    // INTENT EXTRA SO FAR
    public String getAskedBy() {
        return askedBy;
    }

    public void setAskedBy(String askedBy) {
        this.askedBy = askedBy;
    }
}
