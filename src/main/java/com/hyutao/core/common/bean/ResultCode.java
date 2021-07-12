package com.hyutao.core.common.bean;

/**
 * 状态码
 *
 * @author HeLi
 */
public class ResultCode {


  /**
   * 正常
   */
  public static final int OK = 200;

  /**
   * 业务通用异常
   */
  public static final int FAIL = 500;

  /**
   * 系统错误状态码
   */
  public static final int SYSTEM_ERROR = 4000;

  /**
   * token无效或过期
   */
  public static final int TOKEN_EXPIRED = 4001;

  /**
   * 未授权
   */
  public static final int UNAUTHORIZED = 4003;

  /**
   * 参数校验、类型、转换 错误
   */
  public static final int PARAM_ERROR = 4004;

  /**
   * 上传附件操作系统预设大小
   */
  public static final int MAX_UPLOAD_SIZE = 4005;

  /**
   * 接口访问地址不存在
   */
  public static final int NOT_FOUND_PATH = 4006;

  private ResultCode() {
    throw new IllegalStateException("Utility class");
  }

}
