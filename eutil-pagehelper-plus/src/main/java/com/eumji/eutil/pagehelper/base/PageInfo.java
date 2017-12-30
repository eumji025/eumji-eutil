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
     * sort way
     */
    private String sort = "desc";
    /**
     * sort param
     */
    private String sortParam;

    /**
     * what id you need
     */
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
