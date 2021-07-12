package com.hyutao.core.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步async 配置
 *
 * @author HeLi
 * @date 2020/6/6
 * @since
 */
@EnableAsync
@Configuration
public class AsyncConfig {

  @Bean
  public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    //配置核心线程数
    executor.setCorePoolSize(3);
    //配置最大线程数
    executor.setMaxPoolSize(10);
    //配置队列大小
    executor.setQueueCapacity(200);
    //配置线程池中的线程的名称前缀
    executor.setThreadNamePrefix("ejoy-async-");
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    executor.initialize();
    return executor;
  }


}
