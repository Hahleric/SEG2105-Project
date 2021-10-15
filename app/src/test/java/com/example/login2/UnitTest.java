package com.example.login2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static junit.framework.TestCase.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {

    @Test
    public void serviceAdded() {

        String[] form = new String[3];
        form[0]="233";
        String[] doc= new String[3];
        doc[0]="233";
        Service testService = new Service("233",form,doc);
        testService.id = 0;
        adminSucActivity.services.add(testService);
        assertEquals(adminSucActivity.findServiceById(0),testService);
    }
    @Test
    public void serviceTimeTest(){

    }
}