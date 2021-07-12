package com.hyutao.core.modular.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyutao.core.common.base.AbstractController;
import com.hyutao.core.common.bean.ResultBean;
import com.hyutao.core.common.log.ActionLog;
import com.hyutao.core.common.log.ActionModule;
import com.hyutao.core.common.log.ActionType;
import com.hyutao.core.common.validation.AddGroup;
import com.hyutao.core.common.validation.UpdateGroup;
import com.hyutao.core.modular.entity.Book;
import com.hyutao.core.modular.pojo.form.BookForm;
import com.hyutao.core.modular.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
public class BookController extends AbstractController {

    @Autowired
    private BookService bookService;


    /**
     * 新增数据
     */
    @ActionLog(module = ActionModule.BOOK, type = ActionType.ADD, content = "'新增一本书籍数据'")
    @PostMapping("/add")
    public ResultBean addClass(@RequestBody @Validated({AddGroup.class}) BookForm bookForm) {

        if (bookService
                .count(new LambdaQueryWrapper<Book>().eq(Book::getBookName, bookForm.getName()))
                > 0) {
            return ResultBean.fail("新增失败，书籍名称'" + bookForm.getName() + "'已存在");
        }

        Book book = new Book();
        BeanUtil.copyProperties(bookForm, book, CopyOptions.create().ignoreNullValue());
        book.setBookNumber(bookForm.getNumber()).setBookName(bookForm.getName());
        bookService.save(book);

        return ResultBean.ok("书籍添加成功");
    }


    /**
     * 更新书籍
     */
    @ActionLog(module = ActionModule.BOOK, type = ActionType.UPDATE, content = "'修改书籍数据'")
    @PostMapping("/update")
    public ResultBean updateClass(@RequestBody @Validated({UpdateGroup.class}) BookForm bookForm) {

        if (bookService
                .count(new LambdaQueryWrapper<Book>().eq(Book::getId, bookForm.getId())
                        .eq(Book::getDelFlag, 0)) == 0) {
            return ResultBean.fail("修改失败，书籍编号'" + bookForm.getId() + "'不存在");
        }

        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_number", bookForm.getNumber());
        Book item = bookService.getOne(queryWrapper);

        if (!item.getId().equals(bookForm.getId())) {
            return ResultBean.fail("修改失败，书籍编号'" + bookForm.getNumber() + "'已存在");
        }

        Book book = bookService.getById(bookForm.getId());
        BeanUtil.copyProperties(bookForm, book, CopyOptions.create().ignoreNullValue());
        bookService.updateById(book);

        return ResultBean.ok("数据修改成功");
    }

    /**
     * 删除课程
     */
    @ActionLog(module = ActionModule.BOOK, type = ActionType.DELETE, content = "'删除一条书籍数据'")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/delete")
    public ResultBean deleteClass(@NotNull(message = "数据id不可为空") Long id) {

        bookService.removeById(id);

        return ResultBean.ok("课程删除成功");
    }


}
