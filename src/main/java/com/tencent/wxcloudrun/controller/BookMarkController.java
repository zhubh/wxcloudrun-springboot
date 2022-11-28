package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.BookMark;
import com.tencent.wxcloudrun.service.BookmarkService;
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

public class BookMarkController {

    final BookmarkService bookmarkService;
    final Logger logger;

    public BookMarkController(@Autowired BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
        this.logger = LoggerFactory.getLogger(BookMarkController.class);
    }


    @GetMapping(value = "/api/bookmark/{bookId}")
    ApiResponse get(@PathVariable("bookId") String bookId) {
        logger.info("/api/count get request");
        Optional<BookMark> bookMark = bookmarkService.getBookMark(bookId);
        return ApiResponse.ok(bookMark);
    }


    @GetMapping(value = "/api/getBookMarkByWxHM/{userWxHm}")
    ApiResponse getBookMarkByWxHM(@PathVariable("userWxHm") String userWxHm) {
        logger.info("/api/count get request");
        List<BookMark> bookMarks = bookmarkService.getBookMarkByWxHM(userWxHm);
        return ApiResponse.ok(bookMarks);
    }

    @PostMapping("/api/bookmark/add")
    public ApiResponse add(@RequestBody BookMark bookMark) {
        //userInfoService.upsertUserInfo(userInfo);
        return ApiResponse.ok(bookmarkService.upsertBookMark(bookMark));
    }

    @DeleteMapping("/api/bookmark/delet/{bookId}")
    public ApiResponse delet(@PathVariable("bookId") String bookId) {
        return ApiResponse.ok(bookmarkService.clearBookMark(bookId));
    }


}
