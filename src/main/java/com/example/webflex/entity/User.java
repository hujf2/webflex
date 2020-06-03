package com.example.webflex.entity;

/**
 * @author junfeng.hu
 * @create 2020-05-27 15:00
 */
public class User {
    private String name;
    private String geneder;
    private Integer age;

    public User(){

    }

    public User(String name, String geneder, Integer age) {
        this.name = name;
        this.geneder = geneder;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeneder() {
        return geneder;
    }

    public void setGeneder(String geneder) {
        this.geneder = geneder;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
