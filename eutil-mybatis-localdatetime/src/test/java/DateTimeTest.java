import com.eumji.date.DateApplication;
import com.eumji.date.model.UserInfo;
import com.eumji.date.service.DateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        System.out.println(userById.getBirthday());
    }
}
