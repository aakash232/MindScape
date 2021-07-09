package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class customCategoryListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private List<customQuizPropertiesSetter> list;
    private Dialog loadingDialog;
    MediaPlayer mediaPlayer;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_category_list);

        loadingDialog=new Dialog(this);
        loadingDialog.setContentView(R.layout.loading_screen);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();



        position=getIntent().getIntExtra("position",1);


        final customQuizListAdapter categoryAdapter = new customQuizListAdapter(list,position,this);
        recyclerView.setAdapter(categoryAdapter);

        loadingDialog.show();

        myRef.child("CustomQuiz").child(String.valueOf(1)).child("category").child(String.valueOf(position)).child("questionsPack").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    list.add(dataSnapshot1.getValue(customQuizPropertiesSetter.class));

                }
                categoryAdapter.notifyDataSetChanged();
                loadingDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(customCategoryListActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
                finish();
            }
        });


    }
}