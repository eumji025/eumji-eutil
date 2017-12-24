import com.eumji.batch.model.User;
import com.eumji.batch.utils.UserBatchUtil;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 17-12-24
 * @time: 上午11:39
 */
public class JdbcBatchTest {

    @Test
    public void jdbcTest(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/eutil");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        UserBatchUtil userBatchUtil = new UserBatchUtil();
        userBatchUtil.setJdbcTemplate(jdbcTemplate);
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setAge(1);
        user.setName("zhangsan");
        users.add(user);

        User user2 = new User();
        user2.setAge(12);
        user2.setName("zhangsan22");
        users.add(user2);
        userBatchUtil.batchInsert(users,"insert into user(name,age) value(?,?)");
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
}
