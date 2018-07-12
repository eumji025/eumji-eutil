package com.eumji.jmh.demo;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @description: write some thing of this file
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 2018-07-12
 */
@State(Scope.Benchmark)
@Warmup(iterations = 3,time = 1,timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Measurement(iterations = 3,time = 1,timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@Threads(Threads.MAX)
public class LoggerTest {
    public static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Benchmark
    public void replaceTest(){
        logger.info("hello :{},this is my info：{}","zhangsan","lalalalalllllllllllll");
    }

    @Benchmark
    public void appendTest(){
        logger.info("hello :"+"zhangsan"+",this is my info："+"lalalalalllllllllllll");
    }

    @Benchmark
    public void append2Test(){
        logger.info("hello :zhangsan,this is my info:"+"lalalalalllllllllllll");
    }

    @Benchmark
    public void formatTest(){
        logger.info(String.format("hello :%s,this is my info:%s","zhangsan","lalalalalllllllllllll"));
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(LoggerTest.class.getSimpleName()).build();
        new Runner(options).run();
    }


}
