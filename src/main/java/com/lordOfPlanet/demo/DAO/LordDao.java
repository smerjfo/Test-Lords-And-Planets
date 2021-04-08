package com.lordOfPlanet.demo.DAO;

import com.lordOfPlanet.demo.Model.Lord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;


@Component
public class LordDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public LordDao (JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;
        setJdbcTemplate();
    }

    public void setLord(Lord lord){jdbcTemplate.update("INSERT INTO lords (age ,name,id) VALUES (?,?,?)",lord.getAge(),lord.getName(), UUID.randomUUID());}

    /*
    Create Table "lords" with foreign key
     */
    private void setJdbcTemplate (){
        jdbcTemplate.execute("create table If not exists LORDS \n"+
                "(\n"+
                    "AGE  INTEGER     not null,\n"+
                    "NAME VARCHAR(50) not null,\n"+
                    "ID   UUID not null\n"+
                    "constraint UUID\n"+
                    "primary key\n"+
                ");");
    }
    public Lord getLord(Lord lord){ return jdbcTemplate.query("SELECT * FROM lords WHERE name=? and age=?"
            ,new Object[]{lord.getName(),lord.getAge()},new LordMapper()).stream().findAny().orElse(null);}

    public List<Lord> getAllLord(){
        return  jdbcTemplate.query("SELECT * FROM lords",new LordMapper());
    }





    public List<Lord> getYoungestLords(){return jdbcTemplate.query("SELECT top 10 * FROM lords  ORDER BY age",new LordMapper()); }




}
