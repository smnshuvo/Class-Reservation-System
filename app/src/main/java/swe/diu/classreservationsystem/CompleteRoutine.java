package swe.diu.classreservationsystem;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CompleteRoutine {
    public final String TAG = "CR >> ";
    ArrayList<EmptyRoom> saturdayRooms,
                          sundayRooms,
                           mondayRooms,
                            tuesdayRooms,
                             wednesdayRooms,
                              thursdayRooms;

    public CompleteRoutine(ArrayList<EmptyRoom> saturdayRooms, ArrayList<EmptyRoom> sundayRooms, ArrayList<EmptyRoom> mondayRooms, ArrayList<EmptyRoom> tuesdayRooms, ArrayList<EmptyRoom> wednesdayRooms, ArrayList<EmptyRoom> thursdayRooms) {
        this.saturdayRooms = saturdayRooms;
        this.sundayRooms = sundayRooms;
        this.mondayRooms = mondayRooms;
        this.tuesdayRooms = tuesdayRooms;
        this.wednesdayRooms = wednesdayRooms;
        this.thursdayRooms = thursdayRooms;
    }

    public void printSaturday(){
        for (EmptyRoom x : sundayRooms){

            for (String s : x.rooms){
                Log.i(TAG, "printSaturday: "+ x.day + " -- "+ x.period +" >>" + s);
            }
        }
    }

    public void pushRoutine(){
        for (EmptyRoom e : saturdayRooms){
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("routine");
            databaseReference.push().setValue(e);


        }

        for (EmptyRoom e : sundayRooms){
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("routine");
            databaseReference.push().setValue(e);


        }

        for (EmptyRoom e : mondayRooms){
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("routine");
            databaseReference.push().setValue(e);


        }

        for (EmptyRoom e : tuesdayRooms){
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("routine");
            databaseReference.push().setValue(e);


        }

        for (EmptyRoom e : wednesdayRooms){
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("routine");
            databaseReference.push().setValue(e);


        }

        for (EmptyRoom e : thursdayRooms){
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("routine");
            databaseReference.push().setValue(e);


        }
    }
}
