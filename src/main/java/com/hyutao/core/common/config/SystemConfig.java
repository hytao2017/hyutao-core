package com.hyutao.core.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author HeLi
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "system.config")
public class SystemConfig {

  /**
   * Security 登录地址
   */
  private String loginUrl;
  /**
   * Security 退出地址
   */
  private String logoutUrl;

  /**
   * 免认证接口
   */
  private String[] anonUrl;

  /**
   * 允许上传的文件格式
   */
  private String[] allowFormat;

  /**
   * 文件存储路径
   */
  private String filePath;

  /**
   * 文件浏览路径
   */
  private String fileUrl;

  /**
   * wiki系统基础路径
   */
  private String wikiPath;

  /**
   * cors AllowedOrigin
   */
  private String corsAllowedOrigin;

  /**
   * 文件预览后缀
   */
  private String filePreviewExt;
  /**
   * 文件预览url
   */
  private String filePreviewUrl;

  /**
   * 课程推荐算法相似度取值
   */
  private double classSimilarity;


}
