package com.hyutao.core.modular.pojo.form;

import com.hyutao.core.common.validation.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookForm {

    @NotNull(message = "书籍id不能为空", groups = {UpdateGroup.class})
    private Long id;

    @NotNull(message = "书籍编号不可为空")
    private String number;

    @NotNull(message = "书籍名称不可为空")
    private String name;

}
