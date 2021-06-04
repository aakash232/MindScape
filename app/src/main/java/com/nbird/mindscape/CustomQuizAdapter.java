package com.nbird.mindscape;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class CustomQuizAdapter extends PagerAdapter {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    FirebaseStorage storage;
    StorageReference storageReference;
    Context context;
    LayoutInflater layoutInflater;
    String NAME;
    int nos;
    Uri imageUri;
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    StorageReference ref;
    String randomuid,mailid123,imageurl,imageurl123;
    ImageView questionImage;

    List<customQuizUIHolder> listItem=new ArrayList<>();

    RadioGroup radioGroup;
    int selectedId=0;
    int qerroe=0,o1error=0,o2error=0,o3error=0,o4error=0;
    int errorNum=0;
    Button nextbutton,backbutton;
    ViewPager slideViewPager;
    int currentPage;
    customQuizUIHolder l=new customQuizUIHolder();

    TextInputEditText question;
    TextInputEditText optionA,optionB,optionC,optionD;
    int i=0;
    int alarmer=0;
    private View mCurrentView;
    int kal=0;
    public CustomQuizAdapter(Context context, Button nextbutton, ViewPager slideViewPager, Button backbutton){
        this.context=context;
        this.nextbutton=nextbutton;
        this.slideViewPager=slideViewPager;
        this.backbutton=backbutton;

    }

    @Override
    public int getCount() {
        return nos;
    }



    @Override
    public boolean isViewFromObject( View view,  Object object) {
        question=(TextInputEditText) view.findViewById(R.id.question);
        optionA=(TextInputEditText) view.findViewById(R.id.optionA);
        optionB=(TextInputEditText) view.findViewById(R.id.optionB);
        optionC=(TextInputEditText) view.findViewById(R.id.optionC);
        optionD=(TextInputEditText) view.findViewById(R.id.optionD);
        radioGroup=(RadioGroup) view.findViewById(R.id.radioGroup);
        return view== (ScrollView) object;
    }



    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentView = (View)object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.activity_custom_quiz_ques_layout,container,false);



        TextView questionNumber = (TextView) view.findViewById(R.id.questionNumber);




    //    selectedId = radioGroup.getCheckedRadioButtonId();



//        RadioButton radioButton = (RadioButton)radioGroup.findViewById(selectedId);



        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                   if(alarmer==0){
                       i++;
                       slideViewPager.setCurrentItem(currentPage+1);
                       currentPage++;
                       i--;
                       slideViewPager.setCurrentItem(currentPage-1);
                       currentPage--;
                       alarmer=1;
                           l.setQuestion(question.getText().toString());
                           listItem.add(currentPage,l);
                           l=listItem.get(currentPage);

                           if(l.getQuestion().isEmpty()){
                               question.setError("fdndfim");
                               Toast.makeText(context, "pp", Toast.LENGTH_SHORT).show();
                           }else{
                               Toast.makeText(context, l.getQuestion(), Toast.LENGTH_SHORT).show();
                           }

                       if(currentPage==nos-1){
                           Toast.makeText(context,"Quiz Created!",Toast.LENGTH_SHORT).show();
                       }

                       i++;
                       slideViewPager.setCurrentItem(currentPage+1);
                       currentPage++;

                   }else{

                           l.setQuestion(question.getText().toString());
                           listItem.add(currentPage,l);
                           l=listItem.get(currentPage);

                           if(l.getQuestion().isEmpty()){
                               question.setError("fdndfim");
                               Toast.makeText(context, "pp", Toast.LENGTH_SHORT).show();
                           }else{
                               Toast.makeText(context, l.getQuestion(), Toast.LENGTH_SHORT).show();

                           }

                       if(currentPage==nos-1){
                           Toast.makeText(context,"Quiz Created!",Toast.LENGTH_SHORT).show();
                       }
                       i++;
                       slideViewPager.setCurrentItem(currentPage+1);
                       currentPage++;

                   }

            }
        });


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i--;
                slideViewPager.setCurrentItem(currentPage-1);
                currentPage--;

            }
        });



        questionNumber.setText("Question " + (++position) + "/" + nos);
      //  Toast.makeText(context,"Quiz : " + NAME,Toast.LENGTH_SHORT).show();

        container.addView(view);

        return view;
    }

    public boolean emptyErrorShower(String question, String option1, String option2, String option3, String option4, TextInputEditText questionTextView, TextInputEditText optionA, TextInputEditText optionB, TextInputEditText optionC, TextInputEditText optionD){
        if(question.isEmpty()){
            questionTextView.setError("Fields Cannot Be Empty");
            return false;
        }else if(option1.isEmpty()){
            optionA.setError("Fields Cannot Be Empty");
            return false;
        }else if(option2.isEmpty()){
            optionB.setError("Fields Cannot Be Empty");
            return false;
        }else if(option3.isEmpty()){
            optionC.setError("Fields Cannot Be Empty");
            return false;
        }else if(option4.isEmpty()){
            optionD.setError("Fields Cannot Be Empty");
            return false;
        }
        return true;
    }



    public void destroyItem(ViewGroup container,int position,Object object){

        container.removeView((ScrollView)object);
    }



}
