package com.me.sso.service;


import com.me.top.service.MeService;
import com.me.top.service.RedisService;
import com.me.top.util.JacksonUtils;
import com.me.sso.entity.SsoUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;


/**
 * service层
 */
@Service
public class SsoUserService extends MeService<SsoUser> {

    @Resource(name = "redisJedisCluster")
    private RedisService redisService;

    Logger logger = LoggerFactory.getLogger(SsoUserService.class);

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
     * 登录.
     * //将用户信息写入到redis中
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
                try {
                    String token = "sso_token_" + userName + "_" + System.currentTimeMillis();
                    token = DigestUtils.sha256Hex(token);
                    ssoUser.setPwd(null);
                    String _token = redisService.query(userName);
                    if (!StringUtils.isEmpty(_token)) {//redis中当前用户只能存储一个
                        redisService.del(_token);
                        redisService.del(userName);
                    }
                    redisService.save(token, JacksonUtils.objToString(ssoUser), 60 * 60 * 24);
                    redisService.save(userName, token, 60 * 60 * 24);
                    return token;
                } catch (Exception e) {
                    logger.info(e.getMessage());
                }
            }
        }
        return null;
    }

    public String queryByToken(String token) {
        return redisService.query(token);
    }
}
