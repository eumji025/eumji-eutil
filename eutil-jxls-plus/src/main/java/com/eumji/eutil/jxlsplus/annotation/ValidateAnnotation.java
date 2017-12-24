package com.eumji.eutil.jxlsplus.annotation;

import com.eumji.eutil.jxlsplus.enums.ImportTypeEnum;
import com.eumji.eutil.jxlsplus.handler.ValidateAnnotationHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 17-12-23
 * @time: 下午8:51
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { ValidateAnnotationHandler.class})
public @interface ValidateAnnotation {
    /**
     * 格式化类型
     * @return
     */
    ImportTypeEnum type() default ImportTypeEnum.STRING;

    /**
     * 格式化pattern
     * @return
     */
    String pattern() default "";

    /**
     * 响应消息
     * @return
     */
    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
