package com.mall.manage.service;

import com.mall.manage.pojo.ItemCat;

import java.util.List;

/**
 * @Author: Ccaveman
 * @Description: 商品分类service
 * @Date: Created in 12:02 2017/7/26
 */
public interface ItemCatService {

    List<ItemCat> findItemCatList(Long parentId);
}
