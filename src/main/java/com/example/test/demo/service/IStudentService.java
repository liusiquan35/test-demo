package com.example.test.demo.service;

import com.example.test.demo.pagehelper.JsonPage;
import com.example.test.demo.pojo.dto.StudentAddNewDTO;
import com.example.test.demo.pojo.dto.StudentExcelDTO;
import com.example.test.demo.pojo.dto.StudentUpdateDTO;
import com.example.test.demo.pojo.vo.StudentExcelVO;
import com.example.test.demo.pojo.vo.StudentListItemVO;
import com.example.test.demo.pojo.vo.StudentVO;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.List;
/**
 * 处理学生数据的业务接口
 *
 * @author java@lsq
 * @version 0.0.1
 */
@Transactional
public interface IStudentService {

    /**
     * 添加学生信息
     *
     * @param studentAddNewDTO 学生数据
     */
    void addNew(StudentAddNewDTO studentAddNewDTO);

    /**
     * 批量添加学生信息
     *
     * @param studentAddNewDTOList 学生数据集合
     */
    void addBatch(List<StudentExcelDTO> studentAddNewDTOList);



    /**
     * 批量删除学生信息
     *
     * @param ids 尝试删除的学生id
     */
    void deleteByIds(List<Long> ids);

    /**
     * 修改学生信息
     *
     * @param
     */
    void updateById(Long id, StudentUpdateDTO studentUpdateDTO);

    /**
     * 根据name获取学生的标准信息
     *
     * @param name 学生name
     * @return 返回匹配的学生的标准信息，如果没有匹配的数据，将返回null
     */
    List<StudentVO> getStandardByName(String name);

    /**
     * 分页查询学生列表
     *
     * @return 分页后学生列表
     */
    JsonPage<StudentListItemVO> getAllStudentByPage(Integer page, Integer pageSize);

    /**
     * 查询所有学生列表
     *
     * @return 所有学生列表
     */
    List<StudentExcelVO> getAllStudentsExcel();
}


