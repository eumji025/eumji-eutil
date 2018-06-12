package com.eumji.batch.model;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-3-22
 * @time: 下午9:26
 */
public class TestDemo {

    private String name;
    private String cid1;
    private String cid2;
    private String cid3;
    private String cid4;

    public TestDemo(String name, String cid1, String cid2, String cid3, String cid4) {
        this.name = name;
        this.cid1 = cid1;
        this.cid2 = cid2;
        this.cid3 = cid3;
        this.cid4 = cid4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid1() {
        return cid1;
    }

    public void setCid1(String cid1) {
        this.cid1 = cid1;
    }

    public String getCid2() {
        return cid2;
    }

    public void setCid2(String cid2) {
        this.cid2 = cid2;
    }

    public String getCid3() {
        return cid3;
    }

    public void setCid3(String cid3) {
        this.cid3 = cid3;
    }

    public String getCid4() {
        return cid4;
    }

    public void setCid4(String cid4) {
        this.cid4 = cid4;
    }
}
