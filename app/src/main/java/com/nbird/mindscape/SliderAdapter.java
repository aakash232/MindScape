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

import com.airbnb.lottie.LottieAnimationView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }

    //Array
    public int[] slide_images={
            R.raw.animonline,
            R.raw.slideanim2,
            R.raw.animleadership,
    };

    public String[] slide_Headings={
      "Experience Quizing Like Never Before! ",
      "KBC - Custom Quiz - Picture Quiz",
      "Play more To Achieve Ranks! "
    };

    public String[] slide_descs={
      "Compete with players across the world. \n" +
              "1 vs 1 - Tournaments - Single player",
      "Play the legindary KBC... \n" +
              "Test your visual skills with picture mode and \n"+
              "Do your friends really know you? \n" +
              "Check out the personal custom quiz. \n" +
              "Create Share Enjoy! ",
      "* Top the Leadership boards\n" +
              "* Track your progress and achievements\n" +
              "\n" +
              "Welcome to the best way to\n" +
              "Learn, Compete and Have Fun!!!",
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

        LottieAnimationView animation=(LottieAnimationView) view.findViewById(R.id.anim);
        TextView slideHeading=(TextView) view.findViewById(R.id.textView);
        TextView slideDiscription=(TextView) view.findViewById(R.id.textView2);

        animation.setAnimation(slide_images[position]);
        slideHeading.setText(slide_Headings[position]);
        slideDiscription.setText(slide_descs[position]);

        container.addView(view);



        return view;
    }


    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView((ConstraintLayout)object);
    }
}
