package com.example.test.demo.controller;

import com.example.test.demo.pojo.dto.StudentAddNewDTO;
import com.example.test.demo.service.IStudentService;
import com.example.test.demo.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "学生管理模块")
@RestController
@Validated//必须在controller添加Validated
@RequestMapping("/students")
public class StudentController {
    @Autowired
    IStudentService studentService;

    //
    @ApiOperation("学生信息添加")
    @ApiOperationSupport(order = 100)
    @ApiImplicitParam(name="id",value="学生id",required = true, dataType = "long")//配置参数说明
    @PostMapping("/add-new")
    public JsonResult<Void> addNew(@Validated StudentAddNewDTO studentAddNewDTO) {
        log.debug("开始处理【添加相册】的请求，参数：{}", studentAddNewDTO);
        studentService.addNew(studentAddNewDTO);
        return JsonResult.ok();
    }

}
