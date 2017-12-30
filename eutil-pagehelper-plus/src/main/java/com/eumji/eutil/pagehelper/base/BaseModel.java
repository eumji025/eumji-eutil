package com.eumji.eutil.pagehelper.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 17-12-25
 * @time: 下午9:30
 */
public class BaseModel<T> implements Serializable {
    /**
     * primary key
     */
    private T id;
    /**
     * creator
     */
    private String creator;
    /**
     * createDate
     */
    private Date createDate;


}
