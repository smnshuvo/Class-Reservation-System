 package swe.diu.classreservationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

 public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth; // firebase authentication
    GoogleSignInClient mGoogleSignInClient;
    SignInButton signInButton; // Google Sign in

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signInButton = findViewById(R.id.gsignin);
        // GOOGLE SIGN IN PART
        auth = FirebaseAuth.getInstance(); // an instance variable for firebase authentication
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });
    }


     private void signIn(){
         Intent signInIntent = mGoogleSignInClient.getSignInIntent();
         startActivityForResult(signInIntent, 101);
     }

     @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);

         // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
         if (requestCode == 101) {
             Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
             try {
                 // Google Sign In was successful, authenticate with Firebase
                 GoogleSignInAccount account = task.getResult(ApiException.class);
                 firebaseAuthWithGoogle(account);
             } catch (ApiException e) {
                 // Google Sign In failed, update UI appropriately

                 // ...
             }
         }
     }

     /* @param {Object} acct - Account to pass for signing in */
     private void firebaseAuthWithGoogle(GoogleSignInAccount acct){
         AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
         auth.signInWithCredential(credential)
                 .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()) {
                             // Sign in success, update UI with the signed-in user's information
                             //Log.d(TAG, "signInWithCredential:success");
                             FirebaseUser user = auth.getCurrentUser();
                             Toast.makeText(LoginActivity.this, "HELLO " + user.getDisplayName(), Toast.LENGTH_LONG).show();
                             Intent intent = new Intent(LoginActivity.this, IndexActivity.class);
                             intent.putExtra("proName", user.getDisplayName()); // sending User Full Name to the next intent
                             intent.putExtra("proPic", user.getPhotoUrl().toString()); // Converting the Uri to string
                             startActivity(intent);
                         } else {
                             // If sign in fails, display a message to the user.
                             //Log.w(TAG, "signInWithCredential:failure", task.getException());
                             Toast.makeText(LoginActivity.this, "LOG IN FAILED!", Toast.LENGTH_SHORT).show();
                         }

                         // ...
                     }
                 });
     }

}
