package com.nbird.mindscape;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import java.util.List;

public class mainMenuFactsAdapter extends RecyclerView.Adapter<mainMenuFactsAdapter.viewholder> {

    private List<mainMenuFactsHolder> listItem;
    private TextView[] mDots;
    private Context context;
    private LinearLayout dotLayout;
    int currentPage;
    ViewPager slideViewPager;

    public mainMenuFactsAdapter(List<mainMenuFactsHolder> listItem, mainMenuActivity mainMenuActivity, LinearLayout dotLayout, ViewPager slideViewPager){
        this.listItem=listItem;
        this.context=mainMenuActivity;
        this.dotLayout=dotLayout;
        this.slideViewPager=slideViewPager;
    }




    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.mainmenuhorizontalslide,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setData(listItem.get(position).getTitle(),listItem.get(position).getDis(),listItem.get(position).getSet());
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView dis;
        int set;
        private ConstraintLayout constraintLayout;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.textView);
            dis=itemView.findViewById(R.id.textView2);
        }

        public void setData(String title, String dis, final int set) {
            this.title.setText(title);
            this.dis.setText(dis);
            setManupulation(set,constraintLayout);
            addDotsIndicator(0);
            slideViewPager.addOnPageChangeListener(viewListner);

        }
    }
    public void setManupulation(int set, ConstraintLayout constraintLayout){
        for(int i=0;i<5;i++){
            switch (set){
                case 1:
                    constraintLayout.setBackgroundResource(R.drawable.animalsdemo);
                case 2:
                    constraintLayout.setBackgroundResource(R.drawable.example);
                case 3:
                    constraintLayout.setBackgroundResource(R.drawable.animalsdemo);
            }
        }

    }


    public void addDotsIndicator(int position){
        mDots=new TextView[3];
        dotLayout.removeAllViews();
        for(int i=0;i<mDots.length;i++){
            mDots[i]=new TextView(context);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(40);
            mDots[i].setTextColor(context.getResources().getColor(R.color.white));
            dotLayout.addView(mDots[i]);

        }
        if(mDots.length>0){
            mDots[position].setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }
    }
    ViewPager.OnPageChangeListener viewListner=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            currentPage=position;

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}

