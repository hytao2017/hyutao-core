package com.hyutao.core.common.bean;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Tree Node
 */
@Data
@NoArgsConstructor
@Builder
public class TreeNode implements Serializable {


  /**
   * 标题
   */
  private String title;
  /**
   * 值
   */
  private String value;

  /**
   * key
   */
  private String key;

  /**
   * 子节点
   */
  private List<TreeNode> children;


  public TreeNode(String title, String value, String key) {
    this.title = title;
    this.value = value;
    this.key = key;
  }

  public TreeNode(String title, String value, String key, List<TreeNode> children) {
    this.title = title;
    this.value = value;
    this.key = key;
    this.children = children;
  }
}
