package com.eumji.eutil.jxlsplus.model;

import com.eumji.eutil.jxlsplus.annotation.ValidateAnnotation;
import com.eumji.eutil.jxlsplus.enums.ImportTypeEnum;

import javax.validation.constraints.NotNull;

/**
 *  员工信息excel接受类
 *  全部使用String 方便我们记录信息
 *  防止有业务场景需要验证excel的格式和完整性
 *
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 17-12-23
 * @time: 下午7:52
 */
public class Employee extends BaseError {
    @NotNull(message = "hello")
    private String name;
    private String age;
    @ValidateAnnotation(type = ImportTypeEnum.DATE,pattern = "yyyy-MM-dd",message = "payment类型错误")
    private String payment;
    private String bonus;
    @NotNull(message = "生日不能为空")
    @ValidateAnnotation(type = ImportTypeEnum.DATE,pattern = "yyyy-MM-dd",message = "birthDate时间错误")
    private String birthDate;

    public Employee() {
    }

    public Employee(String name, String age, String payment, String bonus,String birthDate) {
        this.name = name;
        this.age = age;
        this.payment = payment;
        this.bonus = bonus;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", payment='" + payment + '\'' +
                ", bonus='" + bonus + '\'' +
                ", birthDate='" + birthDate + '\'' +super.toString()+
                '}';
    }
}
