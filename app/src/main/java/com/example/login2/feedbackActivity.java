package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class feedbackActivity extends AppCompatActivity {
    TextView requestFeedback;
    String result = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        requestFeedback = findViewById(R.id.requestFeedback);
        for (int i = 0; i < BranchList.requests.size(); i++) {
            if (BranchList.requests.get(i).contains(CusSucActivity.CusName)
                    &&(BranchList.requests.get(i).contains(" is rejected")
                    ||BranchList.requests.get(i).contains(" is approved"))) {

                result+= BranchList.requests.get(i);
                result+= "\n";

                }
            }

        if(result ==""){
            Toast.makeText(getApplicationContext(), "The respond list is empty.", Toast.LENGTH_LONG).show();
        }
        requestFeedback.setText(result);

        }
    }
