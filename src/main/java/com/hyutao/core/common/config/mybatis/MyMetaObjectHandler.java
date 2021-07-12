package com.hyutao.core.common.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.iflytek.iakpls.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * 自定义填充公共字段.
 *
 * @author heli0
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

  /**
   * 获取该访问用户信息
   *
   * @return 已登录用户
   */
  public Long getAuthUserId() {
    Long userId = 0L;
    if (SecurityUtil.isAuthenticated()) {
      userId = SecurityUtil.getCurrentUserId();
    }
    return userId;
  }

  @Override
  public void insertFill(MetaObject metaObject) {
    Timestamp time = new Timestamp(System.currentTimeMillis());
    this.setFieldValByName("createUser", getAuthUserId(), metaObject);
    this.setFieldValByName("createTime", time, metaObject);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    this.setFieldValByName("updateUser", getAuthUserId(), metaObject);
    this.setFieldValByName("updateTime", new Timestamp(System.currentTimeMillis()),
        metaObject);

  }

}
