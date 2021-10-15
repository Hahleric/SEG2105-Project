package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText FName;
    EditText LName;
    EditText EmailR;
    EditText PasswordR;
    EditText Confirm;
    Button register;
    Switch identity;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);
        FName = findViewById(R.id.eFName);
        LName = findViewById(R.id.eLName);
        EmailR = findViewById(R.id.eEmail);
        PasswordR = findViewById(R.id.ePassword);
        Confirm = findViewById(R.id.eConfirm);
        register = findViewById(R.id.btnRegister);
        identity = findViewById(R.id.switchR);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = EmailR.getText().toString();
                String password = PasswordR.getText().toString();
                String firstName = FName.getText().toString();
                String lastName = LName.getText().toString();
                String confirm = Confirm.getText().toString();
                if(firstName.isEmpty()){
                    FName.setError("Enter Your First Name");
                    FName.requestFocus();
                }else if(lastName.isEmpty()){
                    LName.setError("Enter Your Last Name");
                    LName.requestFocus();
                }
                else if (email.isEmpty()){
                    EmailR.setError("Enter Your Email");
                    EmailR.requestFocus();
                }else if(password.isEmpty()){
                    PasswordR.setError("Enter Your Password");
                    PasswordR.requestFocus();
                }else if(confirm.isEmpty()){
                    Confirm.setError("Confirm Your Password");
                    Confirm.requestFocus();
                }else if(!firstName.isEmpty()&&!lastName.isEmpty()&&!email.isEmpty()&&!password.isEmpty()&&!confirm.isEmpty()) {
                    if (!password.equals(confirm)) {
                        Confirm.setError(("Two passwords don't match"));
                        Confirm.requestFocus();
                    }
                    else{ Intent intent;
                        if(!identity.isChecked()){
                            boolean check = db.chkemail(email);
                            if(check){
                                String name = firstName+" "+lastName;
                                boolean insert = db.insert(email,password,"customer",name);
                                if(insert){
                                    intent=new Intent(RegisterActivity.this,CusRegSucActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getApplicationContext(),"Fail to Register",Toast.LENGTH_LONG).show();

                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"User already exists",Toast.LENGTH_LONG).show();
                            }

                        }
                        else{
                            boolean check =db.chkemail(email);
                            if(check){
                                String name = firstName+" "+lastName;
                                boolean insert = db.insert(email,password,"Employee",name);
                                if(insert){
                                    intent=new Intent(RegisterActivity.this,CusRegSucActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getApplicationContext(),"False to Register",Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"User already exists",Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                }
            }
        });
    }
}
