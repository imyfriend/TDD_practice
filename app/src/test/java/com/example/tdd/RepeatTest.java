package com.example.tdd;

import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

public class RepeatTest {
    @Rule
    public final RepeatRule repeatRule = new RepeatRule();

    //该方法重复执行5次
    @RepeatRule.Repeat(count = 5)
    @Test
    public void testMethod() throws IOException {
        System.out.println("---test method---");
    }

    @Test
    public void testMethod2() throws IOException {
        System.out.println("---test method2---");
    }
}
