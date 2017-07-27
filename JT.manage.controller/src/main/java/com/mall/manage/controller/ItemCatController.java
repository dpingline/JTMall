package com.mall.manage.controller;

import com.mall.manage.pojo.ItemCat;
import com.mall.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: Ccaveman
 * @Description: 商品分类Controller
 * @Date: Created in 12:18 2017/7/26
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    @ResponseBody
    public List<ItemCat> findItemCatList(@RequestParam(value = "id" ,defaultValue = "0") Long parentId){

        return itemCatService.findItemCatList(parentId);
    }
}
