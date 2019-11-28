package swe.diu.classreservationsystem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReservationRequestAdapter extends BaseAdapter {

    private static final String TAG = "R_ADAPTER CLASS";
    String[] requestBody;
    Context context;
    private LayoutInflater inflater;
    Reservation reservation;



    public ReservationRequestAdapter(String[] requestBody, Context context) {
        this.requestBody = requestBody;
        this.context = context;
    }

    @Override
    public int getCount() {
        return requestBody.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.reservation_request , parent , false);
        }

        TextView rDetails = (TextView) view.findViewById(R.id.rsr_detail); // Reservation Details
        rDetails.setText(requestBody[position]);
        Button yes, no;
        yes = (Button) view.findViewById(R.id.accept_rs); // Unnecessary type casting though
        yes.setTag(requestBody[position]);
        no = (Button) view.findViewById(R.id.reject_rs);
        no.setTag(requestBody[position]);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String realpos = (String) v.getTag();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("reservation");
                databaseReference.child("-LuIUkqUcmNWJVZCYulH").child("status").setValue(2); // THIS IS A TEST

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Log.i(TAG, "Has the node? " + dataSnapshot.child("-LuIUkqUcmNWJVZCYulH").child("status"));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                // TO DO
                Toast.makeText(context, "CLICKED", Toast.LENGTH_SHORT).show();
            }
        });
    return view;
    }
}
