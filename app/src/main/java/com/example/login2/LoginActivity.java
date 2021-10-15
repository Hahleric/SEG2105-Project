package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnlog ;
    Button registerL;
    private static EditText email;
    EditText password;
    DatabaseHelper db;
    String identity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnlog = findViewById(R.id.btnLogin);
        registerL = findViewById(R.id.button2);
        email = findViewById(R.id.eEmailL);
        password = findViewById(R.id.ePasswordL);
        db = new DatabaseHelper(this);
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString();
                String p = password.getText().toString();
                if(e.isEmpty()){
                    email.setError("Enter Your Email");
                    email.requestFocus();
                }else if(p.isEmpty()){
                    password.setError("Enter Your Password");
                    password.requestFocus();
                } else if(e.equals("a")  && p.equals("a")){
                    Intent intent = new Intent(LoginActivity.this,adminSucActivity.class);
                    startActivity(intent);
                }
                else if(db.chkemail(e)){
                    Toast.makeText(getApplicationContext(),"no such user",Toast.LENGTH_LONG).show();

                }else{
                    if(db.chkemailpassword(e,p)){
                        if(db.chkemailidentity(e,"Employee")){
                            identity = "Employee";
                            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this, LogSucActivity.class);
                            startActivity(intent);
                        } else {
                            identity = "customer";
                            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this, CusSucActivity.class);
                            startActivity(intent);
                        }
                    }
                    else{
                        email.setError("Wrong password Provided");
                        email.requestFocus();
                        password.requestFocus();
                    }
                }


            }


        });
        registerL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    public static String getEmail(){
        if (email==null){
            return null;
        }
        return email.getText().toString();
}

}