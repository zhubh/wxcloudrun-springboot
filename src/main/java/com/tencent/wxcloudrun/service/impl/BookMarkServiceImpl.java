package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.BookMarkMapper;
import com.tencent.wxcloudrun.model.BookMark;
import com.tencent.wxcloudrun.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookMarkServiceImpl implements BookmarkService {

    final BookMarkMapper bookMarkMapper;

    public BookMarkServiceImpl(@Autowired BookMarkMapper bookMarkMapper) {
        this.bookMarkMapper = bookMarkMapper;
    }

    @Override
    public Optional<BookMark> getBookMark(String bookId) {
        return Optional.ofNullable(bookMarkMapper.getBookMark(bookId));
    }

    @Override
    public List<BookMark> getBookMarkByWxHM(String userWxHm) {
        return bookMarkMapper.getBookMarkByWxHM(userWxHm);
    }

    @Override
    public Integer upsertBookMark(BookMark bookMark) {
          bookMark.setBookId(UUID.randomUUID().toString());
        return bookMarkMapper.upsertBookMark(bookMark);
    }

    @Override
    public Integer clearBookMark(String bookId) {
        return bookMarkMapper.clearBookMark(bookId);
    }


}
