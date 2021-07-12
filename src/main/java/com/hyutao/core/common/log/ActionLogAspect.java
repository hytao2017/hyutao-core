package com.hyutao.core.common.log;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * 审计日志aop 处理
 *
 * @author HeLi
 */
@Aspect
@Component
@Slf4j
public class ActionLogAspect {

//  final ObjectMapper objectMapper;

//  final SysLogService sysLogService;

  ExpressionParser parser = new SpelExpressionParser();

  LocalVariableTableParameterNameDiscoverer discoverer =
      new LocalVariableTableParameterNameDiscoverer();

//  public ActionLogAspect(ObjectMapper objectMapper, SysLogService sysLogService) {
//    this.objectMapper = objectMapper;
//    this.sysLogService = sysLogService;
//  }

//  @Pointcut(value = "@annotation(com.iflytek.iakpls.common.log.ActionLog)")
//  public void logPointCut() {
//    log.debug("logPointCut");
//  }
//
//  @Around(value = "logPointCut()")
//  public Object invoked(ProceedingJoinPoint pjp) throws Throwable {
//    // 获取方法
//    Method method = ((MethodSignature) pjp.getSignature()).getMethod();
//    ActionLog actionLog = method.getAnnotation(ActionLog.class);
//    ActionType actionType = actionLog.type();
//    ActionModule actionModule = actionLog.module();
//    Object[] arguments = pjp.getArgs();
//    String methodName =
//        pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName() + "()";
//    String spel = actionLog.content();
//    String result = parseSpel(method, arguments, spel, String.class, "");
//    SysLog sysLog = new SysLog();
//    sysLog.setOpTime(new Date());
//    String builder =
//        "【操作日志】"
//            + "操作模块："
//            + actionModule
//            + "，"
//            + "操作类型："
//            + actionType
//            + "，"
//            + "操作描述："
//            + actionLog.content()
//            + "，"
//            + "请求方法："
//            + methodName
//            + "，"
//            + "请求参数："
//            + Arrays.toString(arguments)
//            + "，"
//            + "返回结果："
//            + result
//            + "，"
//            + "请求用户域账号："
//            + SecurityUtil.getCurrentAccount()
//            + "，"
//            + "请求URI："
//            + WebUtil.getRequest().getRequestURI()
//            + "，"
//            + "请求IP："
//            + WebUtil.getIpAddr()
//            + "。";
//    sysLog.setOpRecord(builder);
//    log.info(
//        ">>>>操作日志：\n{}",
//        objectMapper
//            .setSerializationInclusion(Include.NON_NULL)
//            .writerWithDefaultPrettyPrinter()
//            .writeValueAsString(sysLog));
//    // 保存日志
//    sysLogService.insertInfo(sysLog);
//
//    return pjp.proceed();
//  }
//
//  /**
//   * 解析 spel 表达式
//   *
//   * @param method        方法
//   * @param arguments     参数
//   * @param spel          表达式
//   * @param clazz         返回结果的类型
//   * @param defaultResult 默认结果
//   * @return 执行spel表达式后的结果
//   */
//  private <T> T parseSpel(
//      Method method, Object[] arguments, String spel, Class<T> clazz, T defaultResult) {
//    String[] params = discoverer.getParameterNames(method);
//    EvaluationContext context = new StandardEvaluationContext();
//    for (int len = 0; len < params.length; len++) {
//      context.setVariable(params[len], arguments[len]);
//    }
//    try {
//      Expression expression = parser.parseExpression(spel);
//      return expression.getValue(context, clazz);
//    } catch (Exception e) {
//      log.error("解析操作日志表达式错误，", e.getMessage());
//      return defaultResult;
//    }
//  }
}
