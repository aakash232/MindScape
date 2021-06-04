package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class tournamentChoiceActicity extends AppCompatActivity {
    CardView onlineButton;
    Button  createButton,cancelButton;
    AlertDialog.Builder builder1;
    View view1;
    ShimmerFrameLayout shimmer;
    RecyclerView recyclerView;
     List<roomDataHolder> listRoom;
     FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    CardView refreshButton;
    int roomCode;
    Button joinPrivateRoom;
    Button joinButton;
    TextInputEditText password;
    String roomCodeString;
    int codeInteger;
    roomDataHolder s;
    int mam=0;
    private List<roomDataHolder> list123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_choice_acticity);

        builder1 = new AlertDialog.Builder(tournamentChoiceActicity.this, R.style.AlertDialogTheme);
        view1 = LayoutInflater.from(tournamentChoiceActicity.this).inflate(R.layout.tournment_join_create_layout, (ConstraintLayout) findViewById(R.id.layoutDialogContainer), false);


        onlineButton=(CardView) findViewById(R.id.onlineButton);
        createButton=(Button) view1.findViewById(R.id.createButton);
        shimmer=(ShimmerFrameLayout) view1.findViewById(R.id.shimmer);
        refreshButton=(CardView) view1.findViewById(R.id.refresh);
        cancelButton=(Button) view1.findViewById(R.id.cancelButton);
        joinPrivateRoom=(Button) view1.findViewById(R.id.joinPrivateRoom);
        list123 = new ArrayList<>();

        onlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   roomSelectionDialog();
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomListView();
            }
        });

        joinPrivateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                privateRoomJoinAlertDialog();
            }
        });


    }

    public void privateRoomJoinAlertDialog(){
        AlertDialog.Builder builderJoin=new AlertDialog.Builder(tournamentChoiceActicity.this,R.style.AlertDialogTheme);
        View viewJoin=LayoutInflater.from(tournamentChoiceActicity.this).inflate(R.layout.join_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer),false);
        builderJoin.setView(viewJoin);
        builderJoin.setCancelable(false);

        joinButton=(Button) viewJoin.findViewById(R.id.joinButton1);
        password=(TextInputEditText) viewJoin.findViewById(R.id.password);

        final AlertDialog alertDialog=builderJoin.create();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
        s=new roomDataHolder();
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomCodeString=password.getText().toString();
                codeInteger= Integer.parseInt(roomCodeString);

                    myRef.child("room").child(String.valueOf(1)).orderByChild("roomCode").equalTo(codeInteger).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try{
                                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                                    list123.add(dataSnapshot1.getValue(roomDataHolder.class));
                                }
                               // s=snapshot.getValue(roomDataHolder.class);
                                int num=list123.get(0).getNumberOfPlayers();
                                if(num<4){
                                    num++;
                                    final String sos=list123.get(0).getHostName();
                                    mam=list123.get(0).getNumberOfPlayers();
                                    final int finalNum = num;
                                    myRef.child("room").child(String.valueOf(1)).child(list123.get(0).getHostUid()).child("numberOfPlayers").setValue(num).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Intent intent=new Intent(tournamentChoiceActicity.this,tournamentLobbyActivity.class);
                                            intent.putExtra("hostUid",list123.get(0).getHostUid());
                                            intent.putExtra("hostImage",list123.get(0).getHostImageUrl());
                                            intent.putExtra("hostName",sos);
                                            intent.putExtra("isHost",0);
                                            intent.putExtra("roomCode",list123.get(0).getRoomCode());
                                            intent.putExtra("Playernum", finalNum);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                                }else {
                                    Toast.makeText(tournamentChoiceActicity.this, "Room Is Full!", Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e){
                                if(mam==0){
                                    password.setError("Room Password Is Wrong");
                                }else{
                                    alertDialog.dismiss();
                                }
                            }

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    myRef.child("room").child(String.valueOf(0)).orderByChild("roomCode").equalTo(codeInteger).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try{
                                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                                    list123.add(dataSnapshot1.getValue(roomDataHolder.class));
                                }
                              //  s=snapshot.getValue(roomDataHolder.class);

                                int num=list123.get(0).getNumberOfPlayers();
                                if(num<4){
                                    num++;
                                    final String sos=list123.get(0).getHostName();
                                    mam=list123.get(0).getNumberOfPlayers();
                                    final int finalNum = num;
                                    myRef.child("room").child(String.valueOf(0)).child(sos).child("numberOfPlayers").setValue(num).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Intent intent=new Intent(tournamentChoiceActicity.this,tournamentLobbyActivity.class);
                                            intent.putExtra("hostUid",list123.get(0).getHostUid());
                                            intent.putExtra("hostImage",list123.get(0).getHostImageUrl());
                                            intent.putExtra("hostName",sos);
                                            intent.putExtra("isHost",0);
                                            intent.putExtra("roomCode",list123.get(0).getRoomCode());
                                            intent.putExtra("Playernum", finalNum);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });

                                }else {
                                    Toast.makeText(tournamentChoiceActicity.this, "Room Is Full!", Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e){
                                if(mam==0){
                                    password.setError("Room Password Is Wrong");
                                }else{
                                    alertDialog.dismiss();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });




            }
        });

    }

    public void playerPusher(final int codeInteger){



    }


    @Override
    public void onResume() {
        super.onResume();
        shimmer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        shimmer.stopShimmerAnimation();
        super.onPause();
    }



    public void roomSelectionDialog() {

        builder1.setView(view1);
        builder1.setCancelable(false);
        final AlertDialog alertDialog = builder1.create();
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

        roomListView();

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 roomCodeGenerator();
                 Intent intent=new Intent(tournamentChoiceActicity.this,tournamentLobbyActivity.class);
                 intent.putExtra("Playernum",1);
                 intent.putExtra("roomCode",roomCode);
                 startActivity(intent);
                 alertDialog.dismiss();
                 finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(tournamentChoiceActicity.this,mainMenuActivity.class);
                startActivity(intent);
                alertDialog.dismiss();
                finish();
            }
        });

    }

    public void roomCodeGenerator(){
        Random rand = new Random();

        roomCode = rand.nextInt(999999)+1;
        myRef.child("Lobby").orderByChild("roomCode").equalTo(roomCode).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue(Integer.class)!=null){
                    roomCodeGenerator();
                }
                else{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("roomCode").setValue(roomCode).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            myEdit.putInt("codeRemove", roomCode);
                            myEdit.commit();

                        }
                    });

                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("gameStarter").setValue(0).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void roomListView(){
        recyclerView = view1.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(recyclerView.VERTICAL);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        listRoom = new ArrayList<>();


        final roomListAdapter categoryAdapter = new roomListAdapter(listRoom,this);
        recyclerView.setAdapter(categoryAdapter);

        myRef.child("room").child("1").orderByChild("numberOfPlayers").limitToFirst(30).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    listRoom.add(dataSnapshot1.getValue(roomDataHolder.class));
                }
                if(listRoom.size()==0){
                    Toast.makeText(tournamentChoiceActicity.this, "No Rooms Available!Please Create A Room.", Toast.LENGTH_LONG).show();
                }
                categoryAdapter.notifyDataSetChanged();
                shimmer.stopShimmerAnimation();
                shimmer.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}