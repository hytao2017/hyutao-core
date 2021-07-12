package com.hyutao.core.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring Context 工具类
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

  private static final Map<String, ApplicationContext> PROP_MAP = new HashMap<>();

  private static final String APPLICATION_KEY = "applicationContext";


  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    PROP_MAP.put(APPLICATION_KEY, applicationContext);
  }

  /**
   * 获取applicationContext
   */
  public static ApplicationContext getApplicationContext() {
    return PROP_MAP.get(APPLICATION_KEY);
  }

  /**
   * 通过name获取 Bean.
   */
  public static Object getBean(String name) {
    return getApplicationContext().getBean(name);
  }

  /**
   * 通过class获取Bean.
   */
  public static <T> T getBean(Class<T> clazz) {
    return getApplicationContext().getBean(clazz);
  }

  /**
   * 通过name,以及Clazz返回指定的Bean
   */
  public static <T> T getBean(String name, Class<T> clazz) {
    return getApplicationContext().getBean(name, clazz);
  }

  /**
   * 通过name获取 Bean.
   */
  public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
    return getApplicationContext().getBeansOfType(clazz);
  }

  /**
   * 获取配置文件配置项的值
   *
   * @param key 配置项key
   */
  public static String getEnvironmentProperty(String key) {
    return getApplicationContext().getEnvironment().getProperty(key);
  }

  /**
   * 获取spring.profiles.active
   */
  public static String getActiveProfile() {
    return getApplicationContext().getEnvironment().getActiveProfiles()[0];
  }

}