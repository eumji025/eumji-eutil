import com.eumji.batch.model.TestDemo;
import com.eumji.batch.model.User;
import com.eumji.batch.utils.TestDemoBatchUtil;
import com.eumji.batch.utils.UserBatchUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 17-12-24
 * @time: 上午11:39
 */
public class JdbcBatchTest {

    private DataSource dataSource;

    @Before
    public void getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/demo");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        this.dataSource = dataSource;
    }

    @Test
    public void jdbcTest(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/demo");
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
        //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

    @Test
    public void insertTest(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        TestDemoBatchUtil testDemoBatchUtil = new TestDemoBatchUtil();
        testDemoBatchUtil.setJdbcTemplate(jdbcTemplate);
        for (int i = 0; i < 4000; i++) {
            List<TestDemo> demos = new ArrayList<>();
            for (int j = 0; j < 1000; j++) {
                String value = UUID.randomUUID().toString();
                TestDemo test = new TestDemo(value, value, value, value, value);
                demos.add(test);
            }
            int count = testDemoBatchUtil.batchInsert(demos, "insert into test (name,cid1,cid2,cid3,cid4) values(?,?,?,?,?);");
            System.out.println("已经插入1000条，当前i = "+i);
        }
    }
}
