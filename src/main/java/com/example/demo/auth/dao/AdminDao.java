package com.example.demo.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.auth.dto.Admin;

@Mapper
public interface AdminDao {
    public List<Admin> selectAllAdmins();
    public void insert(Admin admin);
    public void delete(String account);
    public Admin selectAdminAid(String email);
    public String selectAdminPassword(@Param("account") String account, @Param("email") String email);
    
}