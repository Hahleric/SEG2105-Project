package com.example.login2;

import java.util.ArrayList;

public class Branch {
    static ArrayList<String> mailbox = new ArrayList<String>(2);
    String branchName = "NOT SET";
    String branchAddress = "NOT SET";
    ArrayList<String> profileContent = new ArrayList<String>(2);
    ArrayList<Service> serviceContent = new ArrayList<Service>();
    static double[] rating = new double[]{0, 0};


    public String getBranchName() {
        return branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public ArrayList<String> getProfileContent() {
        return profileContent;
    }

    public ArrayList<Service> getServiceContent() {
        return serviceContent;
    }

    public String getServiceNames() {
        String[] services = new String[serviceContent.size()];
        for (int i = 0; i < serviceContent.size(); i++) {
            services[i] = serviceContent.get(i).name;
        }
        String servicess = "";
        for (int i = 0; i < services.length; i++) {
            servicess += services[i] + " ,";
        }
        return servicess;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public void setProfileContent(ArrayList<String> profileContent) {
        this.profileContent = profileContent;
    }

    public void setServiceContent(ArrayList<Service> serviceContent) {
        this.serviceContent = serviceContent;
    }

    public double getRating() {
        if (rating[0] == 0) {
            return (rating[1]) / 1;
        } else {
            return (rating[1]) / rating[0];
        }
    }
}