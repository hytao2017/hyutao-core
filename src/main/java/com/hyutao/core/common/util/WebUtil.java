package com.hyutao.core.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * web 工具类
 */
@Slf4j
public class WebUtil {

  private WebUtil() {
    throw new IllegalStateException("Utility class");
  }

  private static final String UNKNOWN = "unknown";

  private static final String[] LOCAL_IP = new String[]{"127.0.0.1", "0:0:0:0:0:0:0:1"};

  /**
   * 获取客户端IP地址. 最外层nginx 代理加入 proxy_set_header X-Real-IP $remote_addr; proxy_set_header
   * X-Forwarded-For $remote_addr; 内层nginx 代理加入 注意不要加X-Real-IP配置 proxy_set_header X-Forwarded-For
   * $proxy_add_x_forwarded_for;
   *
   * @return
   */
  public static String getIpAddr() {
    HttpServletRequest request = getRequest();
    // 从Nginx中X-Real-IP获取真实ip
    String ipAddress = request.getHeader("X-Real-IP");
    if (ipAddress != null && ipAddress.length() > 0 && !UNKNOWN.equalsIgnoreCase(ipAddress)) {
      return ipAddress;
    }
    // 从Nginx中x-forwarded-for获取真实ip
    ipAddress = request.getHeader("x-forwarded-for");

    if (ipAddress != null && ipAddress.length() > 0 && !UNKNOWN.equalsIgnoreCase(ipAddress)) {
      // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
      int index = ipAddress.indexOf(',');
      if (index > 0) {
        ipAddress = ipAddress.substring(0, ipAddress.indexOf(','));
      }
      return ipAddress;
    }
    ipAddress = request.getRemoteAddr();
    if (Arrays.asList(LOCAL_IP).contains(ipAddress)) {
      // 根据网卡取本机配置的IP
      try {
        ipAddress = InetAddress.getLocalHost().getHostAddress();
      } catch (UnknownHostException e) {
        log.error("获取网卡地址异常,{}", e.getMessage());
      }
    }
    return ipAddress;

  }


  /**
   * 获取request
   *
   * @return HttpServletRequest
   */
  public static HttpServletRequest getRequest() {
    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    return requestAttributes.getRequest();
  }

  /**
   * 使用response输出JSON
   *
   * @CreateTime 2019/9/28 11:23
   * @Param resultMap 数据
   * @Return void
   */
  public static void writeJson(HttpServletResponse response, Object object) {
    PrintWriter out = null;
    try {
      ObjectMapper objectMapper = SpringContextUtil.getBean(ObjectMapper.class);
      response.setStatus(HttpServletResponse.SC_OK);
      response.setCharacterEncoding("UTF-8");
      response.setContentType("application/json;charset=UTF-8");
      out = response.getWriter();
      out.write(objectMapper.writeValueAsString(object));
    } catch (Exception e) {
      log.error("【JSON输出异常】" + e);
    } finally {
      if (out != null) {
        out.flush();
        out.close();
      }
    }
  }
}
