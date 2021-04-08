package com.lordOfPlanet.demo.Model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Planet {
    @NotEmpty(message = "Name should be not empty")
    @Size(min=2,max=50,message = "Name should be between 2 and 50 characters")
    private String name;

    private String uuid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
