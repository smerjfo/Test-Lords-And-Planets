package com.lordOfPlanet.demo.DAO;

import com.lordOfPlanet.demo.Model.Planet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanetMapper implements RowMapper<Planet> {
    @Override
    public Planet mapRow(ResultSet resultSet, int i) throws SQLException {
        Planet planet=new Planet();
        planet.setName(resultSet.getString("name"));
        planet.setUuid(resultSet.getString("id"));
        return planet;

    }
}
