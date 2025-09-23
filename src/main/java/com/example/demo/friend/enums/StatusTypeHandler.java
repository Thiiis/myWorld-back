package com.example.demo.friend.enums;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import com.example.demo.friend.enums.FriendStatus;

public class StatusTypeHandler extends BaseTypeHandler<FriendStatus>{
  @Override
    public void setNonNullParameter(PreparedStatement ps, int i, FriendStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public FriendStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String name = rs.getString(columnName);
        return name == null ? null : FriendStatus.valueOf(name);
    }

    @Override
    public FriendStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String name = rs.getString(columnIndex);
        return name == null ? null : FriendStatus.valueOf(name);
    }

    @Override
    public FriendStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String name = cs.getString(columnIndex);
        return name == null ? null : FriendStatus.valueOf(name);
    }
}
