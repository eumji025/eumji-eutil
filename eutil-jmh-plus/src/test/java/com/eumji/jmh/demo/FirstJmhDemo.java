package com.eumji.jmh.demo;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import sun.security.provider.Sun;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: write some thing of this file
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 2018-07-03
 */
public class FirstJmhDemo {

    @Benchmark
    public void testMethod() {

        int  a = 2;

        int b = 3;

        for (int i = 0; i < 100; i++) {
            int c = a + b;
            a = b;
            b = c;
        }

    }

}
