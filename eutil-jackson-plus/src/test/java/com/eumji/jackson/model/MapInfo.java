package com.eumji.jackson.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-1-13
 * @time: 上午11:33
 */
public class MapInfo {
    private int id;
    private String name;
    private Map<String,String> map = new HashMap<String, String>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getMap() {
        return map;
    }

    @JsonAnySetter
    public void setMap(String key,String value) {
        this.map.put(key,value);
    }

    @Override
    public String toString() {
        return "MapInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", map=" + map +
                '}';
    }
}
