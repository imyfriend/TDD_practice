package com.example.tdd.junit;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestJUnitLifeCycle {
    @BeforeClass
    public static void init() {
        System.out.println("------init()------");
    }

    @Before
    public void setUp() {
        System.out.println("------setUp()------");
    }

    @After
    public void tearDown() {
        System.out.println("------tearDown()------");
    }

    @AfterClass
    public static void finish() {
        System.out.println("------finish()------");
    }

    @Test
    public void test1() {
        System.out.println("------test1()------");
    }

    @Test
    public void test2() {
        System.out.println("------test2()------");
    }
}
