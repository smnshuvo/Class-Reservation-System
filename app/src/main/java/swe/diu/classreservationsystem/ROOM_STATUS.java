package swe.diu.classreservationsystem;

public enum ROOM_STATUS {
    FREE(0),
    PENDING(1),
    BOOKED(2);

    private final int STATUS;

    ROOM_STATUS(final int newStatus){
        STATUS = newStatus;

    }

    public int getValue(){ return STATUS;}
}
