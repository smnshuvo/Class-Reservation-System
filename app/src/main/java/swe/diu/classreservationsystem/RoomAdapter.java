package swe.diu.classreservationsystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Hashtable;

public class RoomAdapter extends BaseAdapter {
    private String[] period;
    private String[] roomNo;
    Context context;
    Calendar calendar;
    private LayoutInflater layoutInflater;
    private final Dictionary periodTimeTable = new Hashtable();


    public RoomAdapter(String[] period, String[] roomNo, Calendar calendar, Context context) {
        this.period = period;
        this.roomNo = roomNo;
        this.context = context;
        this.calendar = calendar;

        periodTimeTable.put("1", "8.30 - 10.00 AM");
        periodTimeTable.put("2", "10.00 - 11.30 AM");
    }

    @Override
    public int getCount() {
        return period.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView==null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.room_list , parent, false);
            v = convertView;
        }

        TextView periodText = (TextView) convertView.findViewById(R.id.period);
        TextView roomList = (TextView) convertView.findViewById(R.id.rooms);
        Button book = (Button) convertView.findViewById(R.id.book);
        book.setTag(period[position]);

        String period_text;
        try{
            period_text = periodTimeTable.get(period[position]).toString();
        } catch (Exception e){period_text = "unfetched";}
        periodText.setText(period_text);
        roomList.setText(roomNo[position]);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String realPosition = (String) v.getTag();
                String date = new MyCalenderFormat(calendar).getFormattedDate();
                Toast.makeText(context, "Clicked ->" + realPosition + " " + date, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, BookARoom.class);
                intent.putExtra("DATE", date);
                intent.putExtra("PERIOD", period[position]);
                String[] rooms = roomNo[position].split("\\n"); // split string by new line
                intent.putExtra("EXTRA_ROOMS", rooms);
                context.startActivity(intent);


            }
        });



        return convertView;
    }
}
