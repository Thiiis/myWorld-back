package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.User;

/*
CREATE TABLE "User" (
    "userId" NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "account" VARCHAR2(20 CHAR) NOT NULL UNIQUE,
    "email" VARCHAR2(255 CHAR) NOT NULL UNIQUE,
    "password" VARCHAR2(255) NOT NULL,
    "createdAt" TIMESTAMP WITH TIME ZONE DEFAULT SYSTIMESTAMP NOT NULL,
    "updatedAt" TIMESTAMP WITH TIME ZONE DEFAULT SYSTIMESTAMP NOT NULL
);
 */


@Mapper
public interface UserDao {
    public int insert(User user);
    public User selectByAccount(String account);
    public int update(User user);
    public int delete(String account);
    
}