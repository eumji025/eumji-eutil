package com.eumji.date.plugin;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-2-21
 * @time: 下午3:24
 */
@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        }
)
public class OutInterceptor implements Interceptor {
    private static Logger logger = LoggerFactory.getLogger(OutInterceptor.class);
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.info("I hand interceptor this query method:{} , and the param is:[{}]",invocation.getMethod().getName(), Arrays.asList(invocation.getArgs()));
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
