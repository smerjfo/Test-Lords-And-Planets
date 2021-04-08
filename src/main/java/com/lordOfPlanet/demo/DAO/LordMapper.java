package com.lordOfPlanet.demo.DAO;

import com.lordOfPlanet.demo.Model.Lord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LordMapper implements RowMapper<Lord> {
    @Override
    public Lord mapRow(ResultSet resultSet, int i) throws SQLException {
        Lord lord=new Lord();
        lord.setName(resultSet.getString("name"));
        lord.setAge(resultSet.getInt("age"));
        lord.setUuid(resultSet.getString("id"));
        return lord;
    }
}
