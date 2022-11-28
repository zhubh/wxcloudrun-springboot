package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BookMark implements Serializable {

    private String userId;

    private String userName;

    private String userWxHm;

    private String bookId;

    private String bookMarkName;

    private LocalDateTime bookmarkDate;

    private String bookMarkContent;

}
