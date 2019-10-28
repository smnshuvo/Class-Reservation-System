package swe.diu.classreservationsystem;

public class Routine {
    private int period; // period is like enum 1 represents 8.30
    private String room302;
    private String room304;
    private String room305;
    private String room306;
    private String room404;
    private String room405;
    private String room406;
    private String room501;
    private String room502;
    private String room504;
    private String room505;
    private String room601;
    private String room604;
    private String room605;
    private String room607;

    // default constructor
    public Routine() {
        // i don't know what to write here
    }

    public Routine(int period, String room302, String room304, String room305, String room306, String room404, String room405, String room406, String room501, String room502, String room504, String room505, String room601, String room604, String room605, String room607) {

        this.period = period;
        this.room302 = room302;
        this.room304 = room304;
        this.room305 = room305;
        this.room306 = room306;
        this.room404 = room404;
        this.room405 = room405;
        this.room406 = room406;
        this.room501 = room501;
        this.room502 = room502;
        this.room504 = room504;
        this.room505 = room505;
        this.room601 = room601;
        this.room604 = room604;
        this.room605 = room605;
        this.room607 = room607;
    }

    // Set of setters




    public void setPeriod(int period) {
        this.period = period;
    }

    public void setRoom302(String room302) {
        this.room302 = room302;
    }

    public void setRoom304(String room304) {
        this.room304 = room304;
    }

    public void setRoom305(String room305) {
        this.room305 = room305;
    }

    public void setRoom306(String room306) {
        this.room306 = room306;
    }

    public void setRoom404(String room404) {
        this.room404 = room404;
    }

    public void setRoom405(String room405) {
        this.room405 = room405;
    }

    public void setRoom406(String room406) {
        this.room406 = room406;
    }

    public void setRoom501(String room501) {
        this.room501 = room501;
    }

    public void setRoom502(String room502) {
        this.room502 = room502;
    }

    public void setRoom504(String room504) {
        this.room504 = room504;
    }

    public void setRoom505(String room505) {
        this.room505 = room505;
    }

    public void setRoom601(String room601) {
        this.room601 = room601;
    }

    public void setRoom604(String room604) {
        this.room604 = room604;
    }

    public void setRoom605(String room605) {
        this.room605 = room605;
    }

    public void setRoom607(String room607) {
        this.room607 = room607;
    }


    // set of getter

    public int getPeriod() {
        return period;
    }

    public String getRoom302() {
        return room302;
    }

    public String getRoom304() {
        return room304;
    }

    public String getRoom305() {
        return room305;
    }

    public String getRoom306() {
        return room306;
    }

    public String getRoom404() {
        return room404;
    }

    public String getRoom405() {
        return room405;
    }

    public String getRoom406() {
        return room406;
    }

    public String getRoom501() {
        return room501;
    }

    public String getRoom502() {
        return room502;
    }

    public String getRoom504() {
        return room504;
    }

    public String getRoom505() {
        return room505;
    }

    public String getRoom601() {
        return room601;
    }

    public String getRoom604() {
        return room604;
    }

    public String getRoom605() {
        return room605;
    }

    public String getRoom607() {
        return room607;
    }
}
