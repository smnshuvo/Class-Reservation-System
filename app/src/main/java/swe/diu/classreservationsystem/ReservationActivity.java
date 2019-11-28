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
    // NAMING WAS A BIT WRONG
    // THIS ACTIVITY CONFIRMS RESERVATION


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
                    reservation.setPeriod(ds.getValue(Reservation.class).getPeriod());
                    reservation.setAskedBy(ds.getValue(Reservation.class).getAskedBy());


                    arrayList.add(reservation.getFormattedDetails());
                    Log.d(TAG, "onDataChange: " + reservation.getDate() + "___" +reservation.getRoomNo());
                    reservation.setStatus(2);



                    //databaseReference.child(ds.getKey()).setValue(reservation);
                }


                String requestBody[] = arrayList.toArray(new String[arrayList.size()]);
                ReservationRequestAdapter rAdapter = new ReservationRequestAdapter(requestBody,ReservationActivity.this);
                ListView listView = findViewById(R.id.list);
                listView.setAdapter(rAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
