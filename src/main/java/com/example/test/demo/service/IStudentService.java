package com.example.test.demo.service;

import com.example.test.demo.pojo.dto.StudentAddNewDTO;
import com.example.test.demo.pojo.vo.StudentVO;

import java.util.List;
/**
 * 处理学生数据的业务接口
 *
 * @author java@lsq
 * @version 0.0.1
 */

public interface IStudentService {

    /**
     * 添加学生信息
     *
     * @param studentAddNewDTO 学生数据
     */
    void addNew(StudentAddNewDTO studentAddNewDTO);

    /**
     * 删除学生信息
     *
     * @param id 尝试删除的学生id
     */
    void delete(Long id);

    /**
     * 查询学生列表
     *
     * @return 学生列表
     */
    //List<StudentVO> list();
}
