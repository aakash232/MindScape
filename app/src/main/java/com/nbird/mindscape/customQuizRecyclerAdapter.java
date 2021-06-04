package com.nbird.mindscape;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class customQuizRecyclerAdapter extends RecyclerView.Adapter<customQuizRecyclerAdapter.viewholder> {

    private List<customQuizUIHolder> listItem;
    customQuizUIHolder l=new customQuizUIHolder();
    Context context;
    Button submitbutton;
    int numberOfQuestions;
    private OnEditTextChanged onEditTextChanged;
    public customQuizRecyclerAdapter(Context context, Button submitbutton, int numberOfQuestions) {
        this.context = context;
        this.submitbutton=submitbutton;
        this.numberOfQuestions=numberOfQuestions;
    }
    public interface OnEditTextChanged {
        void onTextChanged(int position, String charSeq);
    }



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_custom_quiz_ques_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, final int position) {

        holder.question.setText("");


        holder.question.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onEditTextChanged.onTextChanged(position, charSequence.toString());
                Toast.makeText(context, charSequence, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });


        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<numberOfQuestions;i++){
                    String questionString=holder.question.getText().toString();
                    if(questionString.isEmpty()){

                    }else{
                        l.setQuestion(questionString);
                        listItem.add(i,l);

                        //
                    }




                    l=listItem.get(i);
                    if(l.getQuestion().isEmpty()){
                        holder.question.setError("Fields Cannot Be Empty");
                    }
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return numberOfQuestions;
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextInputEditText question;
        TextInputEditText optionA,optionB,optionC,optionD;
        RadioGroup radioGroup;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            question=(TextInputEditText) itemView.findViewById(R.id.question);
            optionA=(TextInputEditText) itemView.findViewById(R.id.optionA);
            optionB=(TextInputEditText) itemView.findViewById(R.id.optionB);
            optionC=(TextInputEditText) itemView.findViewById(R.id.optionC);
            optionD=(TextInputEditText) itemView.findViewById(R.id.optionD);
            radioGroup=(RadioGroup) itemView.findViewById(R.id.radioGroup);

        }
    }
}
