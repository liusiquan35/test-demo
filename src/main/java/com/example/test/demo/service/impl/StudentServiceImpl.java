package com.example.test.demo.service.impl;

import com.example.test.demo.ex.ServiceException;
import com.example.test.demo.mapper.StudentMapper;
import com.example.test.demo.pagehelper.JsonPage;
import com.example.test.demo.pojo.dto.StudentAddNewDTO;
import com.example.test.demo.pojo.dto.StudentExcelDTO;
import com.example.test.demo.pojo.dto.StudentUpdateDTO;
import com.example.test.demo.pojo.entity.Student;

import com.example.test.demo.pojo.vo.StudentExcelVO;
import com.example.test.demo.pojo.vo.StudentListItemVO;
import com.example.test.demo.pojo.vo.StudentVO;
import com.example.test.demo.service.IStudentService;
import com.example.test.demo.web.ServiceCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理学生业务的实现类
 */
@Slf4j
@Service
public class StudentServiceImpl implements IStudentService {
//    static {
//       String filePath =  "E:\\fff";
//    }

    @Autowired
    StudentMapper studentMapper;

    @Override
    public void addNew(StudentAddNewDTO studentDTO) {
//        //判断的                        工具类
//        Student student = judgmentUtils(studentDTO);
//        //判断对象是否为空
//        if (student==null){
//            return;
//        }
        //不为空
        Student student = new Student();

        BeanUtils.copyProperties(studentDTO, student);
        System.out.println(student);
        studentMapper.insert(student);
    }

    @Override
    public void addBatch(List<StudentExcelDTO> studentAddNewDTOList) {
        List<Student> studentList = new ArrayList<>();
        for (StudentExcelDTO studentDTO : studentAddNewDTOList) {
            Student s = new Student();
            //添加判断条件
            BeanUtils.copyProperties(studentDTO, s);
            studentList.add(s);
        }
        //排除集合是否为空
//        if (studentList==null){
//            return;
//        }
        //不为空
        studentMapper.insertBatch(studentList);
    }

    @Override
    public void deleteByIds(List<Long> ids) {

        log.debug("开始处理【批量删除学生信息】的业务，参数：{}", ids);
        // 调用Mapper对象的getDetailsByIds()方法执行查询
        for (Long id : ids) {
            //用id搜信息
            StudentVO queryResult = studentMapper.getStandardById(id);
            // 判断查询结果是否为null
            if (queryResult == null) {
                // 是：无此id对应的数据，抛出异常
                String message = id + "号删除学生信息失败，尝试访问的数据不存在！";
                log.warn(message);
                ids.remove(id);
            }
        }


        // 调用Mapper对象的deleteById()方法执行删除
        log.debug("即将删除学生数据……剩下id为:" + ids);
        studentMapper.deleteByIds(ids);
        log.debug("删除学生数据，完成！");
    }


    @Override
    public void updateById(Long id, StudentUpdateDTO studentUpdateDTO) {
        log.debug("开始处理【修改学生信息】的业务，参数：{}", studentUpdateDTO.getId());
        //如果空就报错
        StudentVO queryResult = studentMapper.getStandardById(studentUpdateDTO.getId());
        // 判断查询结果是否为null
        if (queryResult == null) {
            // 是：无此id对应的数据，抛出异常
            String message = "修改学生信息失败，尝试访问的数据不存在！";
            log.warn(message);
            // throw new ServiceException(message);
        }
        //转换对象
        Student student = new Student();
        BeanUtils.copyProperties(studentUpdateDTO, student);
        student.setId(id);

        // 修改数据
        log.debug("即将修改数据：{}", student);
        int rows = studentMapper.updateById(student);
        if (rows != 1) {
            String message = "修改学生详情失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }

    @Override
    public List<StudentVO> getStandardByName(String name) {
        log.debug("开始处理【根据name查询学生详情】的业务");
        List<StudentVO> stus = studentMapper.getStandardByName(name);
        //后台监控查询是否成功
        if (stus == null) {
            String message = "获取学生详情失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        return stus;
    }


    @Override
    public JsonPage<StudentListItemVO> getAllStudentByPage(Integer page, Integer pageSize) {
        System.out.println(page+"  "+pageSize);
        //分页器
        PageHelper.startPage(page, 20);
        //上面设置好分页查询条件,下面的查询在执行时,sql语句会自动被追加limit关键字
        List<StudentListItemVO> list = studentMapper.list();
        //返回结果
        return JsonPage.restPage(new PageInfo<>(list));

    }

    @Override
    public List<StudentExcelVO> getAllStudentsExcel() {
        List<StudentListItemVO> list = studentMapper.list();

        //查完全部后放入VO
        List<StudentExcelVO> listVOs = new ArrayList<>();
        for (StudentListItemVO listItemVO : list) {
            StudentExcelVO listVO = new StudentExcelVO();
            BeanUtils.copyProperties(listItemVO, listVO);
            listVOs.add(listVO);
        }
        System.out.println("ser"+listVOs);
        return listVOs;
    }


    //判断工具包
    Student judgmentUtils(StudentAddNewDTO studentDTO) {
        Student student = new Student();
        //1.获取身份证,判断是否正确(16位加起来是否一致)
        String IdentityType = studentDTO.getIdentityType();
        if (!"身份证".equals(IdentityType)) {
            return student;
        }
        String Identity = studentDTO.getIdentity();
        //判断是否十八位
        if (Identity.length() != 18) {
            return student;
        }
        //判断是否重复
        //判断生日是否一致
        //处理最后一位为 x 的情况，小写转大写
        Identity = Identity.toUpperCase();
        //判断最后一位

        //2.获取并判断手机号
        String phone = studentDTO.getPhone();
        //手机号是否为11位
        if (phone.length() != 11) {
            return student;
        }
        //手机号是否重复

        //3.录入信息
        // 将参数DTO中的数据复制到Student对象中
        BeanUtils.copyProperties(studentDTO, student);
        return student;
    }


}
