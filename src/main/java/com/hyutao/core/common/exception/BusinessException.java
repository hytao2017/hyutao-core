package com.hyutao.core.common.exception;

/**
 * 业务异常.
 */
public class BusinessException extends RuntimeException {

  private static final long serialVersionUID = 7077546185103492641L;

  public BusinessException() {
    super();
  }

  public BusinessException(String message) {
    super(message);
  }

}
