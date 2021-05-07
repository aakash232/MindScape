package com.nbird.mindscape;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    int[] images;

    public ProfileAdapter(int[] images){
        this.images=images;
    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Initialize View
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_item_main,parent,false);
        //Return View
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        //Set image on Image view
        holder.imageView.setBackgroundResource(images[position]);

    }

    @Override
    public int getItemCount() {
        //Return array length
        return images.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
