package com.markerhub.common.lang;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 结果类
 * @author 86182
 */
@ApiModel
@Data
public class Result implements Serializable {

    /**
     *    200是正常，非200表示异常
     */
    @ApiModelProperty(value = "状态码")
    private int code;
    @ApiModelProperty(value = "反馈信息")
    private String msg;
    @ApiModelProperty(value = "返回数据")
    private Object data;

    public static Result succ(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }


    public static Result succ(Object data) {
        return succ(200, "操作成功", data);
    }

    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }


    public static Result fail(String msg) {
        return fail(400, msg, null);
    }

    public static Result fail(String msg, Object data) {
        return fail(400, msg, data);
    }



}
