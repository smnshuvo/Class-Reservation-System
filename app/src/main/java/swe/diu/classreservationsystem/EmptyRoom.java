package swe.diu.classreservationsystem;

import java.util.ArrayList;
import java.util.List;

public class EmptyRoom {
    String day;
    String period;
    ArrayList<String> rooms;

    public EmptyRoom(String day, String period, ArrayList<String> rooms) {
        this.day = day;
        this.period = period;
        this.rooms = rooms;
    }

    public EmptyRoom() {
    }

    public String getDay() {
        return day;
    }

    public String getPeriod() {
        return period;
    }

    public ArrayList<String> getRooms() {
        return rooms;
    }


    public void setDay(String day) {
        this.day = day;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setRooms(ArrayList<String> rooms) {
        this.rooms = rooms;
    }
}
