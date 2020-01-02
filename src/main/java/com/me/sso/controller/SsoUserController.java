package com.me.sso.controller;


import com.me.sso.common.MeResult;
import com.me.sso.entity.SsoUser;
import com.me.sso.service.SsoUserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 1、密码使用明文存储
 * 2、注册时用户名未检验唯一
 */
@RestController
@RequestMapping("/user")
public class SsoUserController {

    @Resource
    private SsoUserService ssoUserService;

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public MeResult register(SsoUser user) {
        String username = ssoUserService.register(user);
        return MeResult.oK(username);
    }

    /**
     * 用户登录
     *
     * @return
     */
    @PostMapping("/login")
    public MeResult login(String u, String p) {
        String token = ssoUserService.login(u, p);
        if (StringUtils.isEmpty(token)) {
            return MeResult.build(MeResult.ERROR, "用户登录失败");
        } else {
            return MeResult.oK(token);
        }
    }
}
