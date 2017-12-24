package com.eumji.batch.utils;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 2017/11/29
 * @time: 22:18
 */
public abstract class BaseBatchUtil<T> {

    protected JdbcTemplate jdbcTemplate;


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 拼接插入sql
     * @param fields
     * @param tableName
     * @return
     */
    private String buildSql(Field[] fields, String tableName){
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ").append(tableName).append("(");
        for (int i = 0; i < fields.length; i++) {
            sb.append(fields[i].getName());
            if (i < fields.length-1){
                sb.append(",");
            }
        }
        sb.append(") values(");
        for (int i = 0; i < fields.length; i++) {
            sb.append("?");
            if (i < fields.length-1){
                sb.append(",");
            }else {
                sb.append(")");
            }
        }

        return sb.toString();
    }


    public int batchInsert(List<T> list,String sql){
        if (jdbcTemplate == null){
            jdbcTemplate = new JdbcTemplate();
        }
        int[] results = jdbcTemplate.batchUpdate(sql, initInsertBatchPreparedStatementSetter(list));
        return results.length;
    }

//    public int batchUpate(List<T> list,String sql){
//        int[] results = jdbcTemplate.batchUpdate(sql, initBatchPreparedStatementSetter(list));
//    }

    public int batchInsert(String tableName,List<T> list) {
        T t = list.get(0);
        Field[] fields = t.getClass().getDeclaredFields();
        String sql = buildSql(fields, tableName);
        //System.out.println(sql);
        return batchInsert(list,sql);
    }

    /**
     * 模板方法 用于自定义插入的参数
     * 默认的sql 插入所有的字段 按字段的顺序
     * @param list
     * @return
     */
    abstract BatchPreparedStatementSetter initInsertBatchPreparedStatementSetter(List<T> list);

}
