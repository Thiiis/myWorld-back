package com.example.demo.diary.dto.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import com.example.demo.diary.dto.Emo;



public class EmoTypeHandler extends BaseTypeHandler<Emo> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Emo parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public Emo getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String name = rs.getString(columnName);
        return name == null ? null : Emo.valueOf(name);
    }

    @Override
    public Emo getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String name = rs.getString(columnIndex);
        return name == null ? null : Emo.valueOf(name);
    }

    @Override
    public Emo getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String name = cs.getString(columnIndex);
        return name == null ? null : Emo.valueOf(name);
    }
}
