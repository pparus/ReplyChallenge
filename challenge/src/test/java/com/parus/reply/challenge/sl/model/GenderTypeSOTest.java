package com.parus.reply.challenge.sl.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class GenderTypeSOTest {

    @Test
    public void createTest() {
        String string = "FEMALE";
        GenderTypeSO gender = GenderTypeSO.valueOf(string);
        System.out.println(gender.name());
    }

}