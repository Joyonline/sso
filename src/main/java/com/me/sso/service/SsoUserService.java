package com.me.sso.service;


import com.me.sso.entity.SsoUser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * service层
 */
@Service
public class SsoUserService extends MeService<SsoUser>{

    /**
     * 注册
     * @param user
     * @return
     */
    public String register(SsoUser user){
        user.setPwd(user.getPwd());//暂未加密
        super.saveSelective(user);
        return user.getUserName();
    }

    /**
     * 登录
     * @param userName
     * @param pwd
     * @return
     */
    public String login(String userName, String pwd) {
        SsoUser user = new SsoUser();
        user.setUserName(userName);//用户名唯一
        SsoUser ssoUser = super.queryByT(user);
        if (!StringUtils.isEmpty(ssoUser)) {
            String _pwd = ssoUser.getPwd();
            if (_pwd.equals(pwd)) {
                //将用户信息写入到redis中
                return "sso_token_" + userName + "_" + System.currentTimeMillis();
            }
        }
        return null;
    }
}
