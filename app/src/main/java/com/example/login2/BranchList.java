package com.example.login2;

import java.util.ArrayList;

public class BranchList {
    public static ArrayList<Branch> branches = new ArrayList<Branch>();
    public void addBranch(Branch branch){
        branches.add(branch);
    }
    public Branch getBranch(int index){
        return branches.get(index);
    }
    public static ArrayList<String> requests = new ArrayList<String>();;
    //我在用户界面已经写好了用地址搜索的东西，你只需要在employee界面加一个更新地址的东西就好。
    public Branch findBranchByAddress(String address){
        for(int i = 0; i < branches.size();i++){
            if(branches.get(i).getBranchAddress().equals(address)){
                return branches.get(i);
            }
        }
        return null;
    }
    public ArrayList<Branch> findBranchByService(String serviceName){
        ArrayList<Branch> foundBranches = new ArrayList<Branch>();
        System.out.println(branches.size());
        for(int i = 0;i<branches.size();i++){
            for(int j = 0; j<branches.get(i).getServiceContent().size(); j++){
                if(branches.get(i).getServiceContent().get(j).name.equals(serviceName)){
                    foundBranches.add(branches.get(i));
                }
            }
        }
        return foundBranches;
    }
    public ArrayList<Branch> findBranchByWorkTime(String time1, String time2){
        ArrayList<Branch> foundBranches = new ArrayList<>();
        for(int i = 0; i < branches.size();i++){
            if(branches.get(i).getProfileContent().get(0).equals(time1)&&branches.get(i).getProfileContent().get(1).equals(time2)){
                foundBranches.add(branches.get(i));
            }
        }
        return foundBranches;
    }
    public Branch findBranchByBranchName(String branchName){
        Branch branch;
        for(int i = 0; i < branches.size();i++){
            if (branches.get(i).getBranchName().equals(branchName)){
                branch = branches.get(i);
                return branch;
            }
        }
        return null;
    }

}
