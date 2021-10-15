package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CusRegSucActivity extends AppCompatActivity {
    TextView sucT;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_reg_suc);
        sucT = findViewById(R.id.textView6);
        db = new DatabaseHelper(this);
        String name = db.getName();
        String identity = db.getIdentity();
        String text;
        text= ("Welcome "+name+"!"+"\n"+"\n"+"You are Registered as a(n) "+identity);
        sucT.setText(sucT.getText()+text);

    }
}