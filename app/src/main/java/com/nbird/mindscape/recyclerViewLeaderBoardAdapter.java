package com.nbird.mindscape;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class recyclerViewLeaderBoardAdapter extends RecyclerView.Adapter<recyclerViewLeaderBoardAdapter.MyViewHolder> {
    private List<leaderBoardHolder> mData;

    public recyclerViewLeaderBoardAdapter(List<leaderBoardHolder> mData){
        this.mData=mData;
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.leader_board_listview,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder( final MyViewHolder holder, final int position) {

        holder.setData(mData.get(position).getUsername()
                ,mData.get(position).getScore()
                ,mData.get(position).getTotalTime()
                ,mData.get(position).getCorrect()
                ,mData.get(position).getWrong()
                ,mData.get(position).getImageUrl()
        ,mData.get(position).getSumationScore());


    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView username;
        TextView score;
        TextView totalTime;
        TextView correctByWrong;
        ImageView imageUrl1;
        ImageView levelImageView;
        TextView levelTextView;

        public MyViewHolder(View itemView){
            super(itemView);

            username=(TextView) itemView.findViewById(R.id.username);
            score=(TextView) itemView.findViewById(R.id.score);
            totalTime=(TextView) itemView.findViewById(R.id.totalTime);
            correctByWrong=(TextView) itemView.findViewById(R.id.totalCorrectAnswer);
            imageUrl1=(ImageView) itemView.findViewById(R.id.pic);
            levelImageView=(ImageView) itemView.findViewById(R.id.batch);
            levelTextView=(TextView) itemView.findViewById(R.id.levelText123);
        }

        public void setData(String username, int score,int totalTime ,int correct,int wrong,String imageUrl,int sumationScore) {
            Glide.with(itemView.getContext()).load(imageUrl).into(imageUrl1);
            this.username.setText(username+" ");
            this.score.setText(String.valueOf("Score : "+score+" "));

            if(totalTime<60){
                this.totalTime.setText(String.valueOf("Total Time : "+totalTime+" sec "));
            }else{
                int minutes=totalTime/60;
                int sec=totalTime%60;
                this.totalTime.setText(String.valueOf("Total Time : "+minutes+" min "+sec+" sec "));
            }

            if(sumationScore<100000){
                if(sumationScore<50000){
                    levelTextView.setText(" Lv. 1 ");
                }else{
                    levelTextView.setText(" Lv. 2 ");
                }
            }else{
                int holder;
                holder=sumationScore/50000;
                levelTextView.setText(" Lv. "+holder+" ");
            }


          if(sumationScore<50000){
              levelImageView.setBackgroundResource(R.drawable.blackiron);
          }else if(sumationScore<200000){
              levelImageView.setBackgroundResource(R.drawable.bronze);
          }else if(sumationScore<500000){
              levelImageView.setBackgroundResource(R.drawable.silver);
            }else if(sumationScore<1000000){
              levelImageView.setBackgroundResource(R.drawable.gold);
          }else if(sumationScore<1500000){
              levelImageView.setBackgroundResource(R.drawable.platinum);
          }else if(sumationScore<2500000){
              levelImageView.setBackgroundResource(R.drawable.diamond);
          }else if(sumationScore<4000000){
              levelImageView.setBackgroundResource(R.drawable.amethyst);
          }else if(sumationScore<6000000){
              levelImageView.setBackgroundResource(R.drawable.master);
          }else{
              levelImageView.setBackgroundResource(R.drawable.king);
          }


            this.correctByWrong.setText("Correct/Wrong : "+correct+"/"+wrong+" ");
        }



    }


}
