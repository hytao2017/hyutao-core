package com.hyutao.core.common.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.iflytek.iakpls.modular.recommend.service.RecommendService;
import com.iflytek.iakpls.modular.search.service.SearchService;
import com.iflytek.iakpls.modular.search.vo.SearchDoc;
import com.iflytek.iakpls.modular.study.service.LnNodeClassService;
import com.iflytek.iakpls.modular.sync.service.ConfluenceService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.List;

/**
 * @author HeLi
 * @date 2020/7/14
 * @since
 */
@Slf4j
@EnableScheduling
@Configuration
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ScheduledConfig {

  @Autowired
  private RecommendService recommendService;

  @Autowired
  private LnNodeClassService lnNodeClassService;


  ConfluenceService confluenceService;
  SearchService searchService;

  @Scheduled(fixedDelay = 600000, initialDelay = 1000)
  public void syncAll() throws IOException {
    log.info("开始全量数据同步>>>");
    List<SearchDoc> searchDocs = confluenceService.getAllSearchDoc();
    searchDocs.forEach(doc -> {
      if (StrUtil.isNotBlank(doc.getContent())) {
        doc.setContent(HtmlUtil.cleanHtmlTag(doc.getContent()));
      }
    });
    searchService.batchInsert(searchDocs);
  }


  // 每天凌晨1点计算一次推荐算法的相似矩阵
  @Scheduled(cron = "0 0 1 * * ?")
  public void calculateSimilarityMatrix() {
    recommendService.readData();
  }

  @Scheduled(cron = "0 */5 * * * ?")
  public void updateRouterClass() {

    lnNodeClassService.AutoFinishOfflineClass();

  }
}
