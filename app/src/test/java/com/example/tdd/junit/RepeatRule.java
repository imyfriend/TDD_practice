package com.example.tdd.junit;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class RepeatRule implements TestRule {

    //这里定义一个注解，用于动态在测试方法里指定重复次数
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    public @interface Repeat {
        int count();
    }

    @Override
    public Statement apply(final Statement base, final Description description) {
        Statement repeatStatement =  new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Repeat repeat = description.getAnnotation(Repeat.class);
                //如果有@Repeat注解，则会重复执行指定次数
                if(repeat != null) {
                    for(int i=0; i < repeat.count(); i++) {
                        base.evaluate();
                    }
                } else {
                    //如果没有注解，则不会重复执行
                    base.evaluate();
                }
            }
        };
        return repeatStatement;
    }
}
