package com.lordOfPlanet.demo.Model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Lord {
    @NotEmpty(message = "Name should be not empty")
    @Size(min=2,max=50,message = "Name should be between 2 and 50 characters")
    private String name;

    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
