package com.hyutao.core.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * BaseController 基础类.
 *
 */
public abstract class AbstractController {

  public static final Logger log = LoggerFactory.getLogger(AbstractController.class);

  @Autowired
  protected HttpServletRequest request;

  /**
   * InitBinder .
   *
   * @param binder binder
   */
  @InitBinder
  public void webInitBinder(WebDataBinder binder) {
    // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
    binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
      @Override
      public String getAsText() {
        Object value = getValue();
        return value != null ? value.toString() : "";
      }

      @Override
      public void setAsText(String text) {
        setValue(text == null ? null : HtmlUtils.htmlEscape(text.trim()));
      }
    });

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
    binder.registerCustomEditor(Date.class, orderDateEditor);

  }

}

