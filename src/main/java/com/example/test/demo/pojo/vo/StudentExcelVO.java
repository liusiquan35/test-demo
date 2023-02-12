package com.example.test.demo.pojo.vo;

import com.example.test.demo.utils.excel.ExcelExport;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class StudentExcelVO {
    /**
     * 自定义排序序号
     */
    @ExcelExport(value = "学号")
    private String sort;
    /**
     * 姓名
     */
    @ExcelExport(value = "姓名")
    private String name;
    /**
     *名字全拼
     */
    @ExcelExport(value = "名字全拼")
    private String nameAll;
    /**
     * 性别
     */
    @ExcelExport(value = "性别")
    private String gender;
    /**
     * 证件类型
     */
    @ExcelExport(value = "证件类型")
    private String identityType;
    /**
     * 证件号
     */
    @ExcelExport(value = "证件号")
    private String identity;
    /**
     * 手机号
     */
    @ExcelExport(value = "手机号")
    private String phone;
    /**
     * 邮箱
     */
    @ExcelExport(value = "邮箱")
    private String email;
    /**
     * 出生日期
     */
    @ExcelExport(value = "出生日期")
    private String birthday;
    /**
     * 数据创建时间
     */
    @ExcelExport(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 数据最后修改时间
     */
    @ExcelExport(value = "修改时间")
    private LocalDateTime updateTime;
}
