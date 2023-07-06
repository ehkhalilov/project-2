package com.example.texnospring1222.model;

import java.util.List;

public class CustomerDto {
    private String name;
    private Integer age;
    private String serialNumber;
    private String fin;
    private Long expireDay;


    public CustomerDto() {
    }

    public CustomerDto(String name, Integer age, String serialNumber, String fin, Long expireDay) {
        this.name = name;
        this.age = age;
        this.serialNumber = serialNumber;
        this.fin = fin;
        this.expireDay = expireDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public Long getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(Long expireDay) {
        this.expireDay = expireDay;
    }
}
