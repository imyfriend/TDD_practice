package com.example.tdd.mockio;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.doReturn;

public class MockioInjectMocksTest {
    @Spy
    private Person mPerson;

    @InjectMocks
    private Home mHome;

    @Rule //<--使用@Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void test() {
        doReturn("david").when(mPerson).getName();
        Assert.assertEquals(mHome.getMaster(), mPerson.getName());
    }
}
