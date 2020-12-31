package com.example.tdd.mockio;

import com.example.tdd.Dollar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class) //<--使用MockitoJUnitRunner
public class MockitoJUnitRunnerTest {
    @Mock //<--使用@Mock注解
            Dollar mPerson;

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }
}
