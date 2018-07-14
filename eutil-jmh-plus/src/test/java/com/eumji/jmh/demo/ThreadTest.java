package com.eumji.jmh.demo;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @description: write some thing of this file
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 2018-07-12
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3,time = 1,timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3,time = 1,timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class ThreadTest {

    @Benchmark
    public void test1() throws InterruptedException {
        Thread.sleep(200L);
    }

    @Benchmark
    public void test2() throws InterruptedException {
        Thread.sleep(300L);
    }


    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(ThreadTest.class.getSimpleName()).build();
        new Runner(options).run();
    }
}
