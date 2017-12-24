import com.eumji.eutil.jxlsplus.model.Employee;
import com.eumji.eutil.jxlsplus.utils.JxlsExcelReaderUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 17-12-24
 * @time: 上午10:03
 */
public class JxlsTest {


    @Test
    public void jxlsDemo(){
        JxlsExcelReaderUtil<Employee> instance = JxlsExcelReaderUtil.getInstance();
        try {
            List<Employee> infoFromFile = instance.getInfoFromFile("xml/EmployeeConfig.xml", new File("/home/eumji/Documents/employee.xlsx"));
            instance.dataCheck(infoFromFile);
            System.out.println(infoFromFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
//        StringBuffer stringBuffer = new StringBuffer();
//        System.out.println(stringBuffer.length());
//        Employee employee = new Employee();
//        employee.setName("zhangsan");
//        employee.setPayment("123");
//        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
//        Validator validator = vf.getValidator();
//        Set<ConstraintViolation<Employee>> set = validator.validate(employee);
//        for (ConstraintViolation<Employee> constraintViolation : set) {
//            System.out.println(constraintViolation.getPropertyPath()+":"+constraintViolation.getMessage());
//        }
    }
}
