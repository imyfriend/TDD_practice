package com.example.tdd.junit;

import com.example.tdd.ExampleUnitTest;
import com.example.tdd.hamcrest.HamcrestDemoTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExampleUnitTest.class,
        HamcrestDemoTest.class,
        TestJUnitLifeCycle.class
})
public class TestSuite {
}
