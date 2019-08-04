package com.eumji.eutil.jxlsplus.utils;

import com.eumji.eutil.jxlsplus.model.BaseError;
import com.eumji.eutil.jxlsplus.model.Employee;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import com.eumji.eutil.jxlsplus.utils.StringUtil;

/**
 * 可扩展具体实现类进行额外的操作
 *
 * 提供一个模板方法extraAction进行扩展
 * 比如我们需要根据表格中的code得到name 然后记录到库中
 *
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 17-12-23
 * @time: 下午7:54
 */
public class JxlsExcelReaderUtil<T extends BaseError> {
    private static Validator validator;
    private Logger logger = LoggerFactory.getLogger(JxlsExcelReaderUtil.class);
    private static JxlsExcelReaderUtil util = new JxlsExcelReaderUtil();
    public static JxlsExcelReaderUtil getInstance(){
        if (validator == null){
            setUpValidator();
        }
        return util;
    }

    /**
     * 初始化 validator
     */
    public static synchronized void setUpValidator() {
        if (validator == null) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            validator = factory.getValidator();
        }
    }

    /**
     * 从excel获取信息列表
     * @param xmlPath xml路径
     * @param file 文件
     * @return 返回获取的列表
     * @throws IOException
     * @throws SAXException
     * @throws InvalidFormatException
     */
    public List<T> getInfoFromFile(String xmlPath,File file) throws IOException, SAXException, InvalidFormatException {
        try (
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        ){
            InputStream template = JxlsUtils.getXMlConfig(xmlPath);
            XLSReader xlsReader = ReaderBuilder.buildFromXML(template);
            List<T> list = new ArrayList();
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("items", list);
            XLSReadStatus read = xlsReader.read(bufferedInputStream, map);
            if (!read.isStatusOK()) {
                logger.error("read excel state error");
            }
            return list;
        }
    }

    /**
     * 数据校验是否有错
     * @param list
     * @return
     */
    public boolean dataCheck(List<T> list){
        boolean hasError = false;
        for (T item : list) {
            StringBuilder sb = new StringBuilder();
            //通过validator验证,如果存在错误信息,则有错
            Set<ConstraintViolation<T>> set = validator.validate(item);
            for (ConstraintViolation<T> constraintViolation : set) {
                //System.out.println(constraintViolation.getPropertyPath()+":"+constraintViolation.getMessage());
                sb.append(constraintViolation.getMessage());
            }
            if (sb.length() > 0){
                item.setErrorInfo(sb.toString());
                hasError = true;
            }
            extraAction(item);
        }
        return hasError;
    }

    /**
     * 托扩展的额外操作模板方法
     * @param item 数据对象
     */
    public void extraAction(T item){};
}
