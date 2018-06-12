package com.eumji.eutil.pagehelper.base;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 17-12-25
 * @time: 下午9:30
 */
public class BaseModel<T> implements Serializable {
    /**
     * 主键
     */
    private T id;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updator;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
