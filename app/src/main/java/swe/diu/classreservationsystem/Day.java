package swe.diu.classreservationsystem;

public class Day {
    // A day represents a set of classes
    public int day;
    Routine classes;

    public Day(int day, Routine classes) {
        this.day = day;
        this.classes = classes;
    }

    public Day(){

    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Routine getClasses() {
        return classes;
    }

    public void setClasses(Routine classes) {
        this.classes = classes;
    }
}
