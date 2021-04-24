package com.nbird.mindscape;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }

    //Array
    public int[] slide_images={
            R.drawable.slide,
            R.drawable.slide,
            R.drawable.slide,
    };

    public String[] slide_Headings={
            "1 vs 1 - Tournaments - Single player",
            "KBC - Custom quiz - Picture quiz",
            " "
    };

    public String[] slide_descs={
            "Experience Quizing like never before!Compete with players across the world. ",
            "Play the legindary KBC Test your visual skills with picture mode... And Do your friends really know you? Check out the personal custom quiz. Create Share Enjoy! ",
            "* Play more to achieve ranks! * Top the Leadership boards * Track your progress and achievements Welcome to the best way to Learn, Compete and Have Fun!!!"
    };
    @Override
    public int getCount() {
        return slide_Headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view== (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.slidelayout,container,false);

        ImageView slideImageView=(ImageView) view.findViewById(R.id.imageView);
        TextView slideHeading=(TextView) view.findViewById(R.id.textView);
        TextView slideDiscription=(TextView) view.findViewById(R.id.textView2);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_Headings[position]);
        slideDiscription.setText(slide_descs[position]);

        container.addView(view);
        return view;
    }


    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView((ConstraintLayout)object);
    }
}
