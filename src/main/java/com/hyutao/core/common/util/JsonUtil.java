package com.hyutao.core.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * json 工具类
 */
@Slf4j
public class JsonUtil {

  private JsonUtil() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * @param map
   * @return java.lang.String @Author AlphaJunS @Date 19:09 2020/3/27 @Description map转json
   */
  public static String mapToJson(Map<String, Object> map) {
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonString = null;
    try {
      jsonString = objectMapper.writeValueAsString(map);
    } catch (JsonProcessingException e) {
      log.error("转化出错{}", e.getMessage());
    }
    return jsonString;
  }
}
