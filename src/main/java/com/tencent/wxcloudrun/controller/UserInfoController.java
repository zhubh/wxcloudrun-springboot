package com.tencent.wxcloudrun.controller;

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
     * 获取当前计数
     *
     * @return API response json
     */
    @GetMapping(value = "/api/userinfo/{userWxHm}")
    ApiResponse get(@PathVariable("userWxHm") String userWxHm) {
        logger.info("/api/count get request");
        Optional<UserInfo> userInfo = userInfoService.getUserInfo(userWxHm);
        return ApiResponse.ok(userInfo);
    }


    @PostMapping("/api/userinfo/add")
    public ApiResponse add(@RequestBody UserInfo userInfo) {
        //userInfoService.upsertUserInfo(userInfo);
        Optional<UserInfo> userInf = userInfoService.getUserInfo(userInfo.getUserWxHm());
        if (userInf.isPresent()) {
            return ApiResponse.error("此用户已存在");
        }
        return ApiResponse.ok(userInfoService.upsertUserInfo(userInfo));
    }

    @PutMapping("/api/userinfo/update")
    public ApiResponse update(@RequestBody UserInfo userInfo) {
        Integer ss = userInfoService.updateUserinfo(userInfo);
        return ApiResponse.ok(ss);
    }

    @GetMapping(value = "/api/userinfo/list")
    ApiResponse getBookMarkByWxHM(@RequestParam(value = "userDeptName", required = false) String userDeptName,
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

    @DeleteMapping("/api/userinfo/delet/{userId}")
    public ApiResponse delet(@PathVariable("userId") String userId) {
        return ApiResponse.ok(userInfoService.clearUserInfo(userId));
    }

}
