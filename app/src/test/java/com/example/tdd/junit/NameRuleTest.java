package com.example.tdd.junit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.junit.Assert.assertEquals;

public class NameRuleTest {
    //用@Rule注解来标记一个TestRule，注意必须是public修饰的
    @Rule
    public final TestName name = new TestName();

    @Test
    public void testA() {
        // 在测试方法内部能知道当前的方法名
        assertEquals("testA", name.getMethodName());
    }

    @Test
    public void testB() {
        assertEquals("testB", name.getMethodName());
    }
}
