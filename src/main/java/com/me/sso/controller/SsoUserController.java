package com.me.sso.controller;


import com.me.top.controller.MeController;
import com.me.top.controller.MeResult;
import com.me.sso.entity.SsoUser;
import com.me.sso.service.SsoUserService;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 1、密码使用明文存储
 * 2、注册时用户名未检验唯一
 */
@RestController
@RequestMapping("/user")
public class SsoUserController extends MeController {

    @Resource
    private SsoUserService ssoUserService;

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public MeResult register(@Valid SsoUser user, BindingResult result) {
        super.hasErrors(result);
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
        paramNullCheck(u,p);
        String token = ssoUserService.login(u, p);
        if (StringUtils.isEmpty(token)) {
            return MeResult.build(MeResult.ERROR, "用户登录失败");
        } else {
            return MeResult.oK(token);
        }
    }

    /**
     * 查询token信息
     * @param token
     * @return
     */
    @GetMapping("/query/{token}")
    public MeResult queryByToken(@PathVariable String token){
        return MeResult.oK(ssoUserService.queryByToken(token));
    }
}
