package com.mall.manage.controller;

import com.mall.common.vo.EasyUIResult;
import com.mall.common.vo.SysResult;
import com.mall.manage.pojo.Item;
import com.mall.manage.pojo.ItemDesc;
import com.mall.manage.service.ItemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Ccaveman
 * @Description: 商品类的Controller
 * @Date: Created in 10:26 2017/7/25
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    private static final Logger logger = Logger.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;
    /**
    *
    * easyUI的全部请求是以Ajax提交
    * 值得传递是以json形式进行
    */
    @RequestMapping("/query")
    @ResponseBody
    public EasyUIResult findItemList(int page, int rows){

        return itemService.findItemList(page,rows);
    }

    /**
     * 查询商品叶子类目
     * @param itemCatId 叶子类目Id
     * @param response  响应
     */
    @RequestMapping("/cat/queryItemName")
    public void queryItemCatName(Long itemCatId, HttpServletResponse response){

        String name = itemService.findItemCatName(itemCatId);
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().write(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 商品新增
     * @param item 商品对象
     * @return 返回的Json 对象
     */
    @RequestMapping("/save")
    @ResponseBody
    public SysResult saveItem(Item item,String desc){

        try {

            itemService.saveItem(item,desc);
            return SysResult.build(200,"商品新增成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("~~~~~新增商品失败"+e.getMessage());
            return SysResult.build(201,"新增商品失败，请联系管理员");

        }

    }

    /**
     * 商品更新
     * @param item 商品更新的对象
     * @return 返回到页面的Json 对象
     */
    @RequestMapping("/update")
    @ResponseBody
    public SysResult updateItem(Item item,String desc){
        try {

            itemService.updateItem(item,desc);
            return SysResult.build(200,"修改商品成功");
        }catch (Exception e){

            e.printStackTrace();
            logger.error("~~~~~~~新增商品失败"+e.getMessage());
            return SysResult.build(201,"修改商品失败");

        }
    }

    /**
     * 删除商品
     * @param ids 商品id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public SysResult deleteItem(Long [] ids){
        try {

            itemService.deleteItem(ids);
            return SysResult.build(200,"商品删除成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("~~~~~~商品删除失败"+e.getMessage());
            return SysResult.build(201,"商品删除失败");
        }

    }

    /**
     * 商品下架
     * @return 商品上架后返回的信息
     */
    @RequestMapping("/instock")
    @ResponseBody
    public SysResult instockItem(Long [] ids){
        int status = 2;
        itemService.updateItemStatusByIds(ids,status);
        return SysResult.build(200,"商品上架成功");
    }

    /**
     * 商品上架
     * @param ids 需要上架商品id
     * @return
     */
    @RequestMapping("/reshelf")
    @ResponseBody
    public SysResult reshelfItem(Long [] ids){

        int status = 1;

        itemService.updateItemStatusByIds(ids,status);

        return SysResult.build(200,"商品下架成功");
    }

    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public SysResult findItemDesc(@PathVariable Long itemId){


        try {

            ItemDesc itemDesc = itemService.findItemDescByItemId(itemId);

            return SysResult.oK(itemDesc);

        }catch (Exception e){

            e.printStackTrace();

            logger.error("~~~~~~查询商品描述信息失败"+e.getMessage());

            return SysResult.build(201,"查询商品描述信息失败!请联系管理员");
        }
    }
}
