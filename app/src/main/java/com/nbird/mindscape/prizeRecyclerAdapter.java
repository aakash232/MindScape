package com.nbird.mindscape;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class prizeRecyclerAdapter extends RecyclerView.Adapter<prizeRecyclerAdapter.viewholder> {

    private List<prizeRecyclerHolder> listItem;
    Context context;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    String userName="";
    String proUrl="";
    String UPIuser;
    int highestScore,attempts;
    int isPlayed;
    String uid;
    AlertDialog alertDialog1;
    public prizeRecyclerAdapter(Context context, List<prizeRecyclerHolder> listItem, AlertDialog alertDialog1) {
        this.context=context;
        this.listItem = listItem;
        this.alertDialog1=alertDialog1;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.prize_category_asset,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        uid=mAuth.getCurrentUser().getUid();

        holder.setData(listItem.get(position).getPic(),listItem.get(position).getTextHead(),listItem.get(position).getSet(),listItem.get(position).getTextDis(),listItem.get(position).getType(),position);

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;
        CardView cardview_id;
        TextView dis;
        LinearLayout linear;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.textTitle);
            image=itemView.findViewById(R.id.img);
            dis=itemView.findViewById(R.id.textDis);
            cardview_id=itemView.findViewById(R.id.cardview_id);
            linear=itemView.findViewById(R.id.linear);

        }

        public void setData(String imageUrl, final String title, final int sets, String d, final int type, final int position) {

            Glide.with(itemView.getContext()).load(imageUrl).apply(RequestOptions
                    .bitmapTransform(new RoundedCorners(14)))
                    .into(image);
            Animation imgAnim1 = AnimationUtils.loadAnimation(context, R.anim.scaleincanim);
            image.setAnimation(imgAnim1);
            cardview_id.setAnimation(imgAnim1);
            this.title.setText(title);
            this.dis.setText(d);

            myRef.child("PrizeModePlayerData").child(String.valueOf(position+1)).child(mAuth.getCurrentUser().getUid()).child("isPlayed").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            isPlayed=snapshot.getValue(Integer.class);
                            if(isPlayed==1){
                                linear.setAlpha(0.7f);
                                cardview_id.setAlpha(0.8f);
                                itemView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Toast.makeText(context, "You Have Played This Package!!!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }catch (Exception e){
                            isPlayed=0;
                            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("UPI").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    try {
                                        UPIuser=snapshot.getValue(String.class);
                                        if(UPIuser.isEmpty()){

                                        }

                                        allImpFuntion(sets);

                                        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                try {
                                                    userName=snapshot.getValue(String.class);
                                                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            try{
                                                                proUrl=snapshot.getValue(String.class);
                                                                itemView.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View view) {
                                                                        prizePlayerDataHolder s1;
                                                                        s1=new prizePlayerDataHolder(UPIuser,highestScore,mAuth.getCurrentUser().getUid(),sets,proUrl,userName,attempts,1);

                                                                        myRef.child("PrizeModePlayerData").child(String.valueOf(sets)).child(mAuth.getCurrentUser().getUid()).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                            @Override
                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                if(type==1){
                                                                                    alertDialog1.dismiss();
                                                                                    Intent intent=new Intent(itemView.getContext(),prizeQuizMain.class);
                                                                                    intent.putExtra("sets",sets);
                                                                                    intent.putExtra("type",type);
                                                                                    itemView.getContext().startActivity(intent);
                                                                                }else if(type==2){
                                                                                    alertDialog1.dismiss();
                                                                                    Intent intent=new Intent(itemView.getContext(),prizePictureQuiz.class);
                                                                                    intent.putExtra("sets",sets);
                                                                                    intent.putExtra("type",type);
                                                                                    itemView.getContext().startActivity(intent);
                                                                                }


                                                                            }
                                                                        });
                                                                    }
                                                                });

                                                            }catch (Exception e){

                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                        }
                                                    });
                                                }catch (Exception e){

                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }catch (Exception e){
                                        itemView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                alertDialogForUpiId(itemView,sets,type,position);
                                            }
                                        });

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });





        }
    }

    public void allImpFuntion(int sets){

        myRef.child("PrizeModePlayerData").child(String.valueOf(sets)).child(mAuth.getCurrentUser().getUid()).child("highestScore").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    highestScore=snapshot.getValue(Integer.class);
                }catch (Exception e){
                    highestScore=-100;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("PrizeModePlayerData").child(String.valueOf(sets)).child(mAuth.getCurrentUser().getUid()).child("attempts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                try {
                    attempts=snapshot.getValue(Integer.class);
                }catch (Exception e){
                    attempts=0;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void alertDialogForUpiId(final View itemView, final int sets, final int type, final int position){
        AlertDialog.Builder builder=new AlertDialog.Builder(context,R.style.AlertDialogTheme);

        final View view1= LayoutInflater.from(context).inflate(R.layout.prize_upi_asker_layout,(ConstraintLayout) itemView.findViewById(R.id.layoutDialogContainer));
        builder.setView(view1);
        builder.setCancelable(true);

        final TextInputEditText upiTextView=(TextInputEditText) view1.findViewById(R.id.upiTextView);
        Button buttonNo=(Button) view1.findViewById(R.id.buttonNo);
        Button buttonYes=(Button) view1.findViewById(R.id.buttonYes);

        final AlertDialog alertDialog=builder.create();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
        allImpFuntion(sets);



        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try {
                            userName=snapshot.getValue(String.class);
                            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    try {
                                       proUrl=snapshot.getValue(String.class);
                                        allImpFuntion(sets);
                                        prizePlayerDataHolder s1;
                                        s1=new prizePlayerDataHolder("",-100,uid,sets,proUrl,userName,0,1);


                                        myRef.child("PrizeModePlayerData").child(String.valueOf(sets)).child(mAuth.getCurrentUser().getUid()).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(type==1){
                                                    alertDialog.dismiss();
                                                    Intent intent=new Intent(itemView.getContext(),prizeQuizMain.class);
                                                    intent.putExtra("sets",sets);
                                                    intent.putExtra("type",type);
                                                    itemView.getContext().startActivity(intent);
                                                }else if(type==2){
                                                    alertDialog.dismiss();
                                                    Intent intent=new Intent(itemView.getContext(),prizePictureQuiz.class);
                                                    intent.putExtra("sets",sets);
                                                    intent.putExtra("type",type);
                                                    itemView.getContext().startActivity(intent);
                                                }

                                            }
                                        });

                                    }catch (Exception e){

                                    }


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!upiTextView.getText().toString().isEmpty()){
                    final String upiString=upiTextView.getText().toString();

                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("UPI").setValue(upiString).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });

                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try {
                                userName=snapshot.getValue(String.class);
                                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        try {
                                            proUrl=snapshot.getValue(String.class);
                                            //allImpFuntion(sets);
                                            prizePlayerDataHolder s1;

                                            s1=new prizePlayerDataHolder(upiString,-100,uid,sets,proUrl,userName,0,1);


                                            myRef.child("PrizeModePlayerData").child(String.valueOf(sets)).child(mAuth.getCurrentUser().getUid()).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {


                                                    if(type==1){
                                                        alertDialog.dismiss();
                                                        Intent intent=new Intent(itemView.getContext(),prizeQuizMain.class);
                                                        intent.putExtra("sets",sets);
                                                        intent.putExtra("type",type);
                                                        itemView.getContext().startActivity(intent);
                                                    }else if(type==2){
                                                        alertDialog.dismiss();
                                                        Intent intent=new Intent(itemView.getContext(),prizePictureQuiz.class);
                                                        intent.putExtra("sets",sets);
                                                        intent.putExtra("type",type);
                                                        itemView.getContext().startActivity(intent);
                                                    }

                                                }
                                            });

                                        }catch (Exception e){

                                        }


                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }catch (Exception e){

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }else{
                    upiTextView.setError("Fields Cannot Be Empty");
                }

            }
        });



    }
}
