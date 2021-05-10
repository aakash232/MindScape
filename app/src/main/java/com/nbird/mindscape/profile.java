package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class profile extends AppCompatActivity {

    ViewPager2 vpVertical,vpHorizontal;
    int[] images = {R.drawable.animalsdemo,R.drawable.animalsdemo,R.drawable.animalsdemo,R.drawable.animalsdemo,R.drawable.animalsdemo};

    ProfileAdapter adapter;


        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Back Button
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(profile.this, mainMenuActivity.class);
                startActivity(back);
            }
        });

        vpVertical = findViewById(R.id.vp_vertical);
        vpHorizontal = findViewById(R.id.vp_horizontal);

        //initialize profile adapter
            adapter = new ProfileAdapter(images);

         //set adapter on vertical viewPager
         vpVertical.setAdapter(adapter);

         vpHorizontal.setClipToPadding(false);
         vpHorizontal.setClipChildren(false);
         vpHorizontal.setOffscreenPageLimit(3);
         vpHorizontal.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER); //set default start position
         //set adapter on horizontal viewpage
         vpHorizontal.setAdapter(adapter);

         //Initialize composite page transform
            CompositePageTransformer transformer = new CompositePageTransformer();
            transformer.addTransformer(new MarginPageTransformer((8)));
            transformer.addTransformer(new ViewPager2.PageTransformer() {
                @Override
                public void transformPage(@NonNull View page, float position) {
                    float v = 1 - Math.abs(position);
                    //set scale y
                    page.setScaleY(0.8f + v * 0.2f);
                }
            });

            //set page transform
            vpHorizontal.setPageTransformer(transformer);
        }

}
