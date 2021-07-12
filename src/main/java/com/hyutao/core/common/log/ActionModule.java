package com.hyutao.core.common.log;

/**
 * 系统操作模块
 */
public enum ActionModule {

  /**
   * 书籍模块
   */
  BOOK("书籍模块");


  // 更具模块增加更多枚举值

  private final String moduleName;

  ActionModule(String moduleName) {
    this.moduleName = moduleName;
  }

  public String getModuleName() {
    return this.moduleName;
  }
}
