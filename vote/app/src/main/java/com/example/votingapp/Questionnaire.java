package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Questionnaire extends AppCompatActivity {

    private LinearLayout lLayoutOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        lLayoutOut = this.findViewById(R.id.LinearLayoutOut);
        initQuestionnaire();
    }
    private void initQuestionnaire(){
        for (int i = 0; i < getIntent().getIntExtra("NumberOfQ",0);i++){
            //--------------------------------------------------------------------------------------
            LinearLayout lLayoutIn = new LinearLayout(Questionnaire.this);
            LinearLayout.LayoutParams lLayoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lLayoutParams.setMargins(0, 15, 0, 0);
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
            ratioGroupParam.setMargins(0,15,0,0);
            buttonGroup.setLayoutParams(ratioGroupParam);

            RadioButton radioButton1 = new RadioButton(Questionnaire.this);
            RadioGroup.LayoutParams ratioButtonParam = new RadioGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            radioButton1.setLayoutParams(ratioButtonParam);
            radioButton1.setSingleLine(false);
            radioButton1.setText("A. "+getIntent().getStringExtra("OP"+i+".1"));
            radioButton1.setBackgroundColor(Color.argb(255, 255, 255, 255));
            buttonGroup.addView(radioButton1);

            RadioButton radioButton2 = new RadioButton(Questionnaire.this);
            RadioGroup.LayoutParams ratioButtonParam2 = new RadioGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            radioButton2.setLayoutParams(ratioButtonParam2);
            radioButton2.setSingleLine(false);
            radioButton2.setText("B. "+getIntent().getStringExtra("OP"+i+".2"));
            radioButton2.setBackgroundColor(Color.argb(255, 255, 255, 255));
            buttonGroup.addView(radioButton2);

            RadioButton radioButton3 = new RadioButton(Questionnaire.this);
            RadioGroup.LayoutParams ratioButtonParam3 = new RadioGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            radioButton3.setLayoutParams(ratioButtonParam3);
            radioButton3.setSingleLine(false);
            radioButton3.setText("C. "+getIntent().getStringExtra("OP"+i+".3"));
            radioButton3.setBackgroundColor(Color.argb(255, 255, 255, 255));
            buttonGroup.addView(radioButton3);

            lLayoutIn.addView(buttonGroup);



            lLayoutOut.addView(lLayoutIn);
        }
    }

    public void back_to_main(View view){
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}
