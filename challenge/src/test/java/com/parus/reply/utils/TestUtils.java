package com.parus.reply.utils;

import com.parus.reply.challenge.dl.model.Demand;
import com.parus.reply.challenge.dl.model.User;
import com.parus.reply.challenge.sl.model.UserSO;
import org.springframework.http.ResponseEntity;

import java.util.Calendar;
import java.util.Date;

public class TestUtils {

    public static Demand createNewDemand(Integer startLoc, Integer endLoc, Date startTime, Date endTime) {
        Demand result = new Demand();
        result.setPickUpTime(startTime);
        result.setDropOffTime(endTime);
        result.setPickUpLocation(startLoc);
        result.setDropOffLocation(endLoc);
        return result;
    }

    public static Demand createNewDemand() {
        Demand result = new Demand();
        result.setPickUpTime(new Date());
        result.setDropOffTime(addOneHourToDate(new Date()));
        result.setPickUpLocation(1);
        result.setDropOffLocation(3);
        return result;
    }

    public static Date addOneHourToDate(Date date) {
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(date); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
        return cal.getTime(); // returns new date object, one hour in the future
    }

    public static UserSO createUserSO() {

        //setUpUserForTest
        UserSO newUser = new UserSO();
        newUser.setName("jUnitTester");
        newUser.setMail("jUnitTester@tester.com");
        return newUser;
    }

    public static User createUser() {

        //setUpUserForTest
        User newUser = new User();
        newUser.setName("jUnitTester");
        newUser.setMail("jUnitTester@tester.com");
        return newUser;
    }

}
