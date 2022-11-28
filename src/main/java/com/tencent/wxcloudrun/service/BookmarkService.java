package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.BookMark;

import java.util.List;
import java.util.Optional;

public interface BookmarkService {

    Optional<BookMark> getBookMark(String bookId);

    List<BookMark> getBookMarkByWxHM(String userWxHm);

    Integer upsertBookMark(BookMark userInfo);

    Integer clearBookMark(String userWxHm);

}
