package com.mall.manage.service.impl;

import com.mall.manage.mapper.ItemCatMapper;
import com.mall.manage.pojo.ItemCat;
import com.mall.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Ccaveman
 * @Description: 商品分类service
 * @Date: Created in 12:05 2017/7/26
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public List<ItemCat> findItemCatList(Long parentId) {

        ItemCat itemCat = new ItemCat();

        itemCat.setParentId(parentId);

        return itemCatMapper.select(itemCat);
    }
}
