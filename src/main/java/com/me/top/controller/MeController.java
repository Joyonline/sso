package com.me.top.controller;

import com.me.top.util.MeException;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class MeController {

    /**
     * 校验数据有效性
     * @param result
     */
    public void hasErrors(BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            List<String> myErrors = new ArrayList();
            for (ObjectError objectError : errors) {
                myErrors.add(objectError.getDefaultMessage());
            }
            throw new MeException(StringUtils.join(myErrors, '|'));
        }
    }

    /**
     * 参数非空校验
     * @param params
     */
    public void paramNullCheck(Object... params) {
        for (Object param : params) {
            if (org.springframework.util.StringUtils.isEmpty(param)) {
                throw new MeException("存在参数为空");
            }
        }
    }
}
