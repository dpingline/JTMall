package com.mall.manage.service;


import com.mall.common.vo.EasyUIResult;
import com.mall.manage.pojo.Item;
import com.mall.manage.pojo.ItemDesc;

/**
 * @Author: Ccaveman
 * @Description: 商品类Service
 * @Date: Created in 20:49 2017/7/25
 */
public interface ItemService {

    EasyUIResult findItemList(int page,int rows);

    String findItemCatName(Long itemCatId);

    void saveItem(Item item,String desc);

    void updateItem(Item item,String desc);

    void deleteItem(Long [] ids);

    void updateItemStatusByIds(Long[] ids,int status);

    ItemDesc findItemDescByItemId(Long itemId);
}
