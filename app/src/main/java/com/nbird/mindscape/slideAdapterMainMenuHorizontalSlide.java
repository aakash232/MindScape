package com.nbird.mindscape;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;


public class slideAdapterMainMenuHorizontalSlide extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    private List<mainMenuFactsHolder> listItem;


    public slideAdapterMainMenuHorizontalSlide(Context context, List<mainMenuFactsHolder> list){
        this.context=context;
        this.listItem=list;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject( View view,  Object object) {
        return view== (ConstraintLayout) object;
    }


    @Override
    public Object instantiateItem( ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.mainmenuhorizontalslide,container,false);

        ConstraintLayout constraintLayout=(ConstraintLayout) view.findViewById(R.id.constrainmain);
        TextView slideHeading=(TextView) view.findViewById(R.id.textView);
        TextView slideDiscription=(TextView) view.findViewById(R.id.textView2);

        setManupulation(listItem.get(position).getCategory(),constraintLayout);
        slideHeading.setText(listItem.get(position).getTitle());
        slideDiscription.setText(listItem.get(position).getDis());

        container.addView(view);



        return view;
    }

    public void setManupulation(int category, ConstraintLayout constraintLayout){
            switch (category){
                case 1:
                    constraintLayout.setBackgroundResource(R.drawable.monumentsimage);
                    break;
                case 2:
                    constraintLayout.setBackgroundResource(R.drawable.animalsdemo);
                    break;
                case 3:
                    constraintLayout.setBackgroundResource(R.drawable.placesimage);
                    break;
            }
    }


    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView((ConstraintLayout)object);
    }
}

