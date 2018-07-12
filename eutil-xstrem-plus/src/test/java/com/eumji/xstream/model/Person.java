package com.eumji.xstream.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: write some thing of this file
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 2018-07-12
 */
public class Person {
    private String name;
    private String address;
    private int age;
    private List<Relationship> relationship;


    public List getRelationship() {
        return relationship;
    }

    public void setRelationship(List<Relationship> relationship) {
        this.relationship = relationship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public static class Relationship{
        private String name;
        private String description;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
