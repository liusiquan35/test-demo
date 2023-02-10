package com.example.test.demo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class StudentExcelDTO {
    /**
     * 学生名称
     */

    private String name;

    /**
     * 学生名称全拼
     */

    private String nameAll;

    /**
     * 性别
     */

    private String gender;

    /**
     * 证件类别
     */
    private String identityType;

    /**
     * 证件号
     */

    private String identity;

    /**
     * 出生日期
     */

    private String birthday;

    /**
     * 手机号
     */

    private String phone;

    /**
     * 邮箱
     */
    private String email;

    public static String convert(String str){
        switch (str){
            case "学号": return  "sort";
            case "姓名":  return "name";
            case "姓名全拼":  return "nameAll";
            case "性别":  return "gender";
            case "证件类型":  return "identityType";
            case "证件号":  return "identity";
            case "出生日期":  return "birthday";
            case "手机号":  return "phone";
            case "邮箱":  return "email";
            default: return  str;
        }
    }
}
