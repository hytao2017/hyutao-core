package com.hyutao.core.common.exception;

/**
 * 业务检查异常.
 */
public class CheckException extends RuntimeException {


  private static final long serialVersionUID = 7077546185103492641L;


  public CheckException() {
    super();
  }

  public CheckException(String message) {
    super(message);
  }

}
