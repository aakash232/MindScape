package com.nbird.mindscape;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class roomListAdapter extends RecyclerView.Adapter<roomListAdapter.viewholder> {
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    int num;
    private List<roomDataHolder> listItem;
    String hostUid;
    Context context;
    public roomListAdapter(List<roomDataHolder> listItem, tournamentChoiceActicity tournamentChoiceActicity) {
        this.listItem = listItem;
        this.context=tournamentChoiceActicity;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tournament_joining_listview,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, final int position) {
        holder.name.setText(" "+listItem.get(position).getHostName()+" ");
        holder.numTextView.setText(" "+listItem.get(position).getNumberOfPlayers()+"/4 ");

        holder.setData(listItem.get(position).getHostImageUrl());
        int manu;
        manu=listItem.get(position).getMode();
        if(manu==0){
            holder.mode.setText(" |  M : Normal");
        }else if(manu==1){
            holder.mode.setText(" |  M : Picture");
        }else if(manu==2){
            holder.mode.setText(" |  M : Buzzer Normal");
        }else{
            holder.mode.setText(" |  M : Buzzer Picture");
        }

        if (listItem.get(position).getTime()==45){

            holder.timeNumber.setText(" |  T : 4.5 Mins ");
        }else{
            holder.timeNumber.setText(" |  T : "+listItem.get(position).getTime()+" Mins ");
        }


        holder.questionNumber.setText("  Q : "+listItem.get(position).getQuestionNumber()+" ");

        holder.joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.joinButton.setEnabled(false);
                hostUid=listItem.get(position).getHostUid();

                myRef.child("room").child(String.valueOf(1)).child(listItem.get(position).getHostUid()).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            num=snapshot.getValue(Integer.class);
                            if(num<4){
                                if(num==1){
                                    num=2;
                                }else if(num==2){
                                    num=3;
                                }else if(num==3){
                                    num=4;
                                }
                               // num++;
                                myRef.child("room").child(String.valueOf(1)).child(listItem.get(position).getHostUid()).child("numberOfPlayers").setValue(num).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(num==4){

                                        }
                                        Intent intent = new Intent(context, tournamentLobbyActivity.class);
                                        intent.putExtra("hostUid", listItem.get(position).getHostUid());
                                        intent.putExtra("hostImage",listItem.get(position).getHostImageUrl());
                                        intent.putExtra("hostName",listItem.get(position).getHostName());
                                        intent.putExtra("isHost",0);
                                        intent.putExtra("roomCode",listItem.get(position).getRoomCode());
                                        intent.putExtra("Playernum",num);
                                        context.startActivity(intent);
                                        ((Activity)context).finish();

                                    }
                                });
                            }else{
                                Toast.makeText(context, "Room Is Full! Please Refresh", Toast.LENGTH_SHORT).show();
                            }


                        }catch (Exception e){
                            Toast.makeText(context, "Room Is Full! Please Refresh", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView proImage;
        private TextView numTextView,questionNumber,timeNumber,mode;
        LinearLayout linearLayout,linearLayoutall;
        Button joinButton;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.username);
            proImage=itemView.findViewById(R.id.proImage);
            numTextView=itemView.findViewById(R.id.num);
            linearLayout=itemView.findViewById(R.id.linearLayout);
            joinButton=itemView.findViewById(R.id.joinButton);
            questionNumber=(TextView) itemView.findViewById(R.id.questionnumber);
            timeNumber=(TextView) itemView.findViewById(R.id.time);
            mode=(TextView) itemView.findViewById(R.id.modequestion);

        }

        public void setData(String imageUrl) {
            Glide.with(itemView.getContext()).load(imageUrl).apply(RequestOptions
                    .bitmapTransform(new RoundedCorners(14)))
                    .into(proImage);

        }
    }
}

