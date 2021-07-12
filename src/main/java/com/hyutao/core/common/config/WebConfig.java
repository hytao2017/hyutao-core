package com.hyutao.core.common.config;

import com.iflytek.iakpls.common.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web 配置类 .
 *
 * @author heli0
 * @date 2016年11月12日 下午5:03:32
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  SystemConfig systemConfig;

  private final LogInterceptor logInterceptor;

  public WebConfig(LogInterceptor logInterceptor) {
    this.logInterceptor = logInterceptor;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**")
        .addResourceLocations("/static", "classpath:static/", "file:" + systemConfig.getFilePath());
  }

  /**
   * RequestContextListener注册 .
   */
  @Bean
  @ConditionalOnMissingBean(RequestContextListener.class)
  public RequestContextListener requestContextListener() {
    return new RequestContextListener();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(logInterceptor).addPathPatterns("/**");
  }

}
