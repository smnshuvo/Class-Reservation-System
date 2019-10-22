package swe.diu.classreservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

public class IndexActivity extends AppCompatActivity {

    ImageView userPic;
    TextView userName;

    // Using this as the index page of our project
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        userPic = findViewById(R.id.profile_pic);
        userName = findViewById(R.id.user_name);
        Intent intent = getIntent();
        String displayName = intent.getStringExtra("proName");
        String proPicURL = intent.getStringExtra("proPic");
        userName.setText(displayName);




    }
}
