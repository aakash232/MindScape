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


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

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
        holder.setData(listItem.get(position).getTitle(),listItem.get(position).getDis(),listItem.get(position).getSet(),listItem.get(position).imageUrl);
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
        ImageView image;
        String str="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/FACTS%20PICTURE%2FHistory%2FA%20Jewish%20Monument%20Banned%20Jews%20From%20Walking%20Under%20It.jpg?alt=media&token=adc96c71-d5c8-40e7-9d6e-5b90cfb76d96";
        public viewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.textView);
            dis=itemView.findViewById(R.id.textView2);
            image=itemView.findViewById(R.id.image);

        }

        public void setData(String title, String dis, final int set, String imageUrl123) {
            this.title.setText(title);
            this.dis.setText(dis);

            Glide.with(itemView.getContext()).load(str).into(image);

       /*     Glide.with(itemView.getContext())
                    .load(str)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                    .into(image);
*/
            addDotsIndicator(0);
            slideViewPager.addOnPageChangeListener(viewListner);

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

