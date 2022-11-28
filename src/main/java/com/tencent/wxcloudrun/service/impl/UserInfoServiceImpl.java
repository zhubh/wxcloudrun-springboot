package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.UserInfoMapper;
import com.tencent.wxcloudrun.model.UserInfo;
import com.tencent.wxcloudrun.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    final UserInfoMapper userInfoMapper;

    public UserInfoServiceImpl(@Autowired UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public Optional<UserInfo> getUserInfo(String userWxHm) {
        return Optional.ofNullable(userInfoMapper.getUserInfo(userWxHm));
    }


    @Override
    public Integer upsertUserInfo(UserInfo userInfo) {
        userInfo.setUserId(UUID.randomUUID().toString());
        return userInfoMapper.upsertUserInfo(userInfo);
    }

    @Override
    public Integer clearUserInfo(String userId) {
        return userInfoMapper.clearUserInfo(userId);
    }

    @Override
    public List<UserInfo> getUserinfoList(UserInfo userInfo) {
        return userInfoMapper.getUserinfoList(userInfo);
    }

    @Override
    public Integer updateUserinfo(UserInfo userInfo) {
        return userInfoMapper.updateUserInfo(userInfo);
    }

    }
