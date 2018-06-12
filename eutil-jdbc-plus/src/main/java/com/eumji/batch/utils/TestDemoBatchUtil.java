package com.eumji.batch.utils;

import com.eumji.batch.model.TestDemo;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-3-22
 * @time: 下午9:32
 */
public class TestDemoBatchUtil extends BaseBatchUtil<TestDemo> {
    @Override
    BatchPreparedStatementSetter initInsertBatchPreparedStatementSetter(List<TestDemo> list) {
        return new BatchPreparedStatementSetter(){
            //如何绑定变量
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                TestDemo demo = list.get(i);
                ps.setString(1,demo.getName());
                ps.setString(2,demo.getCid1());
                ps.setString(3,demo.getCid2());
                ps.setString(4,demo.getCid3());
                ps.setString(5,demo.getCid4());
                //ps.setDate();
            }
            //插入的条数
            @Override
            public int getBatchSize() {
                return list.size();
            }
        };
    }
}
