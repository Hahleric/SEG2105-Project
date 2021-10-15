package com.example.login2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class requestActivity extends AppCompatActivity {
    EditText requestedService;
    EditText form;
    EditText doc;
    TextView appDate;
    Button submit;
    String requestContent;
    int mYear;
    int mMonth;
    int mDay;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        requestedService = findViewById(R.id.requestedService);;
        form = findViewById(R.id.form);;
        doc = findViewById(R.id.doc);;
        submit = findViewById(R.id.submit);
        appDate = findViewById(R.id.editDate);
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        appDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Calendar c = Calendar.getInstance();
                // 直接创建一个DatePickerDialog对话框实例，并将它显示出
                new DatePickerDialog(requestActivity.this,
                        // 绑定监听器

                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                if(year< mYear||year<=mYear&&(monthOfYear+1)<mMonth||year<=mYear&&(monthOfYear+1)<=mMonth&&dayOfMonth<mDay){
                                    TextView show = (TextView) findViewById(R.id.editDate);
                                    show.setText("invalid date");
                                }else{
                                TextView show = (TextView) findViewById(R.id.editDate);
                                show.setText(year + "." + (monthOfYear+1)
                                        + "." + dayOfMonth);}
                            }
                        }
                        // 设置初始日期
                        , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                        .get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(requestedService.getText()==null||form.getText()==null||doc.getText()==null||appDate.getText().toString().equals("Double Click to Book")){
                    Toast.makeText(getApplicationContext(), "Make sure you entered all required information.", Toast.LENGTH_LONG).show();
                }
                else{
                    if(appDate.getText().toString().equals("invalid date")){
                        Toast.makeText(getApplicationContext(),"enter a valid date",Toast.LENGTH_LONG).show();
                    }else {
                        requestContent = CusSucActivity.CusName + "," +appDate.getText().toString()+","+ requestedService.getText().toString() + "," + form.getText().toString() + "," + doc.getText().toString();

                        for (int i = 0; i < BranchList.branches.size(); i++) {
                            if (CusSucActivity.selectedBranchName.equals(BranchList.branches.get(i).branchName)) {
                                BranchList.branches.get(i).mailbox.add(requestContent);
                            }
                        }

                        Toast.makeText(getApplicationContext(), "Request submitted successfully.", Toast.LENGTH_LONG).show();
                    }
                }
                appDate.setText("Double Click to Book");
                requestedService.setText("");
                form.setText("");
                doc.setText("");
            }
        });



    }
}