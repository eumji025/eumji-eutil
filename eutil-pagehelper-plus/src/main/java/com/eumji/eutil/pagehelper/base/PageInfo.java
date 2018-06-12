package com.eumji.eutil.pagehelper.base;

import com.github.pagehelper.Page;

/**
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 17-12-25
 * @time: 下午9:46
 */
public class PageInfo<E> extends Page<E> {
    /**
     * 排序方式
     */
    private String sort = "desc";
    /**
     * 排序字段
     */
    private String sortParam;

    private String id;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortParam() {
        return sortParam;
    }

    public void setSortParam(String sortParam) {
        this.sortParam = sortParam;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
