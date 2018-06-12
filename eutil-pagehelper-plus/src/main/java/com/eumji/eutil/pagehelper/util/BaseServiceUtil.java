package com.eumji.eutil.pagehelper.util;


import com.eumji.eutil.pagehelper.base.Pagination;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 *  分页的工具类  通过lambda表达式和pageHelper进行分页
 * @author eumji
 * @create 2017-12-25 20:55
 */
public class BaseServiceUtil<T, R extends Page> {


    private static final Logger logger = LoggerFactory.getLogger(BaseServiceUtil.class);

    @FunctionalInterface
    public interface Function<T, R> {
        /**  查询数据方法
         * @param searBean 查询条件对象
         * @return
         */
        List<T> search(R searBean);
    }


    /**
     * 查询所有
     * @param searchBean  Model数据，非null字段都做为条件查询
     * @return List 如果无数据时，返回是长度为0的List对象
     */
    public static <T,R extends Page> List<T> searchAll(R searchBean,Function<T, R> c) {
        List<T> result = null;
        try {
            result = c.search(searchBean);
        } catch (Exception e) {
            logger.error("searchAll method has error:{}",e);
        }
        return result;
    }

    /**
     * 用于分页查询，查询条件不限制是Pagination对象
     *
     * @param searchBean 查询条件
     * @param c
     */
    public static <T,R extends Page> Pagination<T> searchByParam(R searchBean, Function<T, R> c) {
        Page<T> page;
        if (searchBean == null){
            page =new Page<>(0,10);
        }else {
            page = new Page<>(searchBean.getPageNum(), searchBean.getPageSize());
        }
        try {
            PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.isCount());
            return new Pagination<>((Page<T>) c.search(searchBean));

        } catch (Exception e) {
            logger.error("通用分页查询数据出现异常,异常原因:",ExceptionUtil.printException(e));
        }
        return new Pagination<>(page);
    }


}
