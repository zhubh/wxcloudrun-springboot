package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.UserInfoMapper;
import com.tencent.wxcloudrun.model.UserInfo;
import com.tencent.wxcloudrun.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    final UserInfoMapper userInfoMapper;

    public UserInfoServiceImpl(@Autowired UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public Optional<UserInfo> getUserInfo(String userWxHm) {
        return  Optional.ofNullable(userInfoMapper.getUserInfo(userWxHm));
    }


    @Override
    public void upsertUserInfo(UserInfo userInfo) {
      userInfoMapper.upsertUserInfo(userInfo);
    }

    @Override
    public void clearUserInfo(String userWxHm) {
      userInfoMapper.clearUserInfo(userWxHm);
    }
}
