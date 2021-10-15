package com.example.login2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CusSucActivity extends AppCompatActivity {
    DatabaseHelper db;
    public static Service[] services;
    static String selectedBranchName;
    static String CusName;
    static boolean rated=false;
    EditText textSearch;
    Spinner searchSpinner;
    ListView branchList;
    Button searchButton;
    BranchList branches;
    EditText textTime2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_cus_suc);
        db = new DatabaseHelper(this);
        branches = new BranchList();
        textSearch = findViewById(R.id.sts);
        searchSpinner = findViewById(R.id.methodSpinner);
        branchList = findViewById(R.id.branchList);
        searchButton = findViewById(R.id.searchButton);
        textTime2 = findViewById(R.id.ets);
        rated = false;
        String name = db.getUser(LoginActivity.getEmail());
        String identity = db.getLogIdentity(LoginActivity.getEmail());

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String method = searchSpinner.getSelectedItem().toString();
                switch (method) {
                    case "Search Branches by Working Hour": {
                        String time1 = textSearch.getText().toString().trim();
                        String time2 = textTime2.getText().toString().trim();
                        ArrayList<Branch> foundBranches = new ArrayList<>();
                        if(branches.findBranchByWorkTime(time1, time2).isEmpty()){
                            Toast.makeText(getApplicationContext(), "No Branch Found", Toast.LENGTH_LONG).show();
                        }else {
                            foundBranches = branches.findBranchByWorkTime(time1, time2);
                            BranchFrag branchAdapter = new BranchFrag(CusSucActivity.this, foundBranches);
                            branchList.setAdapter(branchAdapter);
                            break;
                        }
                    }
                    case "Search Branches by Address": {
                        try{
                        String address = textSearch.getText().toString().trim();
                        ArrayList<Branch> foundBranch = new ArrayList<>();
                        if(branches.findBranchByAddress(address)==null){
                            Toast.makeText(getApplicationContext(), "No Branch Found", Toast.LENGTH_LONG).show();
                        }else{
                        foundBranch.add(branches.findBranchByAddress(address));
                        BranchFrag branchAdapter = new BranchFrag(CusSucActivity.this, foundBranch);
                        branchList.setAdapter(branchAdapter);}
                        break;
                        }
                        catch(Exception e)
                        {Toast.makeText(getApplicationContext(), "No Branch Found", Toast.LENGTH_LONG).show();}

                    }
                    case "Search Branches by Service": {
                        String serviceName = textSearch.getText().toString().trim();
                        ArrayList<Branch> foundBranches;
                        foundBranches = branches.findBranchByService(serviceName);
                        if (foundBranches.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "No Branch Found", Toast.LENGTH_LONG).show();
                        } else {
                            BranchFrag branchFrag = new BranchFrag(CusSucActivity.this, foundBranches);
                            branchList.setAdapter(branchFrag);
                        }
                        break;
                    }
                }
            }
        });
        branchList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Branch branch = branches.getBranch(position);
                showEnterBranchDialog(branch.branchName);
                return true;
            }
        });

        CusName = name;

    }
    public void showEnterBranchDialog(String branchName){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.selected_branch_dialog, null);
        dialogBuilder.setView(dialogView);
        final TextView TextName = dialogView.findViewById(R.id.selectedName);
        final TextView branchWorkhour = dialogView.findViewById(R.id.workhour);
        final TextView branchServices = dialogView.findViewById(R.id.selectedForm);
        final TextView branchAddress = dialogView.findViewById(R.id.selectedDoc);
        final TextView branchRating = dialogView.findViewById(R.id.rating);
        final Button check = dialogView.findViewById(R.id.result);
        final Button rate = dialogView.findViewById(R.id.enterBranch);
        final Button sendRequest = dialogView.findViewById(R.id.btnAppointment);
        dialogBuilder.setTitle(branchName);
        TextName.setText("Branch name: "+branchName);
        branchWorkhour.setText("Work hour starts at "+branches.findBranchByBranchName(branchName).profileContent.get(0)+" until "+branches.findBranchByBranchName(branchName).profileContent.get(1));
        branchServices.setText("Services: "+branches.findBranchByBranchName(branchName).getServiceNames());
        branchAddress.setText("Address: "+branches.findBranchByBranchName(branchName).getBranchAddress());
        branchRating.setText("Rating: "+branches.findBranchByBranchName(branchName).getRating());
        selectedBranchName = branches.findBranchByBranchName(branchName).getBranchName();


        final AlertDialog b = dialogBuilder.create();
        b.show();

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CusSucActivity.this, feedbackActivity.class);
                startActivity(intent);
            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CusSucActivity.this, ratingActivity.class);
                startActivity(intent);

            }
        });

        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CusSucActivity.this, requestActivity.class);
                startActivity(intent);
            }
        });


    }
}