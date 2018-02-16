import com.eumji.date.DateApplication;
import com.eumji.date.mapper.DateMapper;
import com.eumji.date.model.UserInfo;
import com.eumji.date.service.DateService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-2-5
 * @time: 下午5:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DateApplication.class)
public class DateTimeTest {

    @Autowired
    DateService dateService;

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void test(){
        UserInfo userInfo = new UserInfo();
        userInfo.setName("zhangsan");
        userInfo.setAge(18);
        userInfo.setBirthday(LocalDateTime.of(1994,6,18,12,12,12));
        int i = dateService.saveUser(userInfo);
        System.out.println(i);
    }


    @Test
    public void getUser(){
        UserInfo userById = dateService.getUserById(1);
        //UserInfo userById2 = dateService.getUserById(1);
        //System.out.println(userById.getBirthday());
    }

    @Test
    public void sqlsessionDemo(){
//        InputStream resourceAsStream = this.getClass().getResourceAsStream("classpath:mybatis-conf.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        DateMapper mapper = sqlSession.getMapper(DateMapper.class);
//        UserInfo user = mapper.getUser(1);
//        UserInfo user2 = mapper.getUser(1);
//        System.out.println(user);
    }
}
