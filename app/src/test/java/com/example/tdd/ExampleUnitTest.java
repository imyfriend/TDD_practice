package com.example.tdd;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testMultiplication() {
//        Dollar five= new Dollar(5);
//        five.times(2);
//        assertEquals(10, five.amount);
//
//        Dollar five= new Dollar(5);
//        five.times(2);
//        assertEquals(10, five.amount);
//        five.times(3);
//        assertEquals(15, five.amount);

//        Dollar five= new Dollar(5);
//        Dollar product = five.times(2);
//        assertEquals(10, product.amount);
//        product = five.times(3);
//        assertEquals(15, product.amount);

        Dollar five= new Dollar(5);
        assertEquals(new Dollar(10), five.times(2));
        assertEquals(new Dollar(15), five.times(3));
    }

    @Test
    public void testEquality() {
        assertTrue(new Dollar(5).equals(new Dollar(5)));
        assertFalse(new Dollar(5).equals(new Dollar(6)));
    }

    @Test
    public void test3(){
        assertThat("android studio", startsWith("and"));
        assertThat("android studio", endsWith("dio"));
        assertThat("android studio", containsString("android"));
        assertThat("android studio", equalToIgnoringCase("ANDROID studio"));
        assertThat("android studio", equalToIgnoringWhiteSpace(" android studio "));
    }
}