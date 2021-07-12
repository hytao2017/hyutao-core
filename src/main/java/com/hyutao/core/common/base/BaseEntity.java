package com.hyutao.core.common.base;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * The class Base entity.
 *
 *  * @Data
 *  * @EqualsAndHashCode(callSuper = true)
 *  * @Accessors(chain = true)
 *  实体子类在继承时需要使用以上注解解
 *
 */
@Data
public class BaseEntity implements Serializable {

  /**
   * 删除标志 已删除
   */
  public static final Integer DEL_FLAG_YES = 1;

  /**
   * 删除标志 未删除
   */
  public static final Integer DEL_FLAG_NO = 0;

  /**
   * 主键id
   */
  @TableId(type = IdType.AUTO)
  private Long id;
  private static final long serialVersionUID = 2393269568666085258L;
  /**
   * 创建时间.
   */
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  protected Date createTime;
  /**
   * 创建者.
   */
  @TableField(value = "create_user", fill = FieldFill.INSERT)
  protected Long createUser;
  /**
   * 更新时间.
   */
  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  protected Date updateTime;
  /**
   * 更新者.
   */
  @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
  protected Long updateUser;

  /**
   * 逻辑删除
   */
  @TableLogic
  private Integer delFlag = DEL_FLAG_NO;

}
