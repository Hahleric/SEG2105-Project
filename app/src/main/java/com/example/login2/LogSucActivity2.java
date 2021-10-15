package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogSucActivity2 extends AppCompatActivity {


    TextView txtp2;
    EditText address;
    EditText request;
    String profileOutput = "";
    Branch branch;
    Button setAddress;
    Button checkR;
    Button appR;
    Button rejR;
    int index = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_suc2);
        txtp2 = findViewById(R.id.txtp2);
        setAddress = findViewById(R.id.setAddress);
        address = findViewById(R.id.address);
        request = findViewById(R.id.request);
        checkR = findViewById(R.id.checkR);
        appR = findViewById(R.id.appR);
        rejR = findViewById(R.id.rejR);;
        branch = new Branch();

            for (int i = 0; i < BranchList.branches.size(); i++) {
                if (LogSucActivity.name.equals(BranchList.branches.get(i).branchName)) {
                    branch = BranchList.branches.get(i);
                }
            }

        setAddress.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (address.getText().toString().equals("") ) {
                    Toast.makeText(getApplicationContext(), "Make sure you entered the address", Toast.LENGTH_LONG).show();
                } else {
                    branch.branchAddress = address.getText().toString();
                    setProfileOutput();
                }

                Toast.makeText(getApplicationContext(), "Address changed successfully.", Toast.LENGTH_LONG).show();
            }


        });

        checkR.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(LogSucActivity2.this, checkRequestActivity.class);
                startActivity(intent);
            }


        });

        rejR.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                try {
                    index = Integer.parseInt(request.getText().toString());

                    for (int i = 0; i < BranchList.branches.size(); i++) {
                        if (LogSucActivity.name.equals(BranchList.branches.get(i).branchName)) {
                            if (BranchList.branches.get(i).mailbox.get(index - 1).contains( "is injected")||
                                    BranchList.branches.get(i).mailbox.get(index - 1).contains( "is approved"))
                            {
                                Toast.makeText(getApplicationContext(), "This request has been checked.", Toast.LENGTH_LONG).show();
                            } else{

                            BranchList.branches.get(i).mailbox.set(index-1, BranchList.branches.get(i).mailbox.get(index-1) + " is injected");
                                BranchList.requests.add( BranchList.branches.get(i).mailbox.get(index-1) );
                            Toast.makeText(getApplicationContext(), "Request rejected.", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                }catch(Exception e){

                        Toast.makeText(getApplicationContext(), "Make sure you entered a integer from 1 to the size of the request list.", Toast.LENGTH_LONG).show();
                    }

                }
        });

        appR.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                try{
                    index = Integer.parseInt(request.getText().toString());

                    for (int i = 0; i < BranchList.branches.size(); i++) {
                        if (LogSucActivity.name.equals(BranchList.branches.get(i).branchName)) {
                            if (BranchList.branches.get(i).mailbox.get(index - 1).contains( "is injected")||
                                    BranchList.branches.get(i).mailbox.get(index - 1).contains( "is approved"))
                            {
                                Toast.makeText(getApplicationContext(), "This request has been checked.", Toast.LENGTH_LONG).show();
                            } else{

                                BranchList.branches.get(i).mailbox.set(index-1, BranchList.branches.get(i).mailbox.get(index-1) + " is approved");
                                BranchList.requests.add( BranchList.branches.get(i).mailbox.get(index-1) );
                                Toast.makeText(getApplicationContext(), "Request approved.", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                }catch(Exception e){

                    Toast.makeText(getApplicationContext(), "Make sure you entered a integer from 1 to the size of the request list.", Toast.LENGTH_LONG).show();
                }
            }


        });


    }



        public void setProfileOutput(){
            profileOutput = "";
            profileOutput +=  "The address of the branch is:  " + branch.branchAddress+"\n" + "\n" ;

            for(int i=0; i<branch.getProfileContent().size(); i++) {
                if (i == 0) {
                    profileOutput += "The working hours are from " + branch.getProfileContent().get(i) + " to " + branch.getProfileContent().get(i + 1);
                }

                if (i == 1) {
                    profileOutput += "\n" + "\n" + "These services bellow are available at this branch now:  ";
                }

                if (branch.getProfileContent().get(i) != null) {
                    if (i >= 2) {
                        profileOutput += (i-1)+". "+branch.getProfileContent().get(i) + " ";
                    }
                }
            }

            txtp2.setText( profileOutput);

            for (int i = 0; i < BranchList.branches.size(); i++) {
                if (LogSucActivity.name.equals(BranchList.branches.get(i).branchName)) {
                    BranchList.branches.set(i,branch) ;
                }
            }
        }
}