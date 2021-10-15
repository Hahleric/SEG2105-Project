package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class checkRequestActivity extends AppCompatActivity {
    TextView requestCheck;
    String requestList ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_request);

        requestCheck = findViewById(R.id.requestCheck);

        for (int i = 0; i < BranchList.branches.size(); i++) {
            if (LogSucActivity.name.equals(BranchList.branches.get(i).branchName)) {
                for(int j = 0; j< Branch.mailbox.size(); j++){
                    String[] temp = Branch.mailbox.get(j).split(",");
                    String temps = "";
                    for(int k = 0; k<temp.length;k++){
                        if(k==0){
                            temps +=" Customer : "+ temp[k]+"\n";
                        }else if(k==1){
                            temps +="   Appointment Time : "+temp[k]+"\n"+"    Uploaded : ";
                        }else{
                            temps+=temp[k]+", ";
                        }
                    }
                requestList += (j+1)+". "+ temps;
                requestList += "\n";
                }
            }
            }

        if(requestList.equals("")){
            Toast.makeText(getApplicationContext(), "The request list is empty.", Toast.LENGTH_LONG).show();
        }
        requestCheck.setText(requestList);

        }
}