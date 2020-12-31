package com.example.tdd;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class TestParams2 {
    @Parameterized.Parameters
    public static List getParams() {
        return Arrays.asList(new Integer[][]{
                { 0, 0 }, { 1, 1 }, { 2, 2 }, { 3, 3 }
        });
    }

    //这里必须是public，不能是private
    @Parameterized.Parameter
    public int input;

    //注解括号里的参数，用来指定参数的顺序，默认为0
    @Parameterized.Parameter(1)
    public int expected;

    @Test
    public void testFibonacci() {
        System.out.println(input + "," + expected);
        Assert.assertEquals(expected, input);
    }
}
