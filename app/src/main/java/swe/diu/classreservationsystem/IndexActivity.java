package swe.diu.classreservationsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.net.URL;

import static swe.diu.classreservationsystem.LoginActivity.auth;
import static swe.diu.classreservationsystem.LoginActivity.mGoogleSignInClient;

public class IndexActivity extends AppCompatActivity {

    ImageView userPic;
    TextView userName;
    Button signOurBurron;
    Button routineSetter;

    // Using this as the index page of our project
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        userPic = findViewById(R.id.profile_pic);
        userName = findViewById(R.id.user_name);
        signOurBurron = findViewById(R.id.sign_out);
        routineSetter = findViewById(R.id.set_routine);

        final Intent intent = getIntent(); // current Intent
        String displayName = intent.getStringExtra("proName");
        String proPicURL = intent.getStringExtra("proPic");
        userName.setText(displayName);
        loadProfilePicFromURL(proPicURL); // to load my profile picture from URL


        signOurBurron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    auth.signOut();
                    mGoogleSignInClient.signOut();
                } catch (Exception e){

                } finally {
                    Intent loginIntent = new Intent(IndexActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                }


            }
        });


        routineSetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IndexActivity.this , RoutineSetter.class);
                // do i need to finish the existing activity first? I guess NO
                startActivity(i);
            }
        });


    }


    private void loadProfilePicFromURL(String URL){
        Picasso.get()
                .load(URL)
                .error(R.drawable.ppic)
                .into(userPic, new Callback() {
                    @Override
                    public void onSuccess() {
                        // pasted from stackoverflow
                        // to round the image
                        Bitmap imageBitmap = ((BitmapDrawable) userPic.getDrawable()).getBitmap();
                        RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
                        imageDrawable.setCircular(true);
                        imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                        userPic.setImageDrawable(imageDrawable);
                    }

                    @Override
                    public void onError(Exception e) {
                        userPic.setImageResource(R.drawable.ppic);
                        // default image

                    }
                });
    }
}
