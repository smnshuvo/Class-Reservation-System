package swe.diu.classreservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TempActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        EmptyRoom emptyRoom = new EmptyRoom();
        emptyRoom.setDay("SATURDAY");
        emptyRoom.setPeriod("2");


        ArrayList<String> rooms = new ArrayList<String>();
        rooms.add("607");
        rooms.add("504");
        rooms.add("406");
        rooms.add("502");
        rooms.add("305");

        emptyRoom.setRooms(rooms);


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("routine"); // .child() is not needed
        databaseReference.push().setValue(emptyRoom);

    }
}
