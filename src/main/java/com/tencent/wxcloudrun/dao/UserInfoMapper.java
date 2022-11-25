package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper {

    UserInfo getUserInfo(@Param("userWxHm") String userWxHm);

    void upsertUserInfo(UserInfo userInfo);

    void clearUserInfo(@Param("userWxHm") String userWxHm);
}
