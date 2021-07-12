package com.hyutao.core.common.log;

/**
 * 操作类型
 */

public enum ActionType {


  /**
   * 登录操作
   */
  LOGIN("登录"),

  /**
   * 执行添加插入操作
   */
  ADD("新增"),

  /**
   * 执行编辑更新操作
   */
  UPDATE("编辑"),

  /**
   * 执行删除操作
   */
  DELETE("删除"),

  /**
   * 执行导入操作
   */
  IMPORT("导入"),

  /**
   * 执行导出操作
   */
  EXPORT("导出"),

  /**
   * 执行查询操作
   */
  QUERY("查询"),

  /**
   * 执行查询操作
   */
  EXECUTE("执行"),

  /**
   * 执行下载操作
   */
  DOWNLOAD("下载");

  /**
   * 操作名称
   */
  private final String typeName;

  ActionType(String typeName) {
    this.typeName = typeName;
  }

  public String getTypeName() {
    return this.typeName;
  }
}
