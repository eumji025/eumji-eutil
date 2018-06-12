import com.eumji.date.handler.LocalDateTimeHandler;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-2-22
 * @time: 下午5:03
 */
public class TypeHandlerDemo {
    @Test
    public void test(){
        TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();
        TypeHandler<Object> instance = typeHandlerRegistry.getInstance(LocalDateTime.class, LocalDateTimeHandler.class);

    }
}
