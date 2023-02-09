package com.example.test.demo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class StudentExcelDTO {
    /**
     * 学生名称
     */
    @ApiModelProperty(value = "学生名称", example = "小明", required = true)
    @NotNull(message = "添加学生姓名失败，必须提交学生名称！")
    private String name;

    /**
     * 学生名称全拼
     */
    @ApiModelProperty(value = "学生名称全拼", example = "xiaoming", required = true)
    @NotNull(message = "添加学生姓名全拼失败，必须提交学生名称全拼！")
    private String nameAll;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", example = "男", required = true)
    @NotNull(message = "添加学生姓名全拼失败，必须提交学生名称全拼！")
    private String gender;

    /**
     * 证件类别
     */
    @ApiModelProperty(value = "证件类别", example = "身份证", required = true)
    @NotNull(message = "证件类别添加失败，必须提交！")
    private String identityType;

    /**
     * 证件号
     */
    @ApiModelProperty(value = "身份证号", example = "330825200107112111", required = true)
    @NotNull(message = "身份证号添加失败，必须提交！")
    private String identity;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期", example = "20010711", required = true)
    @NotNull(message = "出生日期添加失败，必须提交！")
    private String birthday;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", example = "17858595066", required = true)
    @NotNull(message = "手机号添加失败，必须提交！")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", example = "458503910@qq.com", required = true)
    @NotNull(message = "邮箱添加失败，必须提交！")
    private String email;
}
