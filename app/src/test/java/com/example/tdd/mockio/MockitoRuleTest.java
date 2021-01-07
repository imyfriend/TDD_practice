package com.example.tdd.mockio;

import com.example.tdd.Dollar;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class MockitoRuleTest {
    @Mock //<--使用@Mock注解
    Dollar dollar;

    @Rule //<--使用@Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testIsNotNull(){
        assertNotNull(dollar);
    }

    @Test
    public void testVerify() { // 验证行为
        //创建一个mock对象
        List list = mock(List.class);

        //使用mock对象
        list.add("one");
        list.clear();

        //验证mock对象的行为
        verify(list).add("one");  //验证有add("one")行为发生
        verify(list).clear();     //验证有clear()行为发生
    }

/*
Stub对象用来提供测试时所需要的测试数据，对各种交互设置相应的回应。
Mockito使用when(...).thenReturn(...)设置方法调用的返回值，
使用when(...).thenThrow(...)设置方法调用时抛出的异常。

对于有返回值的方法，mock会默认返回null、空集合、默认值。比如为int/Integer返回0，为boolean/Boolean返回false、为Object返回null。
一旦stubbing，不管方法被调用多少次，都永远返回stubbing的值。
stubbing可以被覆盖， 如果对同一个方法进行多次stubbing，最后一次的stubbing会生效。
 */
    @Test(expected = RuntimeException.class)
    public void testStubbing() {
        //不仅可以针对接口mock, 还可以针对具体类
        LinkedList list = mock(LinkedList.class);

        //设置返回值，当调用list.get(0)时会返回"first"
        when(list.get(0)).thenReturn("first");
        //当调用list.get(1)时会抛出异常
        when(list.get(1)).thenThrow(new RuntimeException());

        //会打印"print"
        System.out.println(list.get(0));
        //会抛出RuntimeException
        System.out.println(list.get(1));
        //会打印 null
        System.out.println(list.get(99));

        verify(list).get(0);
    }

    @Test
    public void testArgumentMatchers() {
        List<String> list = mock(List.class);
        //使用anyInt(), anyString(), anyLong()等进行参数匹配
        when(list.get(anyInt())).thenReturn("item");

        //将会打印出"item"
        System.out.println(list.get(100));

        verify(list).get(anyInt());
    }

    @Test
    public void testTimes() {
        List list = mock(List.class);
        list.add("once");
        list.add("twice");
        list.add("twice");
        list.add("triple");
        list.add("triple");
        list.add("triple");

        //执行1次
        verify(list, times(1)).add("once");
        //执行2次
        verify(list, times(2)).add("twice");
        verify(list, times(3)).add("triple");

        //从不执行, never()等同于times(0)
        verify(list, never()).add("never happened");

        //验证至少执行1次
        verify(list, atLeastOnce()).add("twice");
        //验证至少执行2次
        verify(list, atLeast(2)).add("twice");
        //验证最多执行4次
        verify(list, atMost(4)).add("triple");
    }

    @Test
    public void testVerifyAfter() {
        Dollar dollar = mock(Dollar.class);
        dollar.getAmount();
        // after在给定的时间后进行验证
        verify(dollar, after(1000)).getAmount();
    }

    @Test
    public void testVerifyTimes(){
        Dollar dollar = mock(Dollar.class);
        dollar.getAmount();
        dollar.getAmount();
        //验证方法在100ms超时前调用2次，验证失败时输出内容times is not 2
        verify(dollar, timeout(100).times(2).description("times is not 2")).getAmount();
    }

    @Test
    public void testAnswer() {
        Dollar dollar = mock(Dollar.class);

        System.out.println(dollar.getAmount());

        when(dollar.getAmount()).thenAnswer(invocation -> {
            Object[] objects = invocation.getArguments();
            return 1;
        });
        System.out.println(dollar.getAmount());
    }

    @Test
    public void testInOrder() { // 验证方法的调用顺序
        List list = mock(List.class);
        list.add("first");
        list.add("second");

        InOrder myOrder = inOrder(list);
        myOrder.verify(list).add("first");
        myOrder.verify(list).add("second");

        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        firstMock.add("first add");
        secondMock.add("second add");

        InOrder inOrder1 = inOrder(firstMock, secondMock);

        //下列代码会确认是否firstmock优先secondMock执行add方法
        inOrder1.verify(firstMock).add("first add");
        inOrder1.verify(secondMock).add("second add");
    }

    @Test
    public void testZeroInteractions() {
        List list = mock(List.class);
        //验证mock对象没有产生任何交互，也即没有任何方法调用
        verifyZeroInteractions(list);

        List list2 = mock(List.class);
        list2.add("one");
        list2.add("two");
        verify(list2).add("one");
        //verify(list2).add("two");
        //验证mock对象是否有被调用过但没被验证的方法。这里会测试不通过，list2.add("two")方法没有被验证过
        verifyNoMoreInteractions(list2);
    }

    @Test(expected = RuntimeException.class)
    public void testDoThrow() {
        List list = mock(List.class);
        list.add("123");
        //当list调用clear()方法时会抛出异常
        doThrow(new RuntimeException()).when(list).clear();
        list.clear();

        doReturn("123").when(list).get(anyInt());
        System.out.println(list.get(0));

        class Foo {
            public void doFoo() {
                System.out.println("method doFoo called.");
            }

            public int getCount() {
                return 1;
            }
        }

        Foo foo = mock(Foo.class);

        //什么信息也不会打印, mock对象并不会调用真实逻辑
        foo.doFoo();

        //啥也不会打印出来
        doNothing().when(foo).doFoo();
        foo.doFoo();

        doCallRealMethod().when(foo).doFoo();
        //这里会调用真实逻辑, 打印出"method doFoo called."信息
        foo.doFoo();

        //这里会打印出0
        System.out.println(foo.getCount());
        doCallRealMethod().when(foo).getCount();
        //这里会打印出"1"
        System.out.println(foo.getCount());
    }

    @Test
    public void testOtherDo() {
        List list = mock(List.class);
        list.add("123");

        doReturn("123").when(list).get(anyInt());
        System.out.println(list.get(0));

        class Foo {
            public void doFoo() {
                System.out.println("method doFoo called.");
            }

            public int getCount() {
                return 1;
            }
        }

        Foo foo = mock(Foo.class);

        //什么信息也不会打印, mock对象并不会调用真实逻辑
        foo.doFoo();

        //啥也不会打印出来
        doNothing().when(foo).doFoo();
        foo.doFoo();

        doCallRealMethod().when(foo).doFoo();
        //这里会调用真实逻辑, 打印出"method doFoo called."信息
        foo.doFoo();

        //这里会打印出0
        System.out.println(foo.getCount());
        doCallRealMethod().when(foo).getCount();
        //这里会打印出"1"
        System.out.println(foo.getCount());
    }

    /*
    使用spy可以监视对象方法的真实调用。当我们mock某个类时，如果需要某些方法是真实调用，
    而某些方法是mock调用时，借助spy可以实现这些功能。
     */
    @Test
    public void testSpy(){
        List list = new ArrayList();
        List spy = spy(list);

        //subbing方法，size()并不会真实调用，这里返回10
        when(spy.size()).thenReturn(10);

        //使用spy对象会调用真实的方法
        spy.add("one");
        spy.add("two");

        //会打印出"one"
        System.out.println(spy.get(0));
        //会打印出"10"，与前面的stubbing方法对应
        System.out.println(spy.size());

        //对spy对象依旧可以来验证其行为
        verify(spy).add("one");
        verify(spy).add("two");

        List spyList = spy(new ArrayList());

        //下面代码会抛出IndexOutOfBoundsException
        // when(spyList.get(0)).thenReturn("foo");

        //这里不会抛出异常
        doReturn("foo").when(spyList).get(0);
        System.out.println(spyList.get(0));
    }

    @Test
    public void testArgumentCaptor() {
        List list = mock(List.class);
        ArgumentCaptor<String> args = ArgumentCaptor.forClass(String.class);
        list.add("one");

        //验证后再捕捉参数
        verify(list).add(args.capture());
        Assert.assertEquals("one", args.getValue());
    }

    @Test
    public void testArgumentMatcher() {
        Dollar dollar = spy(new Dollar(5));
        System.out.println(dollar.getAmount());

        doReturn(new Dollar(5)).when(dollar).times(intThat(argument -> {
            System.out.println("argument: " + argument);
            return argument.equals(5);}));

        Dollar dollar1 = dollar.times(5);
        System.out.println(dollar1.getAmount());
    }

    @Test
    public void testArgumentMatcher01() {
        List mockList = mock(ArrayList.class);

        when(mockList.get(anyInt())).thenReturn("不管请求第几个参数 我都返回这句");
        System.out.println(mockList.get(0));
        System.out.println(mockList.get(39));

        class ListOfTwoElements implements ArgumentMatcher<List> {
            public boolean matches(List list) {
                return list.size() == 2;
            }

            public String toString() {
                //printed in verification errors
                return "[list of 2 elements]";
            }
        }

        //当mockList调用addAll()方法时，「匹配器」如果传入的参数list size==2，返回true；
        when(mockList.addAll(argThat(new ListOfTwoElements()))).thenReturn(true);

        boolean b1 = mockList.addAll(Arrays.asList("one", "two"));
        boolean b2 = mockList.addAll(Arrays.asList("one", "two", "three"));

        verify(mockList).addAll(argThat(new ListOfTwoElements()));
        Assert.assertTrue(b1);
        Assert.assertTrue(!b2);
    }

    @Test
    public void testReset() {
        List list = mock(List.class);
        when(list.size()).thenReturn(100);

        //打印出"100"
        System.out.println(list.size());

        //重置mock, 之前的交互和stub将全部失效
        reset(list);

        //打印出"0"
        System.out.println(list.size());
    }

    @Test
    public void testContinueMethod() {
        Person mockedUser = mock(Person.class);

        when(mockedUser.getName())
                .thenReturn("qingmei2")
                .thenThrow(new RuntimeException("方法调用第二次抛出异常"))
                .thenReturn("qingemi2 第三次调用");

        //另外一种方式
        when(mockedUser.getName()).thenReturn("qingmei2 1", "qingmei2 2", "qingmei2 3");

        String name1 = mockedUser.getName();

        try {
            String name2 = mockedUser.getName();
            System.out.println(name2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String name3 = mockedUser.getName();

        System.out.println(name1);
        System.out.println(name3);
    }

    @Test(expected = RuntimeException.class)
    public void testVoid() {
        List mockList = mock(ArrayList.class);

        doAnswer(invocation -> {
            System.out.println("测试无返回值的函数");
            return null;
        }).when(mockList).clear();

        doThrow(new RuntimeException("测试无返回值的函数->抛出异常"))
                .when(mockList).add(eq(1), anyString());

        doNothing().when(mockList).add(eq(2), anyString());

//        doReturn("123456").when(mockList).add(eq(3), anyString());    //不能把空返回值的函数与doReturn关联

        mockList.clear();
        mockList.add(2, "123");
        mockList.add(3, "123");
        mockList.add(4, "123");
        mockList.add(5, "123");

        //但是请记住这些add实际上什么都没有做，mock对象中仍然什么都没有
        System.out.print(mockList.get(4));

        mockList.add(1, "123");
    }

}
