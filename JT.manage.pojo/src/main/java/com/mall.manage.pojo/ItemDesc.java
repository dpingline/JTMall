package com.mall.manage.pojo;

import com.mall.common.po.BasePojo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: Ccaveman
 * @Description:
 * @Date: Created in 10:20 2017/7/25
 */
@Table(name = "tb_item_desc")
public class ItemDesc extends BasePojo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long ItemId;

    @Getter
    @Setter
    private String ItemDesc;


}
