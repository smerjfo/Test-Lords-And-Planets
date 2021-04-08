package com.lordOfPlanet.demo.autoTest;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import static org.junit.Assert.assertEquals;



public class FirstAutoTest extends SettingsTest{

    @Test
    public void addLord()  {
         driver.get("http://localhost:8080/hello/add-lord");
         title=driver.getTitle();
         assertEquals("Add new Lord",title);
         driver.findElement(By.id("name")).sendKeys("Darth Vader");
         driver.findElement(By.id("age")).sendKeys("54");
         driver.findElement(By.id("button")).click();
    }
    @Test
    public void addPlanet(){
        driver.get("http://localhost:8080/hello/add-planet");
        title=driver.getTitle();
        assertEquals("Add new Planet",title);
        driver.findElement(By.id("nameOfPlanet")).sendKeys("The Death Star");
        driver.findElement(By.id("button")).click();
    }

    @Test
    public void destroyPlanetTest() {
        driver.get("http://localhost:8080/hello/destroy");
        title =driver.getTitle();
        assertEquals("Destroy Planet",title);
        driver.findElement(By.id("name")).sendKeys("The Death Star");
        driver.findElement(By.id("button")).click();
    }
    @Test
    public void getYoungestLords(){

        driver.get("http://localhost:8080/hello/get-youngest");
         title =driver.getTitle();
        assertEquals("Get Youngest",title);
        driver.findElement(By.id("button")).click();
    }
    @Test
    public void setLordToPlanet(){
        driver.get("http://localhost:8080/hello/set-lord");
        title=driver.getTitle();
        assertEquals("Set-lord-on-planet",title);
        driver.findElement(By.id("lord")).sendKeys("Darth Vader");
        driver.findElement(By.id("age")).sendKeys("54");
        driver.findElement(By.id("planet")).sendKeys("Tatuin");
        driver.findElement(By.id("button")).click();
    }
    @Test
    public void getAllLounger(){
        driver.get("http://localhost:8080/hello/list-loungers");
        title =driver.getTitle();
        assertEquals("List Loungers",title);
        driver.findElement(By.id("button")).click();
    }


}
