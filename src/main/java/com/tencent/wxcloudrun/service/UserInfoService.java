package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.BookMark;
import com.tencent.wxcloudrun.model.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserInfoService {

    Optional<UserInfo> getUserInfo(String userWxHm);

    List<UserInfo> getUserinfoList(UserInfo  userInfo);

     Integer updateUserinfo(UserInfo userInfo);

    Integer upsertUserInfo(UserInfo userInfo);

    Integer clearUserInfo(String userId);
}
