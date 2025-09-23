package com.example.demo.auth.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)// 실행할 때 적용
@Target(ElementType.METHOD)// 메소드에 적용된다
public @interface Login {

    
}
