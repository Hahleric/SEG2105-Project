package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ratingActivity extends AppCompatActivity {
    EditText rate;
    Button ThankYou;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ThankYou = findViewById(R.id.ThankYou);
        rate = findViewById(R.id.rate);

        ThankYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CusSucActivity.rated==true){
                    Toast.makeText(getApplicationContext(), "You have rated this branch.", Toast.LENGTH_LONG).show();
                    return;
                }

                try {

                    int mark = Integer.parseInt(rate.getText().toString());

                    if(mark<1 || mark>5){
                        Toast.makeText(getApplicationContext(), "The integer you entered is not in the range of 1 to 5", Toast.LENGTH_LONG).show();
                    }else{
                        for (int i = 0; i < BranchList.branches.size(); i++) {
                            if (CusSucActivity.selectedBranchName.equals(BranchList.branches.get(i).branchName)) {
                                BranchList.branches.get(i).rating[1] += mark;
                                BranchList.branches.get(i).rating[0] ++;
                            }
                        }

                        Toast.makeText(getApplicationContext(), "Thank you for your feedback!", Toast.LENGTH_LONG).show();
                        CusSucActivity.rated = true;
                    }


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Make sure you entered an integer between 1 to 5.", Toast.LENGTH_LONG).show();
                }


            }


        });
    }
}
