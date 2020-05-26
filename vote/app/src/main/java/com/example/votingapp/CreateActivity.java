package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.LinkedList;

public class CreateActivity extends AppCompatActivity {

    private LinearLayout lLayoutOut;

    private Button saveButton;
    private EditText duration;
    private EditText question;
    private EditText optionInit1;
    private EditText optionInit2;
    private EditText optionInit3;

    private LinkedList<Button> AddButList;
    private LinkedList<Button> DelButList;
    private ArrayList<Integer> questionIDs;
    private ArrayList<Integer> optionsIDs;

    private int indexOfAdd = 1000;

    private int questionHeight = 0;

    private float pd_px = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        initFirst();
    }

    private void initFirst(){
        lLayoutOut = this.findViewById(R.id.content_view);
        question = this.findViewById(R.id.et_content1);
        optionInit1 = this.findViewById(R.id.InitEditText1);
        optionInit2 = this.findViewById(R.id.InitEditText2);
        optionInit3 = this.findViewById(R.id.InitEditText3);
        saveButton = findViewById(R.id.button_Save);
        Button addButton = this.findViewById(R.id.ibn_add1);
        duration = this.findViewById(R.id.duration);

        AddButList = new LinkedList<>();
        DelButList = new LinkedList<>();
        questionIDs = new ArrayList<>();
        optionsIDs = new ArrayList<>();

        questionIDs.add(R.id.et_content1);
        optionsIDs.add(R.id.InitEditText1);
        optionsIDs.add(R.id.InitEditText2);
        optionsIDs.add(R.id.InitEditText3);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionHeight = question.getHeight();
                pd_px = questionHeight / 80; //1 pd = 2.625 px
                addContent(v);
            }
        });
        AddButList.add(addButton);
        DelButList.add(null);
    }

    private void addContent(View v) {
        if (v == null) {
            return;
        }
        int buttonIndex = -1;
        for (int i = 0; i < AddButList.size(); i++) {
            if (AddButList.get(i) == v) {
                buttonIndex = i;
                break;
            }
        }

        if (buttonIndex >= 0) {
            buttonIndex += 1;
//-------------------------------------LinearLayout In-----------------------------------------------------------
            LinearLayout lLayoutIn = new LinearLayout(CreateActivity.this);
            LinearLayout.LayoutParams lLayoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lLayoutParams.setMargins(0, (int) (pd_px * 5), 0, 0);
            lLayoutIn.setLayoutParams(lLayoutParams);
            lLayoutIn.setBackgroundColor(Color.argb(255, 192, 192, 192));
            lLayoutIn.setPadding((int) (pd_px * 5), (int) (pd_px * 5),
                    (int) (pd_px * 5), (int) (pd_px * 5));
            lLayoutIn.setOrientation(LinearLayout.VERTICAL);
//--------------------------------------EditText question 1-----------------------------------------------------------
            EditText question1 = new EditText(CreateActivity.this);
            LinearLayout.LayoutParams etParam = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, questionHeight);
            question1.setLayoutParams(etParam);
            question1.setId(View.generateViewId());
            questionIDs.add(question1.getId());
            question1.setBackgroundColor(Color.argb(255, 255, 255, 255));
            question1.setGravity(Gravity.LEFT);
            question1.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            question1.setSingleLine(false);
            question1.setPadding((int) (pd_px * 5), 0, 0, 0);
            question1.setTextSize(16);
            question1.setHint("Write the topic question");
            lLayoutIn.addView(question1);
//--------------------------------------Relative Layout-----------------------------------------------------------
            RelativeLayout reLayout = new RelativeLayout(CreateActivity.this);
            RelativeLayout.LayoutParams rlParam = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            reLayout.setPadding(0, (int) (pd_px * 5), 0, 0);
            reLayout.setLayoutParams(rlParam);
//--------------------------------------Button add 1 & Button delete 1-----------------------------------------------------
            Button addButton1 = new Button(CreateActivity.this);
            RelativeLayout.LayoutParams addButtonParam =new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            addButtonParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            addButton1.setLayoutParams(addButtonParam);
            addButton1.setId(indexOfAdd);
            addButton1.setText("ADD");
            addButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addContent(v);
                }
            });
            reLayout.addView(addButton1);
            AddButList.add(buttonIndex, addButton1);

            Button deleteButton1 = new Button(CreateActivity.this);
            deleteButton1.setText("DELETE");
            RelativeLayout.LayoutParams deleteButtonParam = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            deleteButtonParam.setMargins(0, 0, (int) (pd_px * 5), 0);
            deleteButtonParam.addRule(RelativeLayout.LEFT_OF, indexOfAdd);
            deleteButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteContent(v);
                }
            });
            reLayout.addView(deleteButton1, deleteButtonParam);
            DelButList.add(buttonIndex, deleteButton1);

            lLayoutIn.addView(reLayout);
//--------------------------------------Options-----------------------------------------------------
            EditText option1 = new EditText(CreateActivity.this);
            LinearLayout.LayoutParams et1Param = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            et1Param.setMargins(0,(int) (pd_px * 5),0,(int) (pd_px * 5));
            option1.setLayoutParams(et1Param);
            option1.setId(View.generateViewId());
            optionsIDs.add(option1.getId());
            option1.setBackgroundColor(Color.argb(255, 255, 255, 255));
            option1.setHint("Write the question option");
            option1.setPadding(0,0,0,0);
            lLayoutIn.addView(option1);

            EditText option2 = new EditText(CreateActivity.this);
            LinearLayout.LayoutParams et2Param = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            et2Param.setMargins(0,0,0,(int) (pd_px * 5));
            option2.setLayoutParams(et2Param);
            option2.setId(View.generateViewId());
            optionsIDs.add(option2.getId());
            option2.setBackgroundColor(Color.argb(255, 255, 255, 255));
            option2.setHint("Write the question option");
            option2.setPadding(0,0,0,0);
            lLayoutIn.addView(option2);

            EditText option3 = new EditText(CreateActivity.this);
            LinearLayout.LayoutParams et3Param = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            option3.setLayoutParams(et3Param);
            option3.setId(View.generateViewId());
            optionsIDs.add(option3.getId());
            option3.setBackgroundColor(Color.argb(255, 255, 255, 255));
            option3.setHint("Write the question option");
            option3.setPadding(0,0,0,0);
            lLayoutIn.addView(option3);

            lLayoutOut.addView(lLayoutIn, buttonIndex);
            indexOfAdd++;
        }
    }

    private void deleteContent(View v) {
        if (v == null) {
            return;
        }
        int buttonIndex = -1;
        for (int i = 0; i < DelButList.size(); i++) {
            if (DelButList.get(i) == v) {
                buttonIndex = i;
                break;
            }
        }

        if (buttonIndex >= 0) {
            AddButList.remove(buttonIndex);
            DelButList.remove(buttonIndex);
            lLayoutOut.removeViewAt(buttonIndex);
        }
    }

    public void generateSurvey(View view){
        Intent intentToQ = new Intent(getApplicationContext(), Questionnaire.class);
        System.out.println(duration.getText().toString().substring(9));
        intentToQ.putExtra("duration",(duration.getText().toString()).substring(9));
        for (int i = 0;i < questionIDs.size();i++){
            EditText question = findViewById(questionIDs.get(i));
            String strQuestion = question.getText().toString();
            intentToQ.putExtra("q"+i,strQuestion);
        }
        for (int i = 0;i < optionsIDs.size();i = i + 3){
            EditText option1 = findViewById(optionsIDs.get(i));
            String strOption1 = option1.getText().toString();
            intentToQ.putExtra("OP"+i/3+".1",strOption1);

            EditText option2 = findViewById(optionsIDs.get(i+1));
            String strOption2 = option2.getText().toString();
            intentToQ.putExtra("OP"+i/3+".2",strOption2);

            EditText option3 = findViewById(optionsIDs.get(i+2));
            String strOption3 = option3.getText().toString();
            intentToQ.putExtra("OP"+i/3+".3",strOption3);
        }
        intentToQ.putExtra("NumberOfQ",questionIDs.size());
        intentToQ.putExtra("NumberOfOP",(optionsIDs.size())/3);
        startActivity(intentToQ);
    }
}
