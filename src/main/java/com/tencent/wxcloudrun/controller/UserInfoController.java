package com.tencent.wxcloudrun.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.UserInfo;
import com.tencent.wxcloudrun.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * counter控制器
 */
@RestController

public class UserInfoController {

    final UserInfoService userInfoService;
    final Logger logger;

    public UserInfoController(@Autowired UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
        this.logger = LoggerFactory.getLogger(UserInfoController.class);
    }


    /**
     * 登录
     */
    @GetMapping(value = "/api/userinfo/login")
    ApiResponse login(@RequestParam(value = "userWxHm", required = true) String userWxHm,
                      @RequestParam(value = "pwd", required = true) String pwd) {
        Optional<UserInfo> userInfo = userInfoService.getUserInfo(userWxHm);
        if (userInfo.isPresent()) {
            String dbpwd = userInfo.get().getPwd();
            if (pwd.equals(dbpwd)) {
                return ApiResponse.ok(userInfo);
            }
            return ApiResponse.error("用户名或密码错误");
        } else {
            return ApiResponse.error("用户不存在");
        }
    }

    /**
     * 注册
     */
    @PostMapping(value = "/api/userinfo/register")
    ApiResponse register(@RequestBody UserInfo userInfo) {
        Optional<UserInfo> userdb = userInfoService.getUserInfo(userInfo.getUserWxHm());
        if (userdb.isPresent()) {
            userInfo.setUserId(userdb.get().getUserId());
            return ApiResponse.ok(userInfoService.updateUserinfo(userInfo));
        }
        return ApiResponse.error("请联系管理员先授权");
    }

    /**
     * 根据用户微信账号获取用户信息
     */
    @GetMapping(value = "/api/userinfo/{userWxHm}")
    ApiResponse get(@PathVariable("userWxHm") String userWxHm) {
        logger.info("/api/count get request");
        Optional<UserInfo> userInfo = userInfoService.getUserInfo(userWxHm);
        return ApiResponse.ok(userInfo);
    }

    /**
     * 添加用户信息
     */
    @PostMapping("/api/userinfo/add")
    public ApiResponse add(@RequestBody UserInfo userInfo) {
        //userInfoService.upsertUserInfo(userInfo);
        Optional<UserInfo> userInf = userInfoService.getUserInfo(userInfo.getUserWxHm());
        if (userInf.isPresent()) {
            return ApiResponse.error("此用户已存在");
        }
        return ApiResponse.ok(userInfoService.upsertUserInfo(userInfo));
    }

    /**
     * 更新用户信息
     */
    @PutMapping("/api/userinfo/update")
    public ApiResponse update(@RequestBody UserInfo userInfo) {
        Integer ss = userInfoService.updateUserinfo(userInfo);
        return ApiResponse.ok(ss);
    }

    /***
     *
     */
    @GetMapping(value = "/api/userinfo/list")
    ApiResponse getUserInfoList(@RequestParam(value = "userDeptName", required = false) String userDeptName,
                                @RequestParam(value = "userStatus", required = false) String userStatus,
                                @RequestParam(value = "userName", required = false) String userName,
                                @RequestParam(value = "userWxHm", required = false) String userWxHm,
                                @RequestParam(value = "userPhone", required = false) String userPhone,
                                @RequestParam(value = "userPermission", required = false) String userPermission) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserDeptName(userDeptName);
        userInfo.setUserStatus(userStatus);
        userInfo.setUserName(userName);
        userInfo.setUserWxHm(userWxHm);
        userInfo.setUserPhone(userPhone);
        userInfo.setUserPermission(userPermission);
        List<UserInfo> userInfos = userInfoService.getUserinfoList(userInfo);
        return ApiResponse.ok(userInfos);
    }

    @PostMapping(value = "/api/userinfo/listpage")
    ApiResponse getUserPage(@RequestParam(value = "userDeptName", required = false) String userDeptName,
                            @RequestParam(value = "userStatus", required = false) String userStatus,
                            @RequestParam(value = "userName", required = false) String userName,
                            @RequestParam(value = "userWxHm", required = false) String userWxHm,
                            @RequestParam(value = "userPhone", required = false) String userPhone,
                            @RequestParam(value = "userPermission", required = false) String userPermission,
                            @RequestParam(value = "pageNum", required = true) int pageNum,
                            @RequestParam(value = "pageSize", required = true) int pageSize
    ) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserDeptName(userDeptName);
        userInfo.setUserStatus(userStatus);
        userInfo.setUserName(userName);
        userInfo.setUserWxHm(userWxHm);
        userInfo.setUserPhone(userPhone);
        userInfo.setUserPermission(userPermission);

        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> userInfos = userInfoService.getUserinfoList(userInfo);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfos);
        PageHelper.clearPage();
        return ApiResponse.ok(pageInfo);
    }

    @DeleteMapping("/api/userinfo/delet/{userId}")
    public ApiResponse delet(@PathVariable("userId") String userId) {
        return ApiResponse.ok(userInfoService.clearUserInfo(userId));
    }

}
