package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserInfo implements Serializable {

    private String userId;

    private String userName;

    private String userWxHm;

    private String userDeptName;

    private String userPhone;

    private LocalDateTime userDate;

    private String userStatus;

    private String userPermission;

}
