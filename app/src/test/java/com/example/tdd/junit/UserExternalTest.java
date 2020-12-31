package com.example.tdd.junit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import java.io.IOException;

/*
实现了类似@Before、@After注解提供的功能，能在方法执行前与结束后做一些额外的操作。
 */
public class UserExternalTest {
    @Rule
    public final ExternalResource externalResource = new ExternalResource() {
        @Override
        protected void after() {
            super.after();
            System.out.println("---after---");
        }

        @Override
        protected void before() throws Throwable {
            super.before();
            System.out.println("---before---");
        }
    };

    @Test
    public void testMethod() throws IOException {
        System.out.println("---test method---");
    }
}
