package com.nbird.mindscape;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class customQuizMainMenuAdapter extends RecyclerView.Adapter<customQuizMainMenuAdapter.MyViewHolder> {
    private Context mContext;
    private List<customQuizMainMenuHolder> mData;
    int setter,a,b;

    public customQuizMainMenuAdapter(Context mContext,List<customQuizMainMenuHolder> mData,int setter){
        this.mContext=mContext;
        this.mData=mData;
        this.setter=setter;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.customquiz_card_layout,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.tv_exam_title.setText(mData.get(position).getTitle());
        holder.img_exam_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.exam_dis.setText(mData.get(position).getDis());




        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=position;
                Intent intent = new Intent(mContext, customCategoryListActivity.class);
                intent.putExtra("position", a + 1);
                mContext.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_exam_title;
        ImageView img_exam_thumbnail;
        TextView exam_dis;
        CardView cardView;


        public MyViewHolder(View itemView){
            super(itemView);

            tv_exam_title=(TextView) itemView.findViewById(R.id.exam_title);
            img_exam_thumbnail=(ImageView) itemView.findViewById(R.id.exam_img_id);
            exam_dis=(TextView) itemView.findViewById(R.id.exam_dis);
            cardView=(CardView) itemView.findViewById(R.id.cardview_id);

        }


    }

}
