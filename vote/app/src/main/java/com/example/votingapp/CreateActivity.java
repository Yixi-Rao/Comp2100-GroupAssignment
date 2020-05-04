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

    private LinearLayout llContentView;

    private EditText etContent1;

    private LinkedList<Button> listIBTNAdd;

    private int btnIDIndex = 1000;

    private LinkedList<Button> listIBTNDel;

    private int iETContentHeight = 0;

    private float fDimRatio = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        initCtrl();
    }

    private void initCtrl(){
        llContentView = (LinearLayout) this.findViewById(R.id.content_view);
        etContent1 = (EditText) this.findViewById(R.id.et_content1);
        listIBTNAdd = new LinkedList<Button>();
        listIBTNDel = new LinkedList<Button>();
        Button ibtnAdd1 = (Button) this.findViewById(R.id.ibn_add1);
        ibtnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iETContentHeight = etContent1.getHeight();
                fDimRatio = iETContentHeight / 80;
                addContent(v);
            }
        });
        listIBTNAdd.add(ibtnAdd1);
        listIBTNDel.add(null);
    }

    private void addContent(View v) {
        if (v == null) {
            return;
        }

        int iIndex = -1;
        for (int i = 0; i < listIBTNAdd.size(); i++) {
            if (listIBTNAdd.get(i) == v) {
                iIndex = i;
                break;
            }
        }

        if (iIndex >= 0) {
            iIndex += 1;
//--------------------------------------------------------------------------------------------------
            LinearLayout layout = new LinearLayout(CreateActivity.this);
            LinearLayout.LayoutParams lLayoutlayoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lLayoutlayoutParams.setMargins(0, (int) (fDimRatio * 5), 0, 0);
            layout.setLayoutParams(lLayoutlayoutParams);
            layout.setBackgroundColor(Color.argb(255, 192, 192, 192));
            layout.setPadding((int) (fDimRatio * 5), (int) (fDimRatio * 5),
                    (int) (fDimRatio * 5), (int) (fDimRatio * 5));
            layout.setOrientation(LinearLayout.VERTICAL);
//--------------------------------------------------------------------------------------------------
            EditText etContent = new EditText(CreateActivity.this);
            LinearLayout.LayoutParams etParam = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, iETContentHeight);
            etContent.setLayoutParams(etParam);
            etContent.setBackgroundColor(Color.argb(255, 255, 255, 255));
            etContent.setGravity(Gravity.LEFT);
            etContent.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            etContent.setPadding((int) (fDimRatio * 5), 0, 0, 0);
            etContent.setTextSize(16);
            layout.addView(etContent);
//--------------------------------------------------------------------------------------------------
            RelativeLayout rlBtn = new RelativeLayout(CreateActivity.this);
            RelativeLayout.LayoutParams rlParam = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            rlBtn.setPadding(0, (int) (fDimRatio * 5), 0, 0);
            rlBtn.setLayoutParams(rlParam);

            Button btnAdd2 = new Button(CreateActivity.this);
            RelativeLayout.LayoutParams btnAddParam2 =new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            btnAddParam2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            btnAdd2.setLayoutParams(btnAddParam2);
            btnAdd2.setId(btnIDIndex);
            btnAdd2.setText("ADD");
            btnAdd2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addContent(v);
                }
            });
            rlBtn.addView(btnAdd2);
            listIBTNAdd.add(iIndex, btnAdd2);

            Button btnDelete = new Button(CreateActivity.this);
            btnDelete.setText("DELETE");
            RelativeLayout.LayoutParams btnDeleteAddParam = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            btnDeleteAddParam.setMargins(0, 0, (int) (fDimRatio * 5), 0);
            btnDeleteAddParam.addRule(RelativeLayout.LEFT_OF, btnIDIndex);
            btnDelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    deleteContent(v);
                }
            });
            rlBtn.addView(btnDelete, btnDeleteAddParam);
            listIBTNDel.add(iIndex, btnDelete);

            layout.addView(rlBtn);

            EditText etContent1 = new EditText(CreateActivity.this);
            LinearLayout.LayoutParams et1Param = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            etContent1.setLayoutParams(et1Param);
            etContent1.setBackgroundColor(Color.argb(255, 255, 255, 255));
            layout.addView(etContent1);

            EditText etContent2 = new EditText(CreateActivity.this);
            LinearLayout.LayoutParams et2Param = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            etContent2.setLayoutParams(et2Param);
            etContent2.setBackgroundColor(Color.argb(255, 255, 255, 255));
            layout.addView(etContent2);

            EditText etContent3 = new EditText(CreateActivity.this);
            LinearLayout.LayoutParams et3Param = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            etContent3.setLayoutParams(et3Param);
            etContent3.setBackgroundColor(Color.argb(255, 255, 255, 255));
            layout.addView(etContent3);

            llContentView.addView(layout, iIndex);
            btnIDIndex++;
        }
    }

    private void deleteContent(View v) {
        if (v == null) {
            return;
        }

        int iIndex = -1;
        for (int i = 0; i < listIBTNDel.size(); i++) {
            if (listIBTNDel.get(i) == v) {
                iIndex = i;
                break;
            }
        }

        if (iIndex >= 0) {
            listIBTNAdd.remove(iIndex);
            listIBTNDel.remove(iIndex);
            llContentView.removeViewAt(iIndex);
        }
    }
}
