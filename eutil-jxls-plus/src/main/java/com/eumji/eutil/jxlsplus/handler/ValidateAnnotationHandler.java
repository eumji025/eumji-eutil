package com.eumji.eutil.jxlsplus.handler;

import com.eumji.eutil.jxlsplus.annotation.ValidateAnnotation;
import com.eumji.eutil.jxlsplus.enums.ImportTypeEnum;
import com.eumji.eutil.jxlsplus.utils.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

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
    @Override
    public void initialize(ValidateAnnotation constraintAnnotation) {
        type = constraintAnnotation.type();
        pattern = constraintAnnotation.pattern();
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {

            if (StringUtil.isEmpty(value)) {
                return false;
            }
            if (type == ImportTypeEnum.DATE) {
                LocalDate.parse(value, DateTimeFormatter.ofPattern(value));
            }
            if (type == ImportTypeEnum.TIME) {
                LocalTime.parse(value, DateTimeFormatter.ofPattern(value));
            }
            if (type == ImportTypeEnum.DATETIME) {
                LocalDateTime.parse(value, DateTimeFormatter.ofPattern(value));
            }

            if (type == ImportTypeEnum.NUMBER) {
                checkInfo(pattern,value);
            }
        }catch (RuntimeException e){
            return false;
        }

        return true;
    }

    /**
     * 通过正则校验
     * @param regx 正则表达式
     * @param vallue 要校验的value
     * @return {@link Boolean}
     */
    public boolean checkInfo(String regx,String vallue){
        Pattern pattern = Pattern.compile(regx);
        return pattern.matcher(vallue).matches();
    }
}
