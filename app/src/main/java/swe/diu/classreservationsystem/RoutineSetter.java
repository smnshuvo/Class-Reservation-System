package swe.diu.classreservationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;

public class RoutineSetter extends AppCompatActivity {
    EditText day, period;
    Button submitButton;
    DatabaseReference databaseReference;
    EditText r302, r304, r305, r306, r404, r405, r406, r501, r502, r504, r505, r601, r604, r605, r607;
    Routine today = new Routine();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_setter);
        day = findViewById(R.id.date);
        period = findViewById(R.id.period);
        submitButton = findViewById(R.id.submit);
        r302 = findViewById(R.id.room302);
        r304 = findViewById(R.id.room304);
        r305 = findViewById(R.id.room305);
        r306 = findViewById(R.id.room306);
        r404 = findViewById(R.id.room404);
        r405 = findViewById(R.id.room405);
        r406 = findViewById(R.id.room406);
        r501 = findViewById(R.id.room501);
        r502 = findViewById(R.id.room502);
        r504 = findViewById(R.id.room504);
        r505 = findViewById(R.id.room505);
        r601 = findViewById(R.id.room601);
        r604 = findViewById(R.id.room604);
        r605 = findViewById(R.id.room605);
        r607 = findViewById(R.id.room607);


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("routine"); // .child() is not needed

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int date = Integer.parseInt(day.getText().toString());
                getData();
                Day x = new Day(date, today);

                //  databaseReference.setValue(date);
                //databaseReference.child("test").setValue(date);
                //   databaseReference.push().setValue(date);
                databaseReference.push().setValue(x);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                        Log.d("OK", "value: " + map);
                        Toast.makeText(RoutineSetter.this, "INSERTED", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }

    private void getData() {
        try {

            today.setPeriod(Integer.parseInt(period.getText().toString()));
            today.setRoom302(r302.getText().toString());
            today.setRoom304(r304.getText().toString());
            today.setRoom305(r305.getText().toString());
            today.setRoom306(r306.getText().toString());
            today.setRoom404(r404.getText().toString());
            today.setRoom405(r405.getText().toString());
            today.setRoom406(r406.getText().toString());
            today.setRoom501(r501.getText().toString());
            today.setRoom502(r502.getText().toString());
            today.setRoom504(r504.getText().toString());
            today.setRoom505(r505.getText().toString());
            today.setRoom601(r601.getText().toString());
            today.setRoom604(r604.getText().toString());
            today.setRoom605(r605.getText().toString());
            today.setRoom607(r607.getText().toString());

        } catch (Exception e) {// Do nothing}


        }
    }
}
