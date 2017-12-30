package com.eumji.eutil.pagehelper.base;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 17-12-25
 * @time: 下午9:59
 */
public class Pagination<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    // 传参或指定
    private int page; // 当前页号, 采用自然数计数 1,2,3,...
    private int pageSize; // 页面大小:一个页面显示多少个数据

    // 需要从数据库中查找出
    private long totalCount;// 数据总数：一共有多少个数据
    private List<T> list;

    public Pagination() {
    }

    public Pagination(Page<T> pages) {
        this.page = pages.getPageNum();
        this.pageSize = pages.getPageSize();
        this.totalCount = pages.getTotal();
        this.list = pages.getResult();
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
