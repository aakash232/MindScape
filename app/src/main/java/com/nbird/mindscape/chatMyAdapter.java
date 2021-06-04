package com.nbird.mindscape;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class chatMyAdapter extends RecyclerView.Adapter<chatMyAdapter.viewholder> {

    private List<chatHolder> listItem;

    public chatMyAdapter(List<chatHolder> listItem) {
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_chat_collection_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setData(listItem.get(position).getImageUrl(),listItem.get(position).getName(),listItem.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        private TextView name,text;
        private ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.myName);
            pic=itemView.findViewById(R.id.pic);
            text=itemView.findViewById(R.id.myText);

        }

        public void setData(String imageUrl, final String title, final String text) {
            Glide.with(itemView.getContext()).load(imageUrl).apply(RequestOptions
                    .bitmapTransform(new RoundedCorners(14)))
                    .into(pic);

            this.name.setText(title);
            this.text.setText(text);
        }
    }
}

