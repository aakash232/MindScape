package com.nbird.mindscape;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
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
    CountDownTimer countDownTimer;
    MediaPlayer music,musicNav;

    public RecyclerViewAdapter(Context mContext, List<Modes> mData, int setter, MediaPlayer music, CountDownTimer countDownTimer){
        this.mContext=mContext;
        this.mData=mData;
        this.setter=setter;
        this.music=music;
        this.countDownTimer=countDownTimer;
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
                        music();
                        Intent intent = new Intent(mContext, singleModeListView.class);
                        intent.putExtra("position", a);
                        mContext.startActivity(intent);
                        ((Activity)mContext).finish();
                        Runtime.getRuntime().gc();
                        break;
                    case 2:
                        music();
                        Intent intent123 = new Intent(mContext, oneVsOneChoiceActivity.class);
                        intent123.putExtra("position", a);
                        mContext.startActivity(intent123);
                        ((Activity)mContext).finish();
                        Runtime.getRuntime().gc();
                        break;
                    case 3:
                        music();
                        Intent intent2 = new Intent(mContext, tournamentChoiceActicity.class);
                        intent2.putExtra("position", a);
                        mContext.startActivity(intent2);
                        ((Activity)mContext).finish();
                        Runtime.getRuntime().gc();
                        break;
                    case 4:
                        music();
                        Intent intent4 = new Intent(mContext, picture_quiz_menu.class);
                        intent4.putExtra("position", a);
                        mContext.startActivity(intent4);
                        ((Activity)mContext).finish();
                        Runtime.getRuntime().gc();
                        break;
                    case 5:
                        try {
                            countDownTimer.cancel();
                            music.pause();
                            music.release();
                            music=null;
                        }catch (Exception e){

                        }
                        music();
                        Intent intent5 = new Intent(mContext, SongChoiceActivity.class);
                        intent5.putExtra("position", a);

                        mContext.startActivity(intent5);
                        ((Activity)mContext).finish();
                        Runtime.getRuntime().gc();
                        break;
                    case 6:
                        music();
                        Intent intent6 = new Intent(mContext, customQuizMainMenu.class);
                        intent6.putExtra("position", a);
                        mContext.startActivity(intent6);
                        Runtime.getRuntime().gc();
                        break;
                    case 7:
                        try {
                            countDownTimer.cancel();
                            music.pause();
                            music.release();
                            music=null;
                        }catch (Exception e){

                        }


                        music();
                        Intent intent1 = new Intent(mContext, KbcWel.class);
                        intent1.putExtra("position", a);
                        mContext.startActivity(intent1);
                        ((Activity)mContext).finish();
                        Runtime.getRuntime().gc();
                        break;
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

    public void music(){
        musicNav = MediaPlayer.create(mContext, R.raw.navclick);
        musicNav.start();
        musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                try{
                    musicNav.reset();
                }catch (Exception e){

                }
                try{
                    musicNav.release();
                }catch (Exception e) {

                }
            }
        });
    }

}
