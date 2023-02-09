package com.example.test.demo.service;

import com.example.test.demo.pagehelder.JsonPage;
import com.example.test.demo.pojo.dto.StudentAddNewDTO;
import com.example.test.demo.pojo.dto.StudentUpdateDTO;
import com.example.test.demo.pojo.vo.StudentListItemVO;
import com.example.test.demo.pojo.vo.StudentVO;
import org.springframework.transaction.annotation.Transactional;

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
    void addBatch(List<StudentAddNewDTO> studentAddNewDTOList);



    /**
     * 删除学生信息
     *
     * @param id 尝试删除的学生id
     */
    void delete(Long id);

    /**
     * 修改学生信息
     *
     * @param
     */
    void updateById(Long id, StudentUpdateDTO studentUpdateDTO);

    /**
     * 根据id获取学生的标准信息
     *
     * @param id 学生id
     * @return 返回匹配的相册的标准信息，如果没有匹配的数据，将返回null
     */
    StudentVO getStandardById(Long id);

    /**
     * 查询学生列表
     *
     * @return 学生列表
     */
    JsonPage<StudentListItemVO> getAllStudentByPage(Integer page, Integer pageSize);
}
