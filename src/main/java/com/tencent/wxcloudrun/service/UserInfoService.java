package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.UserInfo;

import java.util.Optional;

public interface UserInfoService {

    Optional<UserInfo> getUserInfo(String userWxHm);

    Integer upsertUserInfo(UserInfo userInfo);

    Integer clearUserInfo(String userWxHm);
}
