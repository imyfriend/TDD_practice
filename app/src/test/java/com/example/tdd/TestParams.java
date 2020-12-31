package com.example.tdd;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

//指定Parameterized作为test runner
@RunWith(Parameterized.class)
public class TestParams {
    //这里是配置参数的数据源，该方法必须是public static修饰的，且必须返回一个可迭代的数组或者集合
    //JUnit会自动迭代该数据源，自动为参数赋值，所需参数以及参数赋值顺序由构造器决定。
    @Parameterized.Parameters
    public static List getParams() {
        return Arrays.asList(new Integer[][]{
                { 0, 0 }, { 1, 1 }, { 2, 2 }, { 3, 3 }
        });
    }

    private int input;
    private int expected;

    //在构造函数里，指定了2个输入参数，JUnit会在迭代数据源的时候，自动传入这2个参数。
    //例如：当获取到数据源的第3条数据{2，1}时，input=2，expected=1
    public TestParams(int input, int expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void testFibonacci() {
        System.out.println(input + "," + expected);
        Assert.assertEquals(expected, input);
    }
}
