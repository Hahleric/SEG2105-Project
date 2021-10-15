package com.example.login2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ServiceList extends ArrayAdapter<Service> {
    private Activity context;
    private List<Service> services;
    public ServiceList(@NonNull Activity context, List<Service> services) {
        super(context, R.layout.layout_service_list, services);
        this.context = context;
        this.services = services;
    }

    @Override
    public View getView(int position, View concertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_service_list,null);
        TextView textViewName = listViewItem.findViewById(R.id.txtName);
        TextView textViewDoc = listViewItem.findViewById(R.id.txtDoc);
        Service service = services.get(position);
        textViewName.setText(service.name);
        String doc = "";
        String form = "";
        for(String i:service.documents){
            doc = doc+ "- "+i+"\n";
        }
        for(String i : service.form){
            form =form+"- "+i+"\n";
        }
        textViewDoc.setText(form+doc);
        return listViewItem;
    }

}
