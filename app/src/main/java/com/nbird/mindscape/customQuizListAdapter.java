package com.nbird.mindscape;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

import java.util.List;

public class customQuizListAdapter extends RecyclerView.Adapter<customQuizListAdapter.viewholder> {

    private List<customQuizPropertiesSetter> listItem;
    Context context;
    int position123;
    public customQuizListAdapter(List<customQuizPropertiesSetter> listItem, int position123, Context context) {
        this.listItem = listItem;
        this.position123=position123;
        this.context=context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category_show_asset,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, final int position) {




        holder.setData(listItem.get(position).getQuizName(),listItem.get(position).getTimeDuration(),listItem.get(position).getExpertLL(),
                listItem.get(position).getSwapLL(),listItem.get(position).getAudienceLL(),listItem.get(position).getFiftyfiftyLL(),
                listItem.get(position).getQuizCode(),listItem.get(position).getQuizPrivacy(),listItem.get(position).getUsename(),
                listItem.get(position).getRate(),listItem.get(position).getReport(),listItem.get(position).getDis(),
                listItem.get(position).getProPic(),listItem.get(position).getKey(),listItem.get(position).getNumberOfTimesPlayed());



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(context, R.raw.navclick);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });


                Intent intent=new Intent(context,customPublicQuiz.class);

                intent.putExtra("quizName",listItem.get(position).getQuizName());
                intent.putExtra("privacy",1);
                intent.putExtra("time",listItem.get(position).getTimeDuration());
                intent.putExtra("audienceLL",listItem.get(position).getAudienceLL());
                intent.putExtra("fiftyfiftyLL",listItem.get(position).getFiftyfiftyLL());
                intent.putExtra("expertLL",listItem.get(position).getExpertLL());
                intent.putExtra("key",listItem.get(position).getKey());
                intent.putExtra("category",position123);
                intent.putExtra("rate",listItem.get(position).getRate());
                intent.putExtra("myKey",listItem.get(position).getMyKey());
                intent.putExtra("numberOfTimesPlayed",listItem.get(position).getNumberOfTimesPlayed());
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });






    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        private TextView username,quizName,quizDis,time,timesPlayed;
        private ImageView picPro;
        LinearLayout linearLayoutexpert,linearLayoutAudience,linearLayoutfiftyfifty;
        LottieAnimationView anim1,anim2,anim3,anim4,anim5;
        CardView cardView;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.username);
            quizName=itemView.findViewById(R.id.quizName);
            quizDis=itemView.findViewById(R.id.quizDis);
            time=itemView.findViewById(R.id.time);
            timesPlayed=itemView.findViewById(R.id.timesPlayed);

            cardView=itemView.findViewById(R.id.cardView4);

            linearLayoutexpert=itemView.findViewById(R.id.linearLayoutexpert);
            linearLayoutAudience=itemView.findViewById(R.id.linearLayoutAudience);
            linearLayoutfiftyfifty=itemView.findViewById(R.id.linearLayoutfiftyfifty);

            anim1=itemView.findViewById(R.id.anim1);
            anim2=itemView.findViewById(R.id.anim2);
            anim3=itemView.findViewById(R.id.anim3);
            anim4=itemView.findViewById(R.id.anim4);
            anim5=itemView.findViewById(R.id.anim5);

            picPro=itemView.findViewById(R.id.pic);




        }

        public void setData(final String quizName, final int duration, final int expertll, int swapLL, final int audienceLL, final int fiftyfiftyLL, int quizCode, int quizPrivacy, final String username, int rate, final int report, String dis, String proPic, final String key,int numberOfTimesPlayed) {




            Glide.with(itemView.getContext()).load(proPic).into(picPro);
            this.quizName.setText(quizName);
            this.username.setText(username);
            this.quizDis.setText(dis);
            this.timesPlayed.setText("Number Of Times Played : "+numberOfTimesPlayed);

            this.time.setText("Time : "+duration+" Mins");

            if(expertll==1){
                linearLayoutexpert.setAlpha(0.7f);
            }

            if(audienceLL==1){
                linearLayoutAudience.setAlpha(0.7f);
            }

            if(fiftyfiftyLL==1){
                linearLayoutfiftyfifty.setAlpha(0.7f);
            }
            int finalRating = 0;
            if(numberOfTimesPlayed!=0){
                finalRating=rate/numberOfTimesPlayed;
            }
            

            switch (finalRating){
                case 1:
                    anim5.setVisibility(View.VISIBLE);
                    anim5.setAnimation(R.raw.staranim);
                    anim5.playAnimation();
                    anim5.loop(true);break;
                case 2:
                    anim4.setVisibility(View.VISIBLE);
                    anim4.setAnimation(R.raw.staranim);
                    anim4.playAnimation();
                    anim4.loop(true);
                    anim5.setVisibility(View.VISIBLE);
                    anim5.setAnimation(R.raw.staranim);
                    anim5.playAnimation();
                    anim5.loop(true);break;
                case 3:
                    anim3.setVisibility(View.VISIBLE);
                    anim3.setAnimation(R.raw.staranim);
                    anim3.playAnimation();
                    anim3.loop(true);
                    anim4.setVisibility(View.VISIBLE);
                    anim4.setAnimation(R.raw.staranim);
                    anim4.playAnimation();
                    anim4.loop(true);
                    anim5.setVisibility(View.VISIBLE);
                    anim5.setAnimation(R.raw.staranim);
                    anim5.playAnimation();
                    anim5.loop(true);break;
                case 4:
                    anim2.setVisibility(View.VISIBLE);
                    anim2.setAnimation(R.raw.staranim);
                    anim2.playAnimation();
                    anim2.loop(true);
                    anim3.setVisibility(View.VISIBLE);
                    anim3.setAnimation(R.raw.staranim);
                    anim3.playAnimation();
                    anim3.loop(true);
                    anim4.setVisibility(View.VISIBLE);
                    anim4.setAnimation(R.raw.staranim);
                    anim4.playAnimation();
                    anim4.loop(true);
                    anim5.setVisibility(View.VISIBLE);
                    anim5.setAnimation(R.raw.staranim);
                    anim5.playAnimation();
                    anim5.loop(true);break;
                case 5:
                    anim1.setVisibility(View.VISIBLE);
                    anim1.setAnimation(R.raw.staranim);
                    anim1.playAnimation();
                    anim1.loop(true);
                    anim2.setVisibility(View.VISIBLE);
                    anim2.setAnimation(R.raw.staranim);
                    anim2.playAnimation();
                    anim2.loop(true);
                    anim3.setVisibility(View.VISIBLE);
                    anim3.setAnimation(R.raw.staranim);
                    anim3.playAnimation();
                    anim3.loop(true);
                    anim4.setVisibility(View.VISIBLE);
                    anim4.setAnimation(R.raw.staranim);
                    anim4.playAnimation();
                    anim4.loop(true);
                    anim5.setVisibility(View.VISIBLE);
                    anim5.setAnimation(R.raw.staranim);
                    anim5.playAnimation();
                    anim5.loop(true);break;

            }

         /*   itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(itemView.getContext(),customPublicQuiz.class);

                    intent.putExtra("quizName",quizName);
                    intent.putExtra("privacy",1);
                    intent.putExtra("time",duration);
                    intent.putExtra("audienceLL",audienceLL);
                    intent.putExtra("fiftyfiftyLL",fiftyfiftyLL);
                    intent.putExtra("expertLL",expertll);
                    intent.putExtra("key",key);
                    intent.putExtra("category",position);
                    itemView.getContext().startActivity(intent);
                }
            });*/


        }
    }
}

