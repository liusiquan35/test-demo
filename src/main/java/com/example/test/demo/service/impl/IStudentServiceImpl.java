package com.example.test.demo.service.impl;

import com.example.test.demo.mapper.StudentMapper;
import com.example.test.demo.pojo.dto.StudentAddNewDTO;
import com.example.test.demo.pojo.entity.Student;
import com.example.test.demo.pojo.vo.StudentVO;
import com.example.test.demo.service.IStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**处理学生业务的实现类
 */
@Slf4j
@Service
public class IStudentServiceImpl implements IStudentService {


    public IStudentServiceImpl() {
        System.out.println("学生表实现类启动");
    }
    @Autowired
    StudentMapper mapper;


    @Override
    public void addNew(StudentAddNewDTO studentDTO) {
        //1.获取身份证,判断是否正确(16位加起来是否一致)
        String IdentityType =  studentDTO.getIdentityType();
        if (!"身份证".equals(IdentityType)){  return;}
        String Identity =  studentDTO.getIdentity();
        //判断是否十八位
        if (Identity.length() !=18){ return; }
        //判断是否重复
        //判断生日是否一致
        //处理最后一位为 x 的情况，小写转大写
        Identity = Identity.toUpperCase();
        //判断最后一位

        //2.获取并判断手机号
        String phone = studentDTO.getPhone();
        //手机号是否为11位
        if (phone.length()!=11){ return; }
        //手机号是否重复

        //3.录入信息
        Student student = new Student();
        // 将参数DTO中的数据复制到Student对象中
        BeanUtils.copyProperties(studentDTO,student);
        mapper.insert(student);
    }

    @Override
    public void delete(Long id) {
//        log.debug("开始处理【删除相册】的业务，参数：{}", id);
//        // 调用Mapper对象的getDetailsById()方法执行查询
//        AlbumStandardVO queryResult = mapper.getStandardById(id);
//        // 判断查询结果是否为null
//        if (queryResult == null) {
//            // 是：无此id对应的数据，抛出异常
//            String message = "删除相册失败，尝试访问的数据不存在！";
//            log.warn(message);
//            // throw new ServiceException(message);
//        }
//
//        // 调用Mapper对象的deleteById()方法执行删除
//        log.debug("即将删除相册数据……");
//        mapper.deleteById(id);
//        log.debug("删除相册，完成！");

    }

//    @Override
//    public List<StudentVO> list() {
//       log.debug("查询相册业务启动");
//          return mapper.list();
//    }


}
