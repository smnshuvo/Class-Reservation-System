package swe.diu.classreservationsystem;

import java.util.Calendar;

public class MyCalenderFormat {
    Calendar calendar;

    public MyCalenderFormat(Calendar calendar) {
        this.calendar = calendar;
    }

    public String getFormattedDate(){
        String date = String.format("%d-%d-%d", calendar.get(Calendar.DATE),calendar.get(Calendar.MONTH),calendar.get(Calendar.YEAR));
        return date;
    }
}
