package com.lordOfPlanet.demo.DAO;

import com.lordOfPlanet.demo.Model.Lord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class LordDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public LordDao (JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;
        setJdbcTemplate();
    }

    public void setLord(Lord lord){jdbcTemplate.update("INSERT INTO lords (age ,name) VALUES (?,?)",lord.getAge(),lord.getName());}

    /*
    Create Table "lords" with foreign key
     */
    private void setJdbcTemplate (){
        jdbcTemplate.execute("create table IF NOT EXISTS lords\n" +
            "(\n" +
            "    AGE  INTEGER not null,\n" +
            "    NAME VARCHAR(50) not null ,\n" +
            "    ID   UUID\n" +
            "        references PLANETS\n" +
            ");\n");
    }
    public Lord getLord(Lord lord){ return jdbcTemplate.query("SELECT * FROM lords WHERE name=? and age=?"
            ,new Object[]{lord.getName(),lord.getAge()},new LordMapper()).stream().findAny().orElse(null);}

    public List<Lord> getAllLord(){
        return  jdbcTemplate.query("SELECT * FROM lorddb",new LordMapper());
    }

    public void setKey(Lord lord,String uuid) { jdbcTemplate.update("UPDATE  lords SET ID=? WHERE name=? and age=? ",uuid,lord.getName(),lord.getAge());}

    public void deleteKeyFromLord(String uuid){ jdbcTemplate.update("UPDATE lords SET ID=null WHERE ID=?",uuid);}

    public List<Lord> getYoungestLords(){return jdbcTemplate.query("SELECT top 10 * FROM lords  ORDER BY age",new LordMapper()); }




}
