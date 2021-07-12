package com.hyutao.core.common.log;

import java.lang.annotation.*;

/**
 * 系统操作日志
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActionLog {

  /**
   * 操作模块
   *
   * @return
   */
  ActionModule module();

  /**
   * 操作类型
   *
   * @return
   */
  ActionType type();

  /**
   * 日志内容
   *
   * @return
   */
  String content() default "";

}
