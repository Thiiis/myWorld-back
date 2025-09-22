package com.example.demo.diary.dto.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.aot.generate.AccessControl.Visibility;


public class ViewScopeTypeHandler extends BaseTypeHandler<Object> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        if (parameter instanceof Visibility) {
            ps.setString(i, ((Visibility) parameter).name());
        } else {
            ps.setString(i, parameter.toString()); // 안전하게 문자열 처리
        }
    }

    @Override
    public Visibility getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : Visibility.valueOf(value);
    }

    @Override
    public Visibility getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : Visibility.valueOf(value);
    }

    @Override
    public Visibility getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : Visibility.valueOf(value);
    }
}