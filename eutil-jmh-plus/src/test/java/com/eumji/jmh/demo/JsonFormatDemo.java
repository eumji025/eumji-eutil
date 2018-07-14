package com.eumji.jmh.demo;

import com.alibaba.fastjson.JSON;
import com.eumji.jmh.demo.model.Person;
import com.eumji.jmh.demo.model.Relationship;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @description: write some thing of this file
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 2018-07-07
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3,time = 1,timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3,time = 1,timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(Threads.MAX)
public class JsonFormatDemo {

    private static Gson gson = new GsonBuilder()
            .enableComplexMapKeySerialization()
             .serializeNulls()
             .setDateFormat(DateFormat.LONG)
             .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
             .setPrettyPrinting()
             .setVersion(1.0)
             .create();

    private static ObjectMapper mapper = new ObjectMapper();

    @Benchmark
    public void GsonFormatDemo(){
        String s = gson.toJson(initPerson());
        //System.out.println(s);
    }

    @Benchmark
    public void jackson() throws JsonProcessingException {
        String value = mapper.writeValueAsString(initPerson());
        //System.out.println(value);
    }

    @Benchmark
    public void fastJson(){
        String s = JSON.toJSONString(initPerson());
        //System.out.println(s);
    }
    @Benchmark
    public void jsoninte(){
        String serialize = JsonStream.serialize(initPerson());
        //System.out.println(serialize);
    }


    public static Person initPerson(){
        Person person = new Person();
        person.setName("grand");
        person.setAddress("shenzhen");
        person.setAge(18);
        person.setBirthday(new Date(1994,6,8));
        ArrayList<Relationship> relationships = new ArrayList<>();
        person.setRelationship(relationships);
        Relationship relationship = new Relationship();
        relationship.setName("lisi");
        relationship.setDescription("good partner");
        relationships.add(relationship);

        Relationship relationship2 = new Relationship();
        relationship2.setName("wanger");
        relationship2.setDescription("collage classmate");
        relationships.add(relationship2);
        return person;
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(JsonFormatDemo.class.getSimpleName()).build();
        new Runner(options).run();
    }

}
