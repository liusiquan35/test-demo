package com.example.test.demo.pojo.dto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 *接收id集合
 *
 */
@Data
public class IdsDTO {

    @ApiModelProperty(value = "多个id", example = "1,2,3,4", required = true)
    @NotNull(message = "添加学生姓名失败，必须提交学生名称！")
    private List<Long> ids;


}
