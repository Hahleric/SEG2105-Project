package com.example.login2;

import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.Objects;

public class Service {
    String name;
    String[] form;
    String[] documents;
    int id;
    public Service(String name,String[] form,  String[] documents){
        this.name = name;
        this.form = form;
        this.documents = documents;

    }
    public Service(int id){
        this.id = id;
    }
    public Service(String name){
        form = new String[30];
        this.name = name;
        documents = new String[30];
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        Service tempService = (Service) obj;
        return name.equals(tempService.name) && Arrays.equals(form, tempService.form)&& Arrays.equals(documents,tempService.documents)&& id ==tempService.id;
    }

    }

