package com.mall.manage.pojo;

import com.mall.common.po.BasePojo;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: Ccaveman
 * @Description: 商品列表查询
 * @Date: Created in 19:07 2017/7/25
 */
@Data
@Table(name = "tb_item") //表示与表一一对应
public class Item extends BasePojo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//
    private Long id ;   //商品Id

    private String title; //商品标题

    private String sellPoint; //卖点

    private Long price;   //价格

    private int num;    //库存

    private String barcode; //扫描码

    private String image; //商品图片

    private Long cid;   //分类号

    private  int status; //商品状态 '默认值为1，可选值：1正常，2下架，3删除',


}
