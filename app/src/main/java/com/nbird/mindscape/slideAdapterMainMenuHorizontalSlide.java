package com.nbird.mindscape;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

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

        TextView slideHeading=(TextView) view.findViewById(R.id.textView);
        TextView slideDiscription=(TextView) view.findViewById(R.id.textView2);
        ImageView image=(ImageView) view.findViewById(R.id.image);


        slideHeading.setText(listItem.get(position).getTitle());
        slideDiscription.setText(listItem.get(position).getDis());
        Glide.with(context).load(listItem.get(position).getImageUrl()).into(image);
        Animation imgAnim1 = AnimationUtils.loadAnimation(context, R.anim.scaleincanim);
        image.setAnimation(imgAnim1);

        container.addView(view);



        return view;
    }




    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView((ConstraintLayout)object);
    }
}

