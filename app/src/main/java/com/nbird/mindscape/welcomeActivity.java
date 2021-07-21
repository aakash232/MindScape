package com.nbird.mindscape;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import java.util.Random;

public class welcomeActivity extends AppCompatActivity {

    SignInButton googsignin;
    Dialog loadingDialog;
    int RC_SIGN_IN=1;
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    int firstTime=0;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_user = database.getReference("User");
    private GoogleSignInClient mGoogleSignInClient;
    String personEmail;
    int onevsoneOnlineFinder=0;
    String imageurl="https://firebasestorage.googleapis.com/v0/b/paper-wind.appspot.com/o/BydefalutPic%2Fdefaultpropic.png?alt=media&token=f655727d-9740-4ac9-9ba2-f53ea02dc778";
    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);

        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null){
            startActivity(new Intent(getApplicationContext(),mainMenuActivity.class));
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            finish();
        }




        googsignin=(SignInButton) findViewById(R.id.googlesignin);


        Button loginButton= (Button)findViewById(R.id.login);
        final Button registerButton= (Button)findViewById(R.id.registersmallbutton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(welcomeActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                Intent loginIntent=new Intent(welcomeActivity.this,loginActivity.class);
                startActivity(loginIntent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(welcomeActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                Intent registerIntent=new Intent(welcomeActivity.this,registerActivity.class);
                startActivity(registerIntent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
            }
        });

        createRequest();
        googsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(welcomeActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                loadingDialog=new Dialog(welcomeActivity.this);

                loadingDialog.setContentView(R.layout.loading_screen);
                loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                loadingDialog.setCancelable(true);
                loadingDialog.show();




                signIn();
            }
        });

    }

    public void createRequest(){
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(getApplicationContext());
                            personEmail = account.getEmail();
                            final SharedPreferences mailreminder = getBaseContext().getSharedPreferences("mailreminder123", 0);
                            final SharedPreferences.Editor editormailreminder = mailreminder.edit();
                            editormailreminder.putString("123", personEmail);
                            editormailreminder.commit();

                            table_user.child(mAuth.getCurrentUser().getUid()).child("personal").child("firstTime").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    try {
                                        firstTime =  snapshot.getValue(Integer.class);

                                    } catch (Exception e) {
                                        if (firstTime == 0) {
                                            table_user.child(mAuth.getCurrentUser().getUid()).child("propic").setValue(imageurl).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                }
                                            });

                                            Random rand = new Random();

                                            // Generate random integers in range 0 to 29

                                            final int setNumber = rand.nextInt(95000)+1;
                                            String so="Player"+setNumber;

                                            table_user.child(mAuth.getCurrentUser().getUid()).child("userName").setValue(so).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                }
                                            });




                                            firstTime = 1;
                                            User s1 = new User(firstTime,onevsoneOnlineFinder);







                                            table_user.child(mAuth.getCurrentUser().getUid()).child("personal").setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(getBaseContext(), "Logged In Successfully!", Toast.LENGTH_LONG).show();
                                                        Intent intent = new Intent(welcomeActivity.this, mainMenuActivity.class);
                                                        intent.putExtra("firstTime", 0);
                                                        startActivity(intent);
                                                        loadingDialog.dismiss();
                                                        overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                                        finish();
                                                    } else {
                                                        Toast.makeText(welcomeActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });
                                        }
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            Toast.makeText(getBaseContext(), "Logged In Successfully!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(welcomeActivity.this, mainMenuActivity.class);
                            intent.putExtra("firstTime", 1);
                            startActivity(intent);
                            loadingDialog.dismiss();
                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                            finish();




                        } else {

                            Toast.makeText(getBaseContext(), "Error!"+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            loadingDialog.dismiss();

                        }
                    }
                });
    }

}