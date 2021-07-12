package com.hyutao.core.common.config;

import com.iflytek.iakpls.common.config.es.ElasticSearchProperties;
import com.iflytek.iakpls.modular.search.service.SearchService;
import com.iflytek.iakpls.modular.sync.service.SysApiConfigService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HeLi
 * @date 2020/7/11
 * @since
 */
@Slf4j
@Configuration
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class StartupConfig {

  SearchService searchService;

  ElasticSearchProperties elasticSearchProperties;

  SysApiConfigService sysApiConfigService;

  @Bean
  public CommandLineRunner initData() {
    return args -> {
      String index = elasticSearchProperties.getIndices();
      log.info("初始化ES[{}]索引……", index);
      boolean checkIndex = searchService.checkIndex(index);
      if (!checkIndex) {
        searchService.createIndex(index);
      }
    };
  }

  @Bean
  public CommandLineRunner initSync() {
    return args -> sysApiConfigService.loadConfigurations();
  }

}
