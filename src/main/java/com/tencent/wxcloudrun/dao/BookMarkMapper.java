package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.BookMark;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMarkMapper {

    BookMark getBookMark(@Param("bookId") String bookId);

    List<BookMark> getBookMarkByWxHM(@Param("userWxHm") String userWxHm);

    Integer upsertBookMark(BookMark userInfo);

    Integer clearBookMark(@Param("bookId") String bookId);


}
