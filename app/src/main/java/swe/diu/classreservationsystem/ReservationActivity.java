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

public class ReservationActivity extends AppCompatActivity {

    private static final String TAG = "RESERVATION ACTIVITY";

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("reservation");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);




        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> arrayList = new ArrayList();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Reservation reservation = new Reservation();
                    reservation.setDate(ds.getValue(Reservation.class).getDate());
                    reservation.setRoomNo(ds.getValue(Reservation.class).getRoomNo());


                    arrayList.add("Date: " +reservation.getDate() + "Period: 10.00-11.30" + "__ ROOM: " + reservation.getRoomNo());
                    Log.d(TAG, "onDataChange: " + reservation.getDate() + "___" +reservation.getRoomNo());
                    reservation.setStatus(2);



                    //databaseReference.child(ds.getKey()).setValue(reservation);
                }

                ArrayAdapter arrayAdapter = new ArrayAdapter<String>(ReservationActivity.this, R.layout.lists, arrayList);
                ListView listView = findViewById(R.id.list);
                listView.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
