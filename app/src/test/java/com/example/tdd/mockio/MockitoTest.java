package com.example.tdd.mockio;

import com.example.tdd.Dollar;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class MockitoTest {
    @Test
    public void testIsNotNull(){
        Dollar mPerson = mock(Dollar.class); //<--使用mock方法

        assertNotNull(mPerson);
    }
}
