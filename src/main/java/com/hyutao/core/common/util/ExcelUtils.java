package com.hyutao.core.common.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.hyutao.core.common.ConstKit;
import com.hyutao.core.common.exception.BusinessException;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * excel工具类
 */
public class ExcelUtils {

  private ExcelUtils() {
    throw new IllegalStateException("Utility class");
  }


  /**
   * 导入表格
   *
   * @param filePath  文件路径
   * @param pojoClass 接受类对象
   */
  public static <T> List<T> importExcel(String filePath, Class<T> pojoClass) {
    try {
      return ExcelImportUtil.importExcel(new File(filePath), pojoClass, new ImportParams());
    } catch (Exception e) {
      throw new BusinessException("导入表格失败" + e.getLocalizedMessage());
    }
  }

  /**
   * 导出表格
   *
   * @param list           集合
   * @param pojoClass      类
   * @param fileName       文件名+".xlsx"
   * @param isCreateHeader 是否要表头
   * @param response       response
   */
  public static void exportExcel(List<?> list, Class<?> pojoClass,
      String fileName, boolean isCreateHeader, HttpServletResponse response) {
    ExportParams exportParams = new ExportParams();
    exportParams.setCreateHeadRows(isCreateHeader);
    exportParams.setType(ExcelType.XSSF);
    exportParams.setStyle(ExcelStyleUtil.class);
    Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
    try {
      //下面设置好直接使用即可导出
      fileName = URLEncoder.encode(fileName, ConstKit.CHARSET.displayName());
      fileName = fileName.replaceAll("\\+", "%20");
      //设置响应头，控制浏览器下载该文件
      response.setHeader("content-disposition", "attachment;filename=" + fileName);
      response.setContentType(
          "content-type:application/octet-stream;charset:utf-8");
      OutputStream outputStream = response.getOutputStream();
      workbook.write(outputStream);
      outputStream.flush();
      outputStream.close();
    } catch (Exception e) {
      throw new BusinessException("导出表格失败" + e.getLocalizedMessage());
    }
  }

}
