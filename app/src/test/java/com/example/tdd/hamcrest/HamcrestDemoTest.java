package com.example.tdd.hamcrest;

import com.example.tdd.Dollar;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.Matchers.startsWith;

public class HamcrestDemoTest {
    @Test
    public void testText() {
        assertThat("android studio", startsWith("and"));
        assertThat("android studio", endsWith("dio"));
        assertThat("android studio", containsString("android"));
        assertThat("android studio", equalToIgnoringCase("ANDROID studio"));
        assertThat("android studio ", equalToIgnoringWhiteSpace(" android studio "));
    }

    @Test
    public void testDigit() {
        //测试数字在某个范围之类，10.6在[10.5-0.2, 10.5+0.2]范围之内
        assertThat(10.6, closeTo(10.5, 0.2));
        //测试数字大于某个值
        assertThat(10.6, greaterThan(10.5));
        //测试数字小于某个值
        assertThat(10.6, lessThan(11.0));
        //测试数字小于等于某个值
        assertThat(10.6, lessThanOrEqualTo(10.6));
        //测试数字大于等于某个值
        assertThat(10.6, greaterThanOrEqualTo(10.6));
    }

    @Test
    public void testCollections() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "hello");
        map.put("b", "world");
        map.put("c", "haha");
        //测试map包含某个entry
        assertThat(map, hasEntry("a", "hello"));
        //测试map是否包含某个key
        assertThat(map, hasKey("a"));
        //测试map是否包含某个value
        assertThat(map, hasValue("hello"));

        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        //测试list是否包含某个item
        assertThat(list, hasItem("a"));
        assertThat(list, hasItems("a", "b"));

        //测试数组是否包含某个item
        String[] array = new String[]{"a", "b", "c", "d"};
        assertThat(array, hasItemInArray("a"));
    }

    @Test
    public void testObject() {
        //测试对象不为null
        assertThat(new Object(), notNullValue());
        Object obj = null;
        //测试对象为null
        assertThat(obj, nullValue());
        String str = null;
        assertThat(str, nullValue(String.class));
        obj = new Object();
        Object obj2 = obj;
        //测试2个引用是否指向的通一个对象
        assertThat(obj, sameInstance(obj2));
        str = "abc";
        assertThat(str, instanceOf(String.class));
    }

    @Test
    public void testLogic() {
        //两者都满足，a && b
        assertThat(10.4, both(greaterThan(10.0)).and(lessThan(10.5)));
        //所有的条件都满足，a && b && c...
        assertThat(10.4, allOf(greaterThan(10.0), lessThan(10.5)));
        //任一条件满足，a || b || c...
        assertThat(10.4, anyOf(greaterThan(10.3), lessThan(10.4)));
        //两者满足一个即可，a || b
        assertThat(10.4, either(greaterThan(10.0)).or(lessThan(10.2)));
        assertThat(10.4, is(10.4));
        assertThat(10.4, is(equalTo(10.4)));
        assertThat(10.4, is(greaterThan(10.3)));
        String str = new String("abc");
        assertThat(str, is(instanceOf(String.class)));
        assertThat(str, isA(String.class));
        assertThat(10.4, not(10.5));
        assertThat(str, not("abcd"));

        assertThat(str, any(String.class));
        assertThat(str, anything());
    }

    @Test
    public void testJavaBean() {
        assertThat(new Dollar(5), hasProperty("amount"));
    }
}
