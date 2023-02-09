package com.example.test.demo.mapper;

import com.example.test.demo.pojo.entity.Student;
import org.springframework.stereotype.Repository;

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
     * 根据id删除学生数据
     *
     * @param id 学生id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 更新学生数据
     * @param student 封装了相册的id和需要更新的新数据的对象
     * @return 受影响的行数
     */
    int updateById(Student student);



}
