package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: voting started");

        Button quesButt = (Button) findViewById(R.id.questbut);
        /**
         * Quest Button mainly doing simple guide on how to start the app
         */
        quesButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: questbutt clicked!!");
                toastMessage("Start a new voting by clicking 'CREAT A VOTE'  Load a past running 'vote' by clicking 'LOAD VOTE'");
            }
        });
    }

    /**
     * jump to createActivity to create questionnaire
     * @param v view
     */
    public void jumpToCreateActivity(View v){
        Intent intent = new Intent(this,CreateActivity.class);
        startActivity(intent);
    }

    /**
     * jump to question activity to submit and finish a questionnaire
     * @param v view
     */
    public void jumpToQuesActivity(View v){
        Intent intent = new Intent(this,Questionnaire.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    /**
     * generally setting a fast way to use toaster
     * @param ms message will present
     */
    public void toastMessage(String ms){
        Toast.makeText(MainActivity.this, ms, Toast.LENGTH_LONG).show();
    }
}
