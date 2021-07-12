package com.hyutao.core.common.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * 返回结果 .
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Slf4j
public class ResultBean {

  /**
   * 状态码 .
   */
  private Integer code;

  /**
   * data .
   */
  private Object data;

  /**
   * 提示信息.
   */
  private String msg;

  /**
   * 时间戳
   */
  private Long timestamp;

  /**
   * 构造函数
   *
   * @param code 状态码
   * @param msg  文字提示
   */
  public ResultBean(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
    this.timestamp = System.currentTimeMillis();
  }

  /**
   * 返回成功提示.
   */
  public static ResultBean ok(String msg) {
    return new ResultBean(ResultCode.OK, msg);
  }

  /**
   * 成功 .
   */
  public static ResultBean ok(String msg, Object data) {
    return new ResultBean(ResultCode.OK, msg).setData(data);
  }

  /**
   * 成功 .
   */
  public static ResultBean ok(Object data) {
    return new ResultBean(ResultCode.OK, "").setData(data);
  }

  /**
   * 返回失败提示.
   */
  public static ResultBean fail(int code, String msg) {
    return new ResultBean(code, msg);
  }

  /**
   * 返回失败提示.
   */
  public static ResultBean fail(String msg) {
    return new ResultBean(ResultCode.FAIL, msg);
  }


  /**
   * 返回失败提示.
   */
  public static ResultBean fail(int code, String msg, Object data) {
    return new ResultBean(code, msg).setData(data);
  }


}


