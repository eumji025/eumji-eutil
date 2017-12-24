import com.eumji.eutil.jxlsplus.model.Employee;
import com.eumji.eutil.jxlsplus.utils.JxlsExcelReaderUtil;
import com.eumji.eutil.jxlsplus.utils.JxlsExcelWriteUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Test
    public void outputTest() throws FileNotFoundException {
        Map<String, Object> map = new HashMap<>();
        map.put("ems",generateSampleEmployeeData());
        OutputStream outputStream = new FileOutputStream("/home/eumji/demo.xlsx");
        JxlsExcelWriteUtil.writeDataToOutputStream("template/employeeTemplate.xlsx",map,outputStream);
    }

    public  List<Employee> generateSampleEmployeeData() {
        List<Employee> employees = new ArrayList<Employee>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd", Locale.US);
        employees.add( new Employee("Elsa", "1970-Jul-10", "33","1500", "0.15") );
        employees.add( new Employee("Oleg", "1973-Apr-30","123", "2300", "0.25") );
        employees.add( new Employee("Neil", "1975-Oct-05", "12","2500", "0.00") );
        employees.add( new Employee("Maria", "1978-Jan-07","12", "1700", "0.15") );
        employees.add( new Employee("John", "1969-May-30","12", "2800", "0.20") );
        return employees;
    }
}
