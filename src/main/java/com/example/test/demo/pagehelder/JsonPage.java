package com.example.test.demo.pagehelder;

import com.example.test.demo.pojo.vo.StudentListItemVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class JsonPage<T> implements Serializable{

    // 当前类应该和Page\PageInfo一样,既能包含分页查询结果,又包含分页信息
    // 分页信息方面,我们可以根据项目需求来声明
    // 当前只声明最基本的四个分页信息即可,今后可以随需求变化增加
    @ApiModelProperty(value = "总页数",name="totalPages")
    private Integer totalPages;
    @ApiModelProperty(value = "总条数",name="totalCount")
    private Long totalCount;
    @ApiModelProperty(value = "页码",name="page")
    private Integer page;
    @ApiModelProperty(value = "每页条数",name="pageSize")
    private Integer pageSize;

    // JsonPage还要包含分页查询结果
    @ApiModelProperty(value = "分页数据",name="list")
    private List<T> list;

    // 下面要编写一个能够将PageInfo类型对象转换为JsonPage类型对象的方法
    // 如果需要将其他类型对象转换为JsonPage(例如SpringData的Page类型),另外编写方法即可
    public static <T> JsonPage<T> restPage(PageInfo<T> pageInfo){
        // 所谓转换的意思就是将pageInfo对象中的信息,赋值给JsonPage类型
        JsonPage<T> result=new JsonPage<>();
        // 因为PageInfo和JsonPage同名属性较少,所以手动赋值
        result.setTotalCount(pageInfo.getTotal());
        result.setTotalPages(pageInfo.getPages());
        result.setPage(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        // 别忘了最后的分页数据
        result.setList(pageInfo.getList());
        // 最后返回JsonPage类型对象result
        return  result;


    }


}
