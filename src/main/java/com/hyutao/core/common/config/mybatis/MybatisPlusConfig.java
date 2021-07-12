package com.hyutao.core.common.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MybatisPlus 配置.
 */
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = {"com.**.mapper"})
public class MybatisPlusConfig {

  /**
   * mybatis-plus分页插件
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor();
  }


}
