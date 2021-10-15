package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LogSucActivity extends AppCompatActivity {
    private static Branch branch;
    TextView txt;
    TextView txtp;
    DatabaseHelper db;
    EditText startTime;
    EditText endTime;
    EditText serviceName;
    static String timeOne;
    static String timeTwo;
    static String name;
    String profileOutput;
    Button setTime;
    Button addService;
    Button deleteService;
    Button moreSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_suc);
        db = new DatabaseHelper(this);
        txt = findViewById(R.id.textView3);
        txtp = findViewById(R.id.txtp);
        branch = new Branch();

        String identity;
        if(db.getUser(LoginActivity.getEmail())!=null) {
            Boolean oldEmployee = false;

            name = db.getUser(LoginActivity.getEmail());
            identity = db.getLogIdentity(LoginActivity.getEmail());
            for (int i = 0; i < BranchList.branches.size(); i++) {
                if (name.equals(BranchList.branches.get(i).branchName)) {
                    branch = BranchList.branches.get(i);
                    oldEmployee = true;
                }
            }

            if(oldEmployee == false){
                branch.setBranchName(name);
                BranchList.branches.add(branch);
            }
        }else{ name = "fortest";
         identity = "employee";}
        String text;
        text= ("Welcome "+name+"!"+"\n"+"\n"+"You are Registered as a(n) "+identity);
        txt.setText(txt.getText()+text);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        serviceName = findViewById(R.id.serviceName);
        setTime = findViewById(R.id.setTime);;
        addService = findViewById(R.id.addService);;
        deleteService = findViewById(R.id.deleteService);;
        setTime = findViewById(R.id.setTime);;
        moreSetting = findViewById(R.id.moreSetting);;
        if(branch.getProfileContent().size() == 0){
            branch.getProfileContent().add("NOT SET");
            branch.getProfileContent().add("NOT SET");
        }

        moreSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogSucActivity.this, LogSucActivity2.class);
                startActivity(intent);
            }
        });

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startTime.getText().toString().equals("") || endTime.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Make sure you entered both the start time and end time", Toast.LENGTH_LONG).show();
                } else {
                    timeOne = startTime.getText().toString();
                    timeTwo = endTime.getText().toString();
                    branch.getProfileContent().set(0, timeOne);
                    branch.getProfileContent().set(1, timeTwo);
                    profileOutput = "";
                    setProfileOutput();
                }

                Toast.makeText(getApplicationContext(), "Work hours changed successfully.", Toast.LENGTH_LONG).show();
            }


            }
        );


        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(serviceName.getText().toString()==null){
                    Toast.makeText(getApplicationContext(), "Make sure you entered a service name", Toast.LENGTH_LONG).show();
                    return ;
                }

                Boolean exist1 = false;

                Boolean exist2 = false;

                for(int i = 2; i<branch.getProfileContent().size();i++){
                    if (branch.getProfileContent().get(i).toString().equals(serviceName.getText().toString())){
                        exist2 = true;
                        break;
                    }
                }

                for(int i = 0; i<adminSucActivity.services.size();i++){
                    if (adminSucActivity.services.get(i).name.toString().equals(serviceName.getText().toString())&& exist2 == false){
                        branch.getProfileContent().add(adminSucActivity.services.get(i).name.toString());
                        branch.getServiceContent().add(adminSucActivity.services.get(i));
                        exist1 = true;
                        break;
                    }
                }



                if(exist2 == true){
                    Toast.makeText(getApplicationContext(), "This service is already set at the branch", Toast.LENGTH_LONG).show();
                } else if(exist1 == false){
                    Toast.makeText(getApplicationContext(), "This service is not provided by the Administrator", Toast.LENGTH_LONG).show();
                }
                else if(exist1 == true && exist2 == false){
                    profileOutput = "";
                    setProfileOutput();
                    Toast.makeText(getApplicationContext(), "Service added successfully", Toast.LENGTH_LONG).show();
                }

            }
        });

        deleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(serviceName.getText().toString()==null){
                    Toast.makeText(getApplicationContext(), "Make sure you entered a service name", Toast.LENGTH_LONG).show();
                    return ;
                }



                Boolean exist2 = false;


                for(int i = 2; i<branch.getProfileContent().size();i++){
                    if (branch.getProfileContent().get(i).toString().equals(serviceName.getText().toString())){
                        branch.getProfileContent().remove(i);
                        //branch.getProfileContent().remove(i-2);
                        exist2 = true;
                        break;
                    }
                }


                if(exist2 == false){
                    Toast.makeText(getApplicationContext(), "There is no such a service in the branch", Toast.LENGTH_LONG).show();
                }
                else if(exist2 == true){

                    profileOutput = "";
                   setProfileOutput();
                    Toast.makeText(getApplicationContext(), "Service removed successfully", Toast.LENGTH_LONG).show();

                }

            }
        });


    }

    public void setProfileOutput(){

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


        txtp.setText( profileOutput);

        for (int i = 0; i < BranchList.branches.size(); i++) {
            if (name.equals(BranchList.branches.get(i).branchName)) {
                BranchList.branches.set(i,branch) ;
            }
        }
    }
    public static ArrayList<Service> getServices(){
        return branch.serviceContent;
    }
    }

