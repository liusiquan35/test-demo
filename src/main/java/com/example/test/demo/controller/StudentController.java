package com.example.test.demo.controller;

import com.example.test.demo.pagehelper.JsonPage;
import com.example.test.demo.pojo.dto.IdsDTO;
import com.example.test.demo.pojo.dto.StudentAddNewDTO;
import com.example.test.demo.pojo.dto.StudentExcelDTO;
import com.example.test.demo.pojo.dto.StudentUpdateDTO;
import com.example.test.demo.pojo.vo.StudentExcelVO;
import com.example.test.demo.pojo.vo.StudentListItemVO;
import com.example.test.demo.pojo.vo.StudentVO;
import com.example.test.demo.service.IStudentService;
import com.example.test.demo.utils.ReadExcel;
import com.example.test.demo.utils.excel.ExcelUtils;
import com.example.test.demo.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    @PostMapping("/insert")
    public JsonResult<Void> addNew(@Validated StudentAddNewDTO studentAddNewDTO) {
        log.debug("开始处理【添加学生信息】的请求，参数：{}", studentAddNewDTO);
        studentService.addNew(studentAddNewDTO);
        return JsonResult.ok();
    }

    @ApiOperation("学生信息excel批量添加")
    @ApiOperationSupport(order = 150)
    @PostMapping("/import")
    public JsonResult<Void> importStudentList(@RequestPart("file") MultipartFile file) {
        List<StudentExcelDTO> studentList = ReadExcel.readExcel(file, StudentExcelDTO.class);

        System.out.println(studentList);
        studentService.addBatch(studentList);
        return JsonResult.ok();
    }

    //
    @ApiOperation("批量删除学生信息")
    @ApiOperationSupport(order = 200)
  /*  @ApiImplicitParam(name = "ids", value = "学生ids", required = true, dataType = "long")*/
    @PostMapping("/delBatch")
    public JsonResult<Void> deleteByIds(@Validated IdsDTO ids) {
        log.debug("开始处理【删除学生信息】的请求，参数：{}", ids);
        List<Long> idd = ids.getIds();
        studentService.deleteByIds(idd);
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
    @ApiOperation("根据name查询学生信息详情")
    @ApiOperationSupport(order = 400)
    @ApiImplicitParam(name = "name", value = "学生姓名", required = true, dataType = "string")
    @GetMapping("/{name}/selByName")
    public JsonResult<List<StudentVO>> getStandardById(@PathVariable String name) {
        log.debug("开始处理【根据name查询学生信息详情】的请求：name={}", name);
        List<StudentVO> students = studentService.getStandardByName(name);
        return JsonResult.ok(students);
    }

    //
    @ApiOperation("分页查询学生信息列表")
    @ApiOperationSupport(order = 420)
    @PostMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码", name = "page", example = "1"),
            @ApiImplicitParam(value = "每页条数", name = "pageSize", example = "20")
    })
    public JsonResult<JsonPage<StudentListItemVO>> getAllPage(Integer page, Integer pageSize) {
        //靠分页信息查学生数据
        JsonPage<StudentListItemVO> jsonPage = studentService.getAllStudentByPage(page, pageSize);
        //成功,以json格式返回信息
        return JsonResult.ok(jsonPage);

    }

    @ApiOperation("查询所有学生信息列表")
    @ApiOperationSupport(order = 440)
    @PostMapping("/all")
    public JsonResult<List<StudentExcelVO>> getAllExcel() {
        List<StudentExcelVO> list = studentService.getAllStudentsExcel();
        return JsonResult.ok(list);

    }
    @ApiOperation("所有学生信息列表导出到excel")
    @ApiOperationSupport(order = 460)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        //文件名添加时间
       // String fileName = "学生信息表"+ System.currentTimeMillis();
        List<StudentExcelVO> list = studentService.getAllStudentsExcel();
        //直接响应
        ExcelUtils.export(response, "学生信息表", list,StudentExcelVO.class);
    }


}
