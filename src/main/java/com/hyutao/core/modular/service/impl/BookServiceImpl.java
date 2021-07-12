package com.hyutao.core.modular.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyutao.core.modular.entity.Book;
import com.hyutao.core.modular.mapper.BookMapper;
import com.hyutao.core.modular.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookServiceImpl extends
        ServiceImpl<BookMapper, Book> implements BookService {


    @Resource
    private BookMapper bookMapper;





}



