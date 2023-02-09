package com.example.test.demo.pojo.vo;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 学生excel导出表
 *
 * @author java@lsq
 * @version 0.0.1
 */
@Data
public class StudentExcelVO {
    private Long id;
    /**
     * 自定义排序序号
     */
    private Integer sort;
    /**
     * 姓名
     */
    private String name;
    /**
     *名字全拼
     */
    private String nameAll;
    /**
     * 性别
     */
    private String gender;
    /**
     * 身份证类型
     */
    private String identityType;
    /**
     * 身份证号
     */
    private String identity;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 数据创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    private LocalDateTime gmtModified;

}
