package swe.diu.classreservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;



public class BookARoom extends AppCompatActivity{

    TextView room;
    private static String ASKED_ROOM;
    Reservation reservation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_aroom);
        room = findViewById(R.id.dateTime);
        Intent i = getIntent();
        final String[] rooms = i.getStringArrayExtra("EXTRA_ROOMS");
        final String date = i.getStringExtra("DATE");
        final String period = i.getStringExtra("PERIOD");
        final String RsvrEmail = i.getStringExtra("email");
        Log.i("RCVR EMAIL", "onCreate: " + RsvrEmail);
        Spinner s = findViewById(R.id.roomSpinner);
        Button set = findViewById(R.id.askReservation);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , rooms);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(BookARoom.this, rooms[position], Toast.LENGTH_SHORT).show();
                room.setText(rooms[position]);
                ASKED_ROOM = rooms[position].trim(); // Extra whitespace left after spliting
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        TextView t = findViewById(R.id.dateTime);


        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservation = new Reservation(date, period, ASKED_ROOM, ROOM_STATUS.PENDING.getValue(),RsvrEmail);
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("reservation");
                databaseReference.push().setValue(reservation);

            }
        });


    }


}
