package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.BookMark;
import com.tencent.wxcloudrun.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    UserInfo getUserInfo(@Param("userWxHm") String userWxHm);

    List<UserInfo> getUserinfoList(UserInfo userInfo);

    Integer updateUserInfo(UserInfo userInfo);

    Integer upsertUserInfo(UserInfo userInfo);

    Integer clearUserInfo(@Param("userId") String userId);
}
