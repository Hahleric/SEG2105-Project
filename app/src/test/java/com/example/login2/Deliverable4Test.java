package com.example.login2;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class Deliverable4Test {
    @Test
    public void branchAdded() {
        BranchList.branches.clear();
        Branch branch = new Branch();
        BranchList branchList = new BranchList();
        branch.branchName = "test";
        BranchList.branches.add(branch);
        assertEquals(branchList.findBranchByBranchName("test"),branch);
    }
    @Test
    public void findBranchByServiceTest() {
        BranchList.branches.clear();
        Service service = new Service("testService");
        BranchList branchList = new BranchList();
        Branch branch = new Branch();
        branch.serviceContent.add(service);
        BranchList.branches.add(branch);
        assertEquals(branchList.findBranchByService("testService").get(0),branch);

    }
    @Test
    public void findBranchByAddressTest() {
        BranchList.branches.clear();
        BranchList branchList = new BranchList();
        Branch branch = new Branch();
        branch.branchAddress = "testAddress";
        BranchList.branches.add(branch);
        assertEquals(branch,branchList.findBranchByAddress("testAddress"));

    }
    @Test
    public void findBranchByWorkTimeTest() {
        BranchList.branches.clear();
        BranchList branchList = new BranchList();
        Branch branch = new Branch();
        branch.profileContent.add("1");
        branch.profileContent.add("2");

        BranchList.branches.add(branch);
        assertEquals(branchList.findBranchByWorkTime("1","2").get(0),branch);

    }
}
