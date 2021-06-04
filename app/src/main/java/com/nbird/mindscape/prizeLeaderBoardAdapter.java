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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class prizeLeaderBoardAdapter extends RecyclerView.Adapter<prizeLeaderBoardAdapter.MyViewHolder> {
    private List<prizePlayerDataHolder> mData;

    public prizeLeaderBoardAdapter(List<prizePlayerDataHolder> mData){
        this.mData=mData;
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.prize_leaderboard_asset,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder( final MyViewHolder holder, final int position) {

        holder.setData(mData.get(position).getUpiId()
                ,mData.get(position).getHighestScore()
                ,mData.get(position).getUID()
                ,mData.get(position).getSet()
                ,mData.get(position).getImg()
                ,mData.get(position).getUserName()
        ,mData.get(position).getAttempts());


    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView username;
        TextView score;
        ImageView imageUrl1;

        public MyViewHolder(View itemView){
            super(itemView);

            username=(TextView) itemView.findViewById(R.id.username);
            score=(TextView) itemView.findViewById(R.id.score);
            imageUrl1=(ImageView) itemView.findViewById(R.id.pic);

        }

        public void setData(String upiID, int score,String uid ,int setNumber,String Image,String username,int attempts) {

            Glide.with(itemView.getContext())
                    .load(Image)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                    .into(imageUrl1);

            this.username.setText(username+" ");
            this.score.setText(String.valueOf("Highest Score : "+score+" "));

        }



    }


}
