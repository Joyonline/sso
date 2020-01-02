package com.me.sso.common.controller;
/**
 * @Author: Zhouyu
 * @Description:
 * @Date: Created in 2019-3-27 13:59
 * @Modidied By:
 * @Version
 */

import com.me.sso.common.util.JacksonUtils;
import lombok.Data;

/**
 * MeResult
 */
@Data
public class MeResult {
    private Integer status;
    private String msg;
    private Object data;
    public static final Integer SUCCESS = 200;
    public static final Integer ERROR = 201;
    public static final String USRENAMEORPWDISWRONG = "用户或密码错误";
    public static MeResult build(Integer status, String msg, Object data) {
        return new MeResult(status, msg, data);
    }
    public static MeResult oK(Object data) {
        return new MeResult(data);
    }
    public static MeResult oK() {
        return new MeResult(null);
    }
    public MeResult() {}
    public static MeResult build(Integer status, String msg) {
        return new MeResult(status, msg, null);
    }
    public MeResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    public MeResult(Object data) {
        this.status = SUCCESS;
        this.msg = "OK";
        this.data = data;
    }
    public Boolean isOk() {
        return this.status == 200;
    }
}