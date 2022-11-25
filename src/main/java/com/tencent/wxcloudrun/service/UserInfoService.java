package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.UserInfo;

import java.util.Optional;

public interface UserInfoService {

    Optional<UserInfo> getUserInfo(String userWxHm);

    void upsertUserInfo(UserInfo userInfo);

    void clearUserInfo(String userWxHm);
}
