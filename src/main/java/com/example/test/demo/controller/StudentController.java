package com.example.test.demo.controller;

import com.example.test.demo.pagehelder.JsonPage;
import com.example.test.demo.pojo.dto.StudentAddNewDTO;
import com.example.test.demo.pojo.dto.StudentUpdateDTO;
import com.example.test.demo.pojo.vo.StudentListItemVO;
import com.example.test.demo.pojo.vo.StudentVO;
import com.example.test.demo.service.IStudentService;
import com.example.test.demo.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Api(tags = "学生管理模块")
@RestController
@Validated//必须在controller添加Validated
@RequestMapping("/students")
public class StudentController {

    @Autowired
    IStudentService studentService;

    @ApiOperation("学生信息添加")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult<Void> addNew(@Validated StudentAddNewDTO studentAddNewDTO) {
        log.debug("开始处理【添加相册】的请求，参数：{}", studentAddNewDTO);
        studentService.addNew(studentAddNewDTO);
        return JsonResult.ok();
    }
    @ApiOperation("学生信息批量添加")
    @ApiOperationSupport(order = 150)
    @PostMapping("/import")
    public JsonResult<Void> importStudentList(@RequestPart("file") MultipartFile file) throws Exception {

        return JsonResult.ok();
    }

    //
    @ApiOperation("删除学生信息")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParam(name = "id", value = "学生id", required = true, dataType = "long")
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("开始处理【删除学生信息】的请求，参数：{}", id);
        studentService.delete(id);
        return JsonResult.ok();
    }

    //
    @ApiOperation("修改学生信息详情")
    @ApiOperationSupport(order = 300)
    @ApiImplicitParam(name = "id", value = "学生id", required = true, dataType = "long")
    @PostMapping("/{id:[0-9]+}/update")
    public JsonResult<Void> updateById(@PathVariable Long id, StudentUpdateDTO studentUpdateDTO) {
        log.debug("开始处理【修改学生信息详情】的请求：id={}, brandUpdateDTO={}", id, studentUpdateDTO);
        studentService.updateById(id, studentUpdateDTO);
        return JsonResult.ok();
    }

    //
    @ApiOperation("根据id查询学生信息详情")
    @ApiOperationSupport(order = 400)
    @ApiImplicitParam(name = "id", value = "学生id", required = true, dataType = "long")
    @GetMapping("/{id:[0-9]+}")
    public JsonResult<StudentVO> getStandardById(@PathVariable Long id) {
        log.debug("开始处理【根据id查询学生信息详情】的请求：id={}", id);
        StudentVO album = studentService.getStandardById(id);
        return JsonResult.ok(album);
    }

    //
    @ApiOperation("分页查询学生信息列表")
    @ApiOperationSupport(order = 420)
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码",name="page",example = "1"),
            @ApiImplicitParam(value = "每页条数",name="pageSize",example = "20")
    })
    public JsonResult<JsonPage<StudentListItemVO>> getAllPage(Integer page,Integer pageSize){
        //靠分页信息查学生数据
        JsonPage<StudentListItemVO> jsonPage=studentService.getAllStudentByPage(page,pageSize);
        //成功,以json格式返回信息
        return JsonResult.ok(jsonPage);

    }

}
