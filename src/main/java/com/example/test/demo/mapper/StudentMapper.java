package com.example.test.demo.mapper;

import com.example.test.demo.pojo.entity.Student;
import com.example.test.demo.pojo.entity.StudentIds;
import com.example.test.demo.pojo.vo.StudentListItemVO;
import com.example.test.demo.pojo.vo.StudentVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理学生信息的Mapper接口
 *
 * @author java@lsq
 * @version 0.0.1
 */
@Repository
public interface StudentMapper {
    /**
     * 插入学生全部数据
     *
     * @param student 学生数据
     * @return 受影响的行数
     */
    int insert(Student student);


    /**
     * 批量插入学生数据
     *
     * @param studentList 若干个学生数据的集合
     * @return 受影响的行数
     */
    int insertBatch(List<Student> studentList);




    /**
     * 根据id数组批量删除学生数据
     *
     * @param ids 学生id
     * @return 受影响的行数
     */
    int deleteByIds(List<Long> ids);

    /**
     * 更新学生数据
     * @param student 封装了相册的id和需要更新的新数据的对象
     * @return 受影响的行数
     */
    int updateById(Student student);


    /**
     * id查单个
     * @param id 学生id
     * @return 匹配的学生的标准信息，如果没有匹配的数据，则返回null
     */
    StudentVO getStandardById(Long id);

    /**
     * name查单个
     * @param name 学生姓名
     * @return 匹配的学生的标准信息，如果没有匹配的数据，则返回null
     */
    List<StudentVO> getStandardByName(String name);

    /**
     * 查询学生列表
     *
     * @return 学生列表
     */
    List<StudentListItemVO> list();



}
