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

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<Modes> mData;
    int setter,a,b;

    public RecyclerViewAdapter(Context mContext, List<Modes> mData, int setter){
        this.mContext=mContext;
        this.mData=mData;
        this.setter=setter;
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.cardviewmainmenu,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder( final MyViewHolder holder, final int position) {

        holder.tv_exam_title.setText(mData.get(position).getTitle());
        holder.img_exam_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.exam_dis.setText(mData.get(position).getDis());




       holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=position+1;
                switch (a){
                    case 1:
                        Intent intent = new Intent(mContext, singleModeListView.class);
                        intent.putExtra("position", a);
                        mContext.startActivity(intent);break;
                    case 2:
                        Intent intent123 = new Intent(mContext, oneVsOneChoiceActivity.class);
                        intent123.putExtra("position", a);
                        mContext.startActivity(intent123);break;
                    case 3:
                        Intent intent2 = new Intent(mContext, tournamentChoiceActicity.class);
                        intent2.putExtra("position", a);
                        mContext.startActivity(intent2);break;
                    case 4:
                        Intent intent4 = new Intent(mContext, picture_quiz_menu.class);
                        intent4.putExtra("position", a);
                        mContext.startActivity(intent4);break;
                    case 5:
                        Intent intent6 = new Intent(mContext, customQuizMainMenu.class);
                        intent6.putExtra("position", a);
                        mContext.startActivity(intent6);break;
                    case 6:
                        Intent intent1 = new Intent(mContext, KbcWel.class);
                        intent1.putExtra("position", a);
                        mContext.startActivity(intent1);break;
                }

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
