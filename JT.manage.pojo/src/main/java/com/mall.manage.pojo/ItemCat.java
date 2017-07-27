package com.mall.manage.pojo;

import com.mall.common.po.BasePojo;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: Ccaveman
 * @Description: 商品分类表
 * @Date: Created in 11:41 2017/7/26
 */
@Data
@Table(name = "tb_item_cat")
public class ItemCat extends BasePojo{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long parentId;

  private String name;

  private Integer status;

  private Integer sortOrder;

  private Boolean isParent;


    /**
     * 为满足EasyUI树形参数要求返回 text 和state
     * @return
     */
  public String getText(){
      return name;
  }

  public String getState(){
      return isParent?"closed":"open";
  }
}
