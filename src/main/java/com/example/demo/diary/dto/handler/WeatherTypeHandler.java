package com.example.demo.diary.dto.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.example.demo.diary.enums.Weather;


public class WeatherTypeHandler extends BaseTypeHandler<Weather> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Weather parameter, JdbcType jdbcType) throws SQLException {
        // DB에 저장할 때 enum 이름(String)으로 저장
        ps.setString(i, parameter.name());
    }

    @Override
    public Weather getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String name = rs.getString(columnName);
        return name == null ? null : Weather.valueOf(name);
    }

    @Override
    public Weather getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String name = rs.getString(columnIndex);
        return name == null ? null : Weather.valueOf(name);
    }

    @Override
    public Weather getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String name = cs.getString(columnIndex);
        return name == null ? null : Weather.valueOf(name);
    }
}