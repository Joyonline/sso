package com.me.sso.mapper;

import com.me.sso.entity.SsoUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends MeMapper<SsoUser> {
}
