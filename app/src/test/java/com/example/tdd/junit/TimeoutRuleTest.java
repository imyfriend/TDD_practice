package com.example.tdd.junit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class TimeoutRuleTest {
    /*
    与@Test注解里的属性timeout类似，但这里是针对同一测试类里的所有测试方法都使用同样的超时时间。
     */
    @Rule
    public final Timeout globalTimeout = Timeout.millis(20);

    @Test
    public void testInfiniteLoop1() {
        for(;;) {}
    }

    @Test
    public void testInfiniteLoop2() {
        for(;;) {}
    }
}
