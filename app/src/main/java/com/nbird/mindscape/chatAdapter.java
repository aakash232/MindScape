package com.nbird.mindscape;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class chatAdapter extends RecyclerView.Adapter<chatAdapter.viewholder> {

    private List<chatHolder> listItem;
    int playerNumActivity;
    public chatAdapter(List<chatHolder> listItem, int playerNumActivity) {
        this.listItem = listItem;
        this.playerNumActivity=playerNumActivity;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.opponent_chat_collection_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setData(listItem.get(position).getImageUrl(),listItem.get(position).getName(),listItem.get(position).getText(),listItem.get(position).playerNum);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        private TextView name,text;
        private ImageView pic;
        LinearLayout linearLayout,innerlinerLayout;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.oppoName);
            pic=itemView.findViewById(R.id.pic);
            text=itemView.findViewById(R.id.oppoText);
            linearLayout=itemView.findViewById(R.id.linearLayout);
            innerlinerLayout=itemView.findViewById(R.id.innerlinerLayout);
        }

        public void setData(String imageUrl, final String title, final String text,int playerNum) {

                linearLayout.setBackgroundResource(R.drawable.my_chat_layout);
                innerlinerLayout.setBackgroundResource(R.drawable.black_inner_back);
                //linearLayout.setGravity(Gravity.RIGHT);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.weight = 1.0f;
                params.gravity = Gravity.RIGHT;
                linearLayout.setLayoutParams(params);




            Glide.with(itemView.getContext()).load(imageUrl).apply(RequestOptions
                    .bitmapTransform(new RoundedCorners(14)))
                    .into(pic);

            this.name.setText(title);
            this.text.setText(text);



        }
    }
}

