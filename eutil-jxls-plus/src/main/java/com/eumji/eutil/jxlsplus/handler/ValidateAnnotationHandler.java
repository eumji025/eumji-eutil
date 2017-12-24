package com.eumji.eutil.jxlsplus.handler;

import com.eumji.eutil.jxlsplus.annotation.ValidateAnnotation;
import com.eumji.eutil.jxlsplus.enums.ImportTypeEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;

/**
 * 自定义验证类
 *
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 17-12-23
 * @time: 下午8:54
 */
public class ValidateAnnotationHandler implements ConstraintValidator<ValidateAnnotation,String> {
    private ImportTypeEnum type;
    private String pattern;
    SimpleDateFormat sf;
    public void initialize(ValidateAnnotation constraintAnnotation) {
        type = constraintAnnotation.type();
        pattern = constraintAnnotation.pattern();
        sf = new SimpleDateFormat(pattern);
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            switch (type){
                case DATE:
                    sf.parse(value);
                    break;
                case DOUBLE:
                    Double.parseDouble(value);
                    break;
                case INTEGER:
                    Integer.parseInt(value);
                    break;
                case DATETIME:
                    sf.parse(value);
                    break;
                default:
                    return true;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
