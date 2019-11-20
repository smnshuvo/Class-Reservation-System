package swe.diu.classreservationsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ViewRooms extends AppCompatActivity {
    CalendarView calenderView;
private static final String TAG = "DATABASE";
private static String SELECTED_DAY = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rooms);

        // DATABASE Reference
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


        // GET my calender
        calenderView = findViewById(R.id.calender);
        calenderView.setFirstDayOfWeek(Calendar.SATURDAY); // Bangladesh standard

        calenderView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(year,month,dayOfMonth);
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

                // dictionary to convert dayofweek to string
                // i don't know a better way to do this
                Dictionary days = new Hashtable();
                days.put(1, "Sunday");
                days.put(2, "Monday");
                days.put(3, "Tuesday");
                days.put(4, "Wednesday");
                days.put(5, "Thursday");
                days.put(6, "Friday");
                days.put(7, "Saturday");



                SELECTED_DAY = days.get(dayOfWeek).toString().toUpperCase();
                Toast.makeText(ViewRooms.this, "->" + days.get(dayOfWeek), Toast.LENGTH_SHORT).show();
                Query myQuery = databaseReference.child("routine").orderByChild("day").equalTo(SELECTED_DAY);
                myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        showData(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });




        /*
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
                Log.d(TAG, "onDataChange: METHOD IS CALLED");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

         */



    }

    private void showData(DataSnapshot dataSnapshot){
        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();

        Log.d(TAG, "value: " + map);
        /*
        ListView listView = findViewById(R.id.class_list);
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add(map.toString());
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, R.layout.lists, arrayList);
        listView.setAdapter(arrayAdapter);
        */
        ArrayList<String> arrayList = new ArrayList();
        EmptyRoom emptyRoom = new EmptyRoom();

        for (DataSnapshot ds : dataSnapshot.getChildren()){


            /*
            List<String> day = ds.getValue(Day.class).getClasses().getPeriod();
            //day.setDay(ds.getValue(Day.class).getClasses().getPeriod());
            ArrayList<String> arrayList = new ArrayList<>();

                arrayList.add(temp.getDay() + "");

             */

            emptyRoom.setRooms(ds.getValue(EmptyRoom.class).getRooms());
            emptyRoom.setPeriod(ds.getValue(EmptyRoom.class).getPeriod());


            try {
                String x = "";
                if (emptyRoom.getPeriod().equals("1")){
                    x = "\t\t\t\t\t\t\t\t\t\t8.30 - 10.00\n";
                } else if (emptyRoom.getPeriod().equals("2")) x = "\t\t\t\t\t\t\t\t\t\t10.00 - 11.30\n";
                arrayList.add(x +ds.getValue(EmptyRoom.class).getRooms().toString().replaceAll(",", "\n").replace('[',' ').replace(']',' '));

               /*
                String date = "On " + ds.getValue(Day.class).getDay() +" NOv at period no "+ ds.getValue(Day.class).getClasses().getPeriod() ;
                arrayList.add(date + " " +ds.getValue(Day.class).getClasses().getRoom302());

                */
            } catch (Exception e){

            } finally {

            }


        }
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, R.layout.lists, arrayList);
        ListView listView = findViewById(R.id.class_list);
        listView.setAdapter(arrayAdapter);

        Button roomBook = findViewById(R.id.roomBook);

        roomBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("reservation");
                Reservation reservation = new Reservation(23, "605", 1);
                databaseReference.push().setValue(reservation);

            }
        });

    }


}
