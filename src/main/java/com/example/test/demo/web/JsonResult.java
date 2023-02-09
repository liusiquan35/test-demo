package com.example.test.demo.web;

import com.example.test.demo.ex.ServiceException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult<T> implements Serializable {

    /**
     * 业务状态码
     */
    @ApiModelProperty("业务状态码")
    private Integer state;
    /**
     * 操作失败时的提示文本
     */
    @ApiModelProperty("操作失败时的提示文本")
    private String message;
    /**
     * 操作成功时的响应数据
     */
    @ApiModelProperty("操作成功时的响应数据")
    private T data;

    public JsonResult() {

    }

    public static JsonResult<Void> ok() {
        // JsonResult jsonResult = new JsonResult();
        // jsonResult.state = ServiceCode.OK.getValue();
        // jsonResult.message = null;
        // jsonResult.data = null;
        // return jsonResult;
        return ok(null);
    }

    public static <T> JsonResult<T> ok(T data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.state = ServiceCode.OK.getValue();
        jsonResult.data = data;
        return jsonResult;

    }

    public static JsonResult<Void> fail(ServiceException e) {
        // JsonResult jsonResult = new JsonResult();
        // jsonResult.state = e.getServiceCode().getValue();
        // jsonResult.message = e.getMessage();
        // return jsonResult;
        return fail(e.getServiceCode(), e.getMessage());
    }

    public static JsonResult<Void> fail(ServiceCode serviceCode, String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.state = serviceCode.getValue();
        jsonResult.message = message;
        return jsonResult;
    }

}