package com.mall.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.service.BaseService;
import com.mall.common.vo.EasyUIResult;
import com.mall.manage.mapper.ItemDescMapper;
import com.mall.manage.mapper.ItemMapper;
import com.mall.manage.pojo.Item;
import com.mall.manage.pojo.ItemDesc;
import com.mall.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: Ccaveman
 * @Description:
 * @Date: Created in 20:55 2017/7/25
 */
@Service
public class ItemServiceImpl extends BaseService<Item> implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;
    /**
     *
     * 查找全部商品组成的集合
     * @return 商品组成的集合
     */
    @Override
    public EasyUIResult findItemList(int page,int rows) {


        //使用分页插件进行分页查询 page 页数 rows 查询数据量
        //
        PageHelper.startPage(page,rows);

        List<Item> itemList = itemMapper.findItemList();
        //自己计算全部信息
        PageInfo<Item> info = new PageInfo<Item>(itemList);

        return new EasyUIResult(info.getTotal(),info.getList());
    }

    /**
     * 根据商品分类id查询商品分类名称
     * @param itemCatId
     * @return
     */
    @Override
    public String findItemCatName(Long itemCatId) {
        return itemMapper.findItemCatName(itemCatId);
    }


    /**
     * 向数据库中插入新增商品
     * @param item 所需要插入的商品对象
     */
    @Override
    public void saveItem(Item item,String desc) {

        item.setCreated(new Date());

        item.setUpdated(item.getCreated());

        itemMapper.insertSelective(item);

        /*
        * 当向商品表插入数据时，相应的商品详情描述表也需要插入商品描述信息
        * 但是插入的主键是商品表中的id，当未完成插入操作时数据库查询不出商品id
        * 这时需要用到mybatis+通用mapper+mysql进行insert插入后再次查询商品表中的新增id
        * 这时商品详情描述表中就有了id从而达到同时插入操作
        *　注意，此通用mapper只对mysql有效对oracle无效
        * */
        ItemDesc itemDesc = new ItemDesc();

        itemDesc.setItemId(item.getId());

        itemDesc.setItemDesc(desc);

        itemDesc.setCreated(item.getCreated());

        itemDesc.setUpdated(item.getUpdated());

        itemDescMapper.insert(itemDesc);
    }

    /**
     * 更新商品
     * @param item 更新商品对象
     */
    @Override
    public void updateItem(Item item,String desc) {

        item.setUpdated(new Date());

        itemMapper.updateByPrimaryKeySelective(item);

        ItemDesc itemDesc = new ItemDesc();

        itemDesc.setItemId(item.getId());

        itemDesc.setItemDesc(desc);

        itemDesc.setCreated(item.getCreated());

        itemDesc.setUpdated(item.getUpdated());

        itemDescMapper.updateByPrimaryKeySelective(itemDesc);
    }


    /**
     * 删除商品
     * @param ids 需要删除的商品对象
     */
    @Override
    public void deleteItem(Long[] ids) {

        //删除商品描述信息
        itemDescMapper.deleteByIDS(ids);

        //删除商品信息
        itemMapper.deleteByIDS(ids);

    }

    /**
     * 下架商品
     * @param ids 商品id组成的集合
     */
    @Override
    public void updateItemStatusByIds(Long[] ids,int status) {

        itemMapper.updateItemStatusByIds(ids,status);
    }

    /**
     * 查询商品描述信息
     * @param itemId 商品id
     * @return 商品描述信息对象
     */
    @Override
    public ItemDesc findItemDescByItemId(Long itemId) {

        return  itemDescMapper.selectByPrimaryKey(itemId);
    }


}
