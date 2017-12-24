package com.eumji.batch.utils;

import com.eumji.batch.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * 示例 需要绑定连接池
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 2017/11/29
 * @time: 21:02
 */
public class UserBatchUtil extends BaseBatchUtil<User> {
    /**
     * 自定义插入的字段
     * 如果不需要插入所有的地段,重写 buildSql方法
     * @param list
     * @return
     */
    @Override
    BatchPreparedStatementSetter initInsertBatchPreparedStatementSetter(List<User> list) {
        return new BatchPreparedStatementSetter(){
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                User user = list.get(i);
                ps.setString(1,user.getName());
                ps.setInt(2,user.getAge());
                //ps.setDate();
            }
            @Override
            public int getBatchSize() {
                return list.size();
            }
        };
    }

}
