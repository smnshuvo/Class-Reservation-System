package swe.diu.classreservationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ViewRooms extends AppCompatActivity {
private static final String TAG = "DATABASE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rooms);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("routine");
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

    }
}
