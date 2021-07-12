package com.hyutao.core.common.bean;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyutao.core.common.ConstKit;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义分页对象 .
 */
public class PageParam<T> extends Page<T> {

  /**
   * 当前页数.
   */
  @NotNull(message = "页码数不能为空")
  @Min(value = 1, message = "最小分页为1")
  private long current = 1L;

  /**
   * 每页记录数.
   */
  @Max(value = 500, message = "每页记录数不能超过范围")
  private long size = 10L;

  /**
   * 排序方式
   */
  @Getter
  @Setter
  private String sortType;

  /**
   * 排序的字段
   */
  @Getter
  @Setter
  private String sortName;

  @Override
  public List<OrderItem> orders() {
    if (StrUtil.isNotBlank(this.sortName)) {
      List<OrderItem> orderItems;
      String[] sortFields = Arrays.stream(sortName.split(StringPool.COMMA))
          .map(StrUtil::toUnderlineCase).toArray(String[]::new);
      if (ConstKit.ORDER_DESC.equalsIgnoreCase(this.sortType)) {
        orderItems = OrderItem.descs(sortFields);
      } else {
        orderItems = OrderItem.ascs(sortFields);
      }
      return orderItems;
    }
    return super.orders();
  }

  @Override
  public Page<T> setCurrent(long current) {
    this.current = current;
    return this;
  }

  @Override
  public long getSize() {
    return size;
  }

  @Override
  public Page<T> setSize(long size) {
    this.size = size;
    return this;
  }

  @Override
  public long getCurrent() {
    return this.current;
  }

}