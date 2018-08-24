package com.eumji.common.feign.config;

import feign.RetryableException;
import feign.Retryer;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 2018/8/24
 * @time: 22:37
 */
public class DefaultRetryerPlus implements Retryer {

    private final int maxAttempts;
    private final long period;
    private final long maxPeriod;
    int attempt;
    long sleptForMillis;

    public DefaultRetryerPlus() {
        this(100, SECONDS.toMillis(1), 5);
    }

    public DefaultRetryerPlus(long period, long maxPeriod, int maxAttempts) {
        this.period = period;
        this.maxPeriod = maxPeriod;
        this.maxAttempts = maxAttempts;
        this.attempt = 1;
    }

    // visible for testing;
    protected long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public void continueOrPropagate(RetryableException e) {
        System.out.println("continueOrPropagate 方法第 "+attempt +" 次执行");
        if (attempt++ >= maxAttempts) {
            throw e;
        }

        long interval;
        if (e.retryAfter() != null) {
            interval = e.retryAfter().getTime() - currentTimeMillis();
            if (interval > maxPeriod) {
                interval = maxPeriod;
            }
            if (interval < 0) {
                return;
            }
        } else {
            interval = nextMaxInterval();
        }
        try {
            Thread.sleep(interval);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
        sleptForMillis += interval;
    }

    /**
     * Calculates the time interval to a retry attempt. <br> The interval increases exponentially
     * with each attempt, at a rate of nextInterval *= 1.5 (where 1.5 is the backoff factor), to the
     * maximum interval.
     *
     * @return time in nanoseconds from now until the next attempt.
     */
    long nextMaxInterval() {
        long interval = (long) (period * Math.pow(1.5, attempt - 1));
        return interval > maxPeriod ? maxPeriod : interval;
    }

    @Override
    public Retryer clone() {
        return new DefaultRetryerPlus(period, maxPeriod, maxAttempts);
    }
}