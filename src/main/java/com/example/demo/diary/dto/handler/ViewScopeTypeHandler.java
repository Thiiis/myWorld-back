package com.example.demo.diary.dto.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.example.demo.diary.enums.ViewScope;

public class ViewScopeTypeHandler extends BaseTypeHandler<ViewScope> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ViewScope parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public ViewScope getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : ViewScope.valueOf(value);
    }

    @Override
    public ViewScope getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : ViewScope.valueOf(value);
    }

    @Override
    public ViewScope getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : ViewScope.valueOf(value);
    }
}
