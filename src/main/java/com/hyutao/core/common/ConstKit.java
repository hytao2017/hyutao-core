package com.hyutao.core.common;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 系统常量类.
 *
 * @author heli0
 */
public class ConstKit {

  /**
   * 排序规则： descend 降序
   */
  public static final String ORDER_DESC = "descend";
  /**
   * 排序规则： ascend 升序
   */
  public static final String ORDER_ASC = "ascend";

  /**
   * 默认编码UTF-8
   */
  public static final Charset CHARSET = StandardCharsets.UTF_8;

  public static final String TOKEN_KEY_PREFIX = "user:token:%s";

  /**
   * 验证码redis 前缀
   */
  public static final String CAPTCHA_PREFIX = "user:captcha:%s";

  public static final String HEADER_AUTHORIZATION = "Authorization";

  public static final String HEADER_BEARER = "Bearer ";

  /**
   * 多选项分隔符
   */
  public static final String DELIMITER_TAG = ",";

  /**
   * Token 有效期（s）
   */
  public static final long TOKEN_EXPIRES = 7200L;


  private ConstKit() {
    throw new IllegalStateException("Utility class");
  }
}
