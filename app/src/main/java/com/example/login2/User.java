package com.example.login2;

public class User {
    String password,email,identity,Name;
    boolean isAdmin;
    public User(String email,String Password){
        this.password = Password;
        this.email = email;
    }
    public User(String email,String Password, String identity){
        this.password = Password;
        this.email = email;
        this.identity = identity;
    }

    public void setName(String name){
        this.Name = name;
    }
}
