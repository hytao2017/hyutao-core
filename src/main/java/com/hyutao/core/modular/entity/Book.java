package com.hyutao.core.modular.entity;

import com.hyutao.core.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Book extends BaseEntity {

    private String bookNumber;
    private String bookName;


}