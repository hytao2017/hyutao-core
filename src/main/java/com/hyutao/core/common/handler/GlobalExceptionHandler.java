package com.hyutao.core.common.handler;


import com.hyutao.core.common.bean.ResultBean;
import com.hyutao.core.common.bean.ResultCode;
import com.hyutao.core.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 参数校验异常
   *
   * @param e 异常
   * @return ResultBean
   */
  @ExceptionHandler(BindException.class)
  public ResultBean handlerBindException(BindException e) {
    StringBuilder stringBuffer = new StringBuilder();
    System.out.println(e.getBindingResult().getAllErrors());
    e.getBindingResult().getAllErrors()
        .forEach(err -> stringBuffer.append(err.getDefaultMessage()).append(";"));
    return result(ResultBean.fail(ResultCode.PARAM_ERROR, stringBuffer.toString()), e);
  }

  /**
   * 统一处理请求参数校验(普通传参)
   *
   * @param e ConstraintViolationException
   * @return ResultBean
   */
  @ExceptionHandler(value = ConstraintViolationException.class)
  public ResultBean handleConstraintViolationException(ConstraintViolationException e) {
    StringBuilder stringBuffer = new StringBuilder();
    e.getConstraintViolations().forEach(err -> stringBuffer.append(err.getMessage()).append(";"));
    return result(ResultBean.fail(ResultCode.PARAM_ERROR, stringBuffer.toString()), e);
  }


  /**
   * 参数校验异常
   *
   * @param e
   * @return
   */
  @ExceptionHandler(IllegalStateException.class)
  public ResultBean handlerIllegalStateException(IllegalStateException e) {
    return result(ResultBean.fail(ResultCode.PARAM_ERROR, "参数类型校验失败"), e);
  }


  /**
   * 参数校验异常json
   *
   * @param e
   * @return
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResultBean handlerMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    StringBuilder stringBuffer = new StringBuilder();
    e.getBindingResult().getAllErrors()
        .forEach(err -> stringBuffer.append(err.getDefaultMessage()).append(";"));
    return result(ResultBean.fail(ResultCode.PARAM_ERROR, stringBuffer.toString()), e);
  }

  /**
   * 参数类型转换错误
   *
   * @param e
   * @return
   */
  @ExceptionHandler(HttpMessageConversionException.class)
  public ResultBean handlerHttpMessageConversionException(
      HttpMessageConversionException e) {
    return result(ResultBean.fail(ResultCode.PARAM_ERROR, e.getLocalizedMessage()), e);
  }


  /**
   * 上传附件操作系统预设大小
   *
   * @param e
   * @return
   */
  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResultBean handlerMaxUploadSizeExceededException(
      MaxUploadSizeExceededException e) {
    return result(ResultBean.fail(ResultCode.MAX_UPLOAD_SIZE, e.getMessage()), e);
  }

  /**
   * 业务异常
   *
   * @param e
   * @return
   */
  @ExceptionHandler(BusinessException.class)
  public ResultBean handlerBusinessException(BusinessException e) {
    return result(ResultBean.fail(ResultCode.FAIL, e.getMessage()), e);
  }


  /**
   * 参数异常
   *
   * @param e
   * @return
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResultBean handlerIllegalArgumentException(IllegalArgumentException e) {
    return result(ResultBean.fail(ResultCode.FAIL, e.getMessage()), e);
  }

//  /**
//   * 接口鉴权失败，未找到token
//   *
//   * @param e
//   * @return
//   */
//  @ExceptionHandler(BadCredentialsException.class)
//  public ResultBean handlerBadCredentialsException(BadCredentialsException e) {
//    return result(ResultBean.fail(ResultCode.COMMON_ERROR, e.getMessage()), e);
//  }


  /**
   * NoHandlerFoundException异常
   *
   * @param e
   * @return
   */
  @ExceptionHandler(NoHandlerFoundException.class)
  public ResultBean handlerException(NoHandlerFoundException e) {

    return result(ResultBean
            .fail(ResultCode.NOT_FOUND_PATH, "接口不存在[" + e.getHttpMethod() + "] " + e.getRequestURL()),
        e);
  }


  /**
   * method 方式不正确
   *
   * @param e
   * @return
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResultBean handlerHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException e) {
    return result(ResultBean.fail(ResultCode.PARAM_ERROR, e.getLocalizedMessage()), e);
  }


//  /**
//   * 未授权访问
//   *
//   * @param e
//   * @return
//   */
//  @ExceptionHandler(AccessDeniedException.class)
//  public ResultBean handlerAccessDeniedException(AccessDeniedException e) {
//    throw e;
//  }

  /**
   * 业务异常
   *
   * @param e
   * @return
   */
  @ExceptionHandler(Exception.class)
  public ResultBean handlerException(Exception e) {
    return result(ResultBean.fail(ResultCode.SYSTEM_ERROR, "系统忙，请稍后再试！"), e);
  }

  private ResultBean result(ResultBean resultBean, Throwable e) {
    log.error("异常信息:{}", resultBean, e);
    return resultBean;
  }

}





