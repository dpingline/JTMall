package com.mall.manage.mapper;

import com.mall.common.mapper.SysMapper;
import com.mall.manage.pojo.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Ccaveman
 * @Description: 商品类mapper
 * @Date: Created in 20:33 2017/7/25
 */
public interface ItemMapper extends SysMapper<Item> {

    /**
     * 查询全部商品类信息,根据日期倒叙排列
     *
     */
    List<Item> findItemList ();

    /**
     * 根据商品的分类id查询分类商品
     * @param itemCatId
     * @return
     */
    String findItemCatName(Long itemCatId);

    /**
     * 批量上架或者下架商品
     * @param ids
     */
    void updateItemStatusByIds(@Param("ids") Long[] ids,@Param("status") int status);
}
