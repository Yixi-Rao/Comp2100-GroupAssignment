package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Questionnaire extends AppCompatActivity {

    private LinearLayout lLayoutOut;

    public int respondentId = 0;
    public int indexOfQ = 0;

    private HashMap<String, Integer> OptionToIdMap; // e.g. 1.1 --> id, 2.3 --> id
    private ArrayList<Integer> RadioGroupList;
    private ArrayList<String> Choices;
    private ArrayList<int[]> Question_Option_Group;
    TextView duration_tv;
    CountDownTimer timer;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        //String questionaireGuide = "Making choices and submit!";
        lLayoutOut = this.findViewById(R.id.LinearLayoutOut);
        RadioGroupList = new ArrayList<>();
        OptionToIdMap = new HashMap<>();
        Choices = new ArrayList<>();

        duration_tv = this.findViewById(R.id.timer_tv);
        initQuestionnaire();
        /**
         * if the timer is shut down then if click the back button wont get back to the "shut down" intent
         */
        if (getIntent().getStringExtra("duration").equals("")){
            System.out.println("You have not set up the Timer");
            duration_tv.setText("No Timer");
            duration_tv.setTextSize(15);
        } else {
            /**
             * setting counting down timer to set deadline
             */
            timer = new CountDownTimer(Integer.parseInt(getIntent().getStringExtra("duration") )* 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    duration_tv.setText((millisUntilFinished / 1000) + "s");
                }
                @Override
                public void onFinish() {
                    Choices.clear();
                    Intent intentToR = new Intent(getApplicationContext(), Result.class);
                    intentToR.putExtra("NumOfQ",Question_Option_Group.size());
                    for (int i = 0;i < Question_Option_Group.size();i++){
                        intentToR.putExtra(i+"A",Question_Option_Group.get(i)[0]);
                        intentToR.putExtra(i+"B",Question_Option_Group.get(i)[1]);
                        intentToR.putExtra(i+"C",Question_Option_Group.get(i)[2]);
                    }
                    intentToR.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    Question_Option_Group.clear();
                    startActivity(intentToR);
                }
            };
            timer.start();
        }
    }

    private void initQuestionnaire(){
        Question_Option_Group = new ArrayList<>();
        for (int i = 0; i < getIntent().getIntExtra("NumberOfQ",0);i++){
            Question_Option_Group.add(new int[3]);
            //--------------------------------------------------------------------------------------
            LinearLayout lLayoutIn = new LinearLayout(Questionnaire.this);
            LinearLayout.LayoutParams lLayoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lLayoutParams.setMargins(0, 15 , 0, 0);
            lLayoutIn.setLayoutParams(lLayoutParams);
            lLayoutIn.setBackgroundColor(Color.argb(255, 192, 192, 192));
            lLayoutIn.setPadding(15, 15, 15, 15);
            lLayoutIn.setOrientation(LinearLayout.VERTICAL);
            //--------------------------------------------------------------------------------------
            TextView question1 = new TextView(Questionnaire.this);
            LinearLayout.LayoutParams etParam = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            question1.setLayoutParams(etParam);
            question1.setBackgroundColor(Color.argb(255, 255, 255, 255));
            question1.setGravity(Gravity.LEFT);
            question1.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            question1.setPadding(5, 0, 0, 0);
            question1.setTextSize(16);
            question1.setText(i+1+". "+getIntent().getStringExtra("q"+ i));
            question1.setSingleLine(false);
            lLayoutIn.addView(question1);
            //--------------------------------------------------------------------------------------
            RadioGroup buttonGroup = new RadioGroup(Questionnaire.this);
            RadioGroup.LayoutParams ratioGroupParam = new RadioGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            buttonGroup.setId(View.generateViewId());
            RadioGroupList.add(buttonGroup.getId());
            ratioGroupParam.setMargins(0,15,0,0);
            buttonGroup.setLayoutParams(ratioGroupParam);

            RadioButton radioButton1 = new RadioButton(Questionnaire.this);
            RadioGroup.LayoutParams ratioButtonParam = new RadioGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            radioButton1.setId(View.generateViewId());
            OptionToIdMap.put(i+".1",radioButton1.getId());
            radioButton1.setLayoutParams(ratioButtonParam);
            radioButton1.setSingleLine(false);
            radioButton1.setText("A. "+getIntent().getStringExtra("OP"+i+".1"));
            radioButton1.setBackgroundColor(Color.argb(255, 255, 255, 255));
            buttonGroup.addView(radioButton1);

            RadioButton radioButton2 = new RadioButton(Questionnaire.this);
            RadioGroup.LayoutParams ratioButtonParam2 = new RadioGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            radioButton2.setId(View.generateViewId());
            OptionToIdMap.put(i+".2",radioButton2.getId());
            radioButton2.setLayoutParams(ratioButtonParam2);
            radioButton2.setSingleLine(false);
            radioButton2.setText("B. "+getIntent().getStringExtra("OP"+i+".2"));
            radioButton2.setBackgroundColor(Color.argb(255, 255, 255, 255));
            buttonGroup.addView(radioButton2);

            RadioButton radioButton3 = new RadioButton(Questionnaire.this);
            RadioGroup.LayoutParams ratioButtonParam3 = new RadioGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            radioButton3.setId(View.generateViewId());
            OptionToIdMap.put(i+".3",radioButton3.getId());
            radioButton3.setLayoutParams(ratioButtonParam3);
            radioButton3.setSingleLine(false);
            radioButton3.setText("C. "+getIntent().getStringExtra("OP"+i+".3"));
            radioButton3.setBackgroundColor(Color.argb(255, 255, 255, 255));
            buttonGroup.addView(radioButton3);

            lLayoutIn.addView(buttonGroup);

            lLayoutOut.addView(lLayoutIn);
            indexOfQ++;
        }
    }

    public void back_to_main(View view){
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void submit_and_clear(View view){
        for (int i = 0; i <RadioGroupList.size();i++){
            int idOfChoice = ((RadioGroup)findViewById(RadioGroupList.get(i))).getCheckedRadioButtonId ();
            if (idOfChoice == -1){
                System.out.println("No choice!");
                break;
            }
            String choice = ((RadioButton)findViewById(idOfChoice)).getText().toString().charAt(0)+"";
            Choices.add(choice);
            /**
             * Choices is a n-row 3 columns "answers" arraylist
             */
            if (choice.equals("A")){
                Question_Option_Group.get(i)[0]++;
                System.out.println("A"+Question_Option_Group.get(i)[0]);
            } else if (choice.equals("B")){
                Question_Option_Group.get(i)[1]++;
                System.out.println("B"+Question_Option_Group.get(i)[1]);
            } else {
                Question_Option_Group.get(i)[2]++;
                System.out.println("C"+Question_Option_Group.get(i)[2]);
            }
        }

        for (int i = 0; i <RadioGroupList.size();i++){
            ((RadioGroup)findViewById(RadioGroupList.get(i))).clearCheck();
        }
        /**
         * transferring answers to get result
         */
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(respondentId+"");
        myRef.setValue(Choices);
        respondentId++;
        System.out.println(Choices);
        Choices.clear();
    }

    public void result_and_visualize(View view) {
        Intent intentToR = new Intent(getApplicationContext(), Result.class);
        intentToR.putExtra("NumOfQ",Question_Option_Group.size());
        for (int i = 0;i < Question_Option_Group.size();i++){
            intentToR.putExtra(i+"A",Question_Option_Group.get(i)[0]);
            intentToR.putExtra(i+"B",Question_Option_Group.get(i)[1]);
            intentToR.putExtra(i+"C",Question_Option_Group.get(i)[2]);
        }
        Choices.clear();
        Question_Option_Group.clear();
        intentToR.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentToR);
    }

}
