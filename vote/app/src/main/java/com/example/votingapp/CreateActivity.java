package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.LinkedList;

public class CreateActivity extends AppCompatActivity {

    private LinearLayout lLayoutOut;

    private EditText question;

    private LinkedList<Button> AddButList;
    private LinkedList<Button> DelButList;

    private int indexOfAdd = 1000;

    private int questionHeight = 0;

    private float fDimRatio = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        initFirst();
    }

    private void initFirst(){
        lLayoutOut = (LinearLayout) this.findViewById(R.id.content_view);
        question = (EditText) this.findViewById(R.id.et_content1);
        AddButList = new LinkedList<Button>();
        DelButList = new LinkedList<Button>();
        Button addButton = (Button) this.findViewById(R.id.ibn_add1);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionHeight = question.getHeight();
                fDimRatio = questionHeight / 80;
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
            lLayoutParams.setMargins(0, (int) (fDimRatio * 5), 0, 0);
            lLayoutIn.setLayoutParams(lLayoutParams);
            lLayoutIn.setBackgroundColor(Color.argb(255, 192, 192, 192));
            lLayoutIn.setPadding((int) (fDimRatio * 5), (int) (fDimRatio * 5),
                    (int) (fDimRatio * 5), (int) (fDimRatio * 5));
            lLayoutIn.setOrientation(LinearLayout.VERTICAL);
//--------------------------------------EditText question 1-----------------------------------------------------------
            EditText question1 = new EditText(CreateActivity.this);
            LinearLayout.LayoutParams etParam = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, questionHeight);
            question1.setLayoutParams(etParam);
            question1.setBackgroundColor(Color.argb(255, 255, 255, 255));
            question1.setGravity(Gravity.LEFT);
            question1.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            question1.setPadding((int) (fDimRatio * 5), 0, 0, 0);
            question1.setTextSize(16);
            lLayoutIn.addView(question1);
//--------------------------------------Relative Layout-----------------------------------------------------------
            RelativeLayout reLayout = new RelativeLayout(CreateActivity.this);
            RelativeLayout.LayoutParams rlParam = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            reLayout.setPadding(0, (int) (fDimRatio * 5), 0, 0);
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
            deleteButtonParam.setMargins(0, 0, (int) (fDimRatio * 5), 0);
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
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            option1.setLayoutParams(et1Param);
            option1.setBackgroundColor(Color.argb(255, 255, 255, 255));
            lLayoutIn.addView(option1);

            EditText option2 = new EditText(CreateActivity.this);
            LinearLayout.LayoutParams et2Param = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            option2.setLayoutParams(et2Param);
            option2.setBackgroundColor(Color.argb(255, 255, 255, 255));
            lLayoutIn.addView(option2);

            EditText option3 = new EditText(CreateActivity.this);
            LinearLayout.LayoutParams et3Param = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            option3.setLayoutParams(et3Param);
            option3.setBackgroundColor(Color.argb(255, 255, 255, 255));
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
}
