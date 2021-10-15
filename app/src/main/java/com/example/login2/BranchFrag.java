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

public class BranchFrag extends ArrayAdapter<Branch> {
    private Activity context;
    private List<Branch> branches;
    public BranchFrag(@NonNull Activity context, List<Branch> services) {
        super(context, R.layout.layout_branches_list, services);
        this.context = context;
        this.branches = services;
    }

    @Override
    public View getView(int position, View concertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_branches_list,null);
        TextView textViewName = listViewItem.findViewById(R.id.branchName);
        Branch branch = branches.get(position);
        textViewName.setText("Branch name: "+branch.branchName +",  Rating: "+branch.getRating());
        return listViewItem;
    }

}
