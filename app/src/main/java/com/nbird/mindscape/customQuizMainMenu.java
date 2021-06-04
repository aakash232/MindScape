package com.nbird.mindscape;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.snapshot.Index;

import java.util.ArrayList;
import java.util.List;

public class customQuizMainMenu extends AppCompatActivity {
    List<customQuizMainMenuHolder> lstExam;
    int setter=0;
    CardView customQuizMakerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_quiz_main_menu);


        customQuizMakerButton=(CardView) findViewById(R.id.onlineButton);

        customQuizMakerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(customQuizMainMenu.this,customQuizFirstProperties.class);
                startActivity(intent);
                finish();
            }
        });

        lstExam=new ArrayList<>();
        parto();
        RecyclerView myrv=(RecyclerView) findViewById(R.id.recyclerview);
        customQuizMainMenuAdapter myAdapter=new customQuizMainMenuAdapter(this,lstExam,setter);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);

    }

    public void parto(){
        lstExam.add(new customQuizMainMenuHolder("Historical Monuments",R.drawable.cathistoricalmonuments,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
        lstExam.add(new customQuizMainMenuHolder("Flora AndFauna",R.drawable.catfloranandfauna,"Joint Entrance Examination – Main is a national level entrance exam conducted by NTA to offer admission to BE/BTech, BPlan, BArch courses at IIITs, NITs"));
        lstExam.add(new customQuizMainMenuHolder("Geography",R.drawable.catgeography,"The National Eligibility cum Entrance Test (Undergraduate), formerly AIPMT, is an entrance examination for UG medical courses(MBBS) and dental courses(BDS) in India."));
        lstExam.add(new customQuizMainMenuHolder("Astronomy And Space",R.drawable.catspaceandastrology,"VITEEE is an annual entrance examination conducted by Vellore Institute of technology, a private university in Vellore, Tamil Nadu founded in 1984."));
        lstExam.add(new customQuizMainMenuHolder("Sports",R.drawable.catsports,"State level entrance examination by the Consortium of Medical, Engineering and Dental colleges of Karnataka for admissions to Engineerting and Architecture courses."));
        lstExam.add(new customQuizMainMenuHolder("Some Superlatives",R.drawable.catsomesuperlatives,"NDA exam is conducted by UPSC twice a year for admissions to Army, Navy, and Air Force wings of the prestigious National Defence Academy, Pune."));
        lstExam.add(new customQuizMainMenuHolder("Country,Capitals And Currencies",R.drawable.catcountriesandcapitals,"BITS Admission test is an online entrance examination for admissions to integrated first degree programmes of BITS Pilani, Goa and Hyderabad."));
        lstExam.add(new customQuizMainMenuHolder("Famous Personalities",R.drawable.catfamousperson,"The Manipal Entrance Test (MET) for BTech admissions is a common entrance test for admission to Manipal Institute of Technology."));
        lstExam.add(new customQuizMainMenuHolder("Science",R.drawable.catscience,"MHT CET (MH CET) or Maharashtra Common Entrance Test is conducted by the State Common Entrance Test Cell for admissions to BE/BTech and Pharmacy programmes (BPharma/PharmaD)."));
        lstExam.add(new customQuizMainMenuHolder("Important Dates And Events",R.drawable.catimportantdates,"The SRM University conducts SRM Joint Engineering Entrance Examination (SRMJEEE) for granting admissions in undergraduate engineering courses. "));
        lstExam.add(new customQuizMainMenuHolder("Religion And Mythology",R.drawable.catrelionandculture,"Karnataka Common Entrance Test is a state-level entrance exam conducted by Karnataka Examination Authority (KEA) organised to provide admission to different UG courses in Karnataka."));
        lstExam.add(new customQuizMainMenuHolder("History",R.drawable.cathistory,"Uttar Pradesh State Entrance Exam is a state-level entrance examination organized for admission to colleges affiliated to Dr. APJ Abdul Kalam Technical University, Lucknow"));
        lstExam.add(new customQuizMainMenuHolder("Film And Entertainment",R.drawable.catfilmandentertainment,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
        lstExam.add(new customQuizMainMenuHolder("Inventions And Discoveries",R.drawable.catinventionsanddiscovery,"Joint Entrance Examination – Main is a national level entrance exam conducted by NTA to offer admission to BE/BTech, BPlan, BArch courses at IIITs, NITs"));
        lstExam.add(new customQuizMainMenuHolder("First In Different Fields",R.drawable.catfirstindifferentfields,"The National Eligibility cum Entrance Test (Undergraduate), formerly AIPMT, is an entrance examination for UG medical courses(MBBS) and dental courses(BDS) in India."));
        lstExam.add(new customQuizMainMenuHolder("Festival,Art And Culture",R.drawable.catfestivalartandculture,"VITEEE is an annual entrance examination conducted by Vellore Institute of technology, a private university in Vellore, Tamil Nadu founded in 1984."));
        lstExam.add(new customQuizMainMenuHolder("Polity And Constitution",R.drawable.catlaws,"State level entrance examination by the Consortium of Medical, Engineering and Dental colleges of Karnataka for admissions to Engineerting and Architecture courses."));
        lstExam.add(new customQuizMainMenuHolder("Literature",R.drawable.literature,"NDA exam is conducted by UPSC twice a year for admissions to Army, Navy, and Air Force wings of the prestigious National Defence Academy, Pune."));
        lstExam.add(new customQuizMainMenuHolder("Health And Disease",R.drawable.cathealthanddisease,"BITS Admission test is an online entrance examination for admissions to integrated first degree programmes of BITS Pilani, Goa and Hyderabad."));
    }


}