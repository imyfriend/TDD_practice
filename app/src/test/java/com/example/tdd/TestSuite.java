package com.example.tdd;

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
