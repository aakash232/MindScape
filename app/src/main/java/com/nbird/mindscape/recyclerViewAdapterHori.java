package com.nbird.mindscape;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class recyclerViewAdapterHori extends RecyclerView.Adapter<recyclerViewAdapterHori.viewholder> {

    private List<Facts> listItem;
    int Exam,Std,Paper,Chapter;
    public recyclerViewAdapterHori(List<Facts> listItem){
        this.listItem=listItem;
    }




    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewfact,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setData(listItem.get(position).getHeadingText(),listItem.get(position).getDisText());
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        private TextView heatText;
        private TextView disText;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            heatText=itemView.findViewById(R.id.exam_title);
            disText=itemView.findViewById(R.id.exam_dis);
        }

        public void setData(String headingText, String disText) {
            this.heatText.setText(headingText);
            this.disText.setText(disText);


                }





        }
    }
