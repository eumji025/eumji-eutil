package com.eumji.eutil.pagehelper.util;


import com.eumji.eutil.pagehelper.base.Pagination;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author eumji
 * @create 2017-12-25 20:55
 */
public class BaseServiceUtil<T, R extends Page> {

    private static volatile BaseServiceUtil baseServiceUtil = null;

    private static final Logger logger = LoggerFactory.getLogger(BaseServiceUtil.class);

    @FunctionalInterface
    public interface Function<T, R> {
        /**  实际调用函数
         * @param searBean 查询条件对象
         * @return
         */
        List<T> search(R searBean);
    }


    /**
     * 通过Model获取数据
     * @param searchBean  Model数据，非null字段都做为条件查询
     * @return List 如果无数据时，返回是长度为0的List对象
     */
    public List<T> selectAll(R searchBean,Function<T, R> c) {
        List<T> result = null;
        try {
            result = c.search(searchBean);
        } catch (Exception e) {
            logger.error("searchAll method has error:{}",e);
        }
        return result;
    }

    /**
     * double check locked instance baseServiceUtil
     * @return
     */
    public static BaseServiceUtil getInstance(){
        if (baseServiceUtil == null){
            synchronized (BaseServiceUtil.class){
                if (baseServiceUtil == null){
                    baseServiceUtil = new BaseServiceUtil();
                }
            }
        }
        return baseServiceUtil;
    }

    /**
     * 用于分页查询，查询条件不限制是Pagination对象
     *
     * @param searchBean 查询条件
     * @param c
     */
    public Pagination<T> search(R searchBean, Function<T, R> c) {
        Page page = null;
        if (searchBean == null){
            page =new Page(0,10);
        }else {
            page = new Page(searchBean.getPageNum(),searchBean.getPageSize());
        }
        return executePagination(c, page, searchBean);
    }

    private Pagination executePagination(Function<T, R> c, Page<T> page, R searchBean) {
        try {
            PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.isCount());
            return new Pagination((Page) c.search(searchBean));

        } catch (Exception e) {
            logger.error("分页查询数据失败,",e);
        }
        return new Pagination(page);
    }


}
