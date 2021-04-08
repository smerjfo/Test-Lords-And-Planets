package com.lordOfPlanet.demo.DAO;
import com.lordOfPlanet.demo.Model.Lord;
import com.lordOfPlanet.demo.Model.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;

@Component
public class PlanetDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlanetDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
        setJdbcTemplatePlanet();
    }
    /*
    Create Table "planets"  with primary key
    */
    private void setJdbcTemplatePlanet(){
        jdbcTemplate.execute("create table If not exists PLANETS"+
                "( \n"+
                "NAME VARCHAR(50) not null unique,\n"+
                "ID   UUID    \n"+
                "references LORDS \n"+
                ");");
    }
    public List<Planet> getPlanets(){return jdbcTemplate.query("SELECT * FROM planets",new PlanetMapper());}

    public Planet getPlanet(String name){ return jdbcTemplate.query("SELECT * FROM planets WHERE name=?"
            ,new Object[]{name},new PlanetMapper()).stream().findAny().orElse(null);}

    public void destroyPlanet (String name) { jdbcTemplate.update("DELETE FROM planets WHERE name=?",name);}

    public List<Planet> getPlanetById(String uuid){ return jdbcTemplate.query("SELECT * FROM planets WHERE id=?",new Object[]{uuid},new PlanetMapper());}

    public void setKey(String planet, String uuid) { jdbcTemplate.update("UPDATE  PLANETS SET ID=? WHERE name=?  ",uuid,planet);}

    public  void setNamePlanet (String name){ jdbcTemplate.update("INSERT INTO planets (name) VALUES (?)",name);}

}
