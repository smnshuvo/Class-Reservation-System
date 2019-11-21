package swe.diu.classreservationsystem;

public enum ROOM_STATUS {
    FREE(0),
    PENDING(1),
    BOOKED(2);

    private final int value;

    ROOM_STATUS(final int newVal){
        value = newVal;

    }

    public int getValue(){ return value;}
}
