package com.mall.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Ccaveman
 * @Description: 跳转后台首页
 * @Date: Created in 23:22 2017/7/24
 */
@Controller
public class IndexController {


    /**
     * 使用Restful实现页面统一跳转
     * @param index 后台首页
     * @return 返回至后台首页
     */
    @RequestMapping("/page/{index}")
    public String toIndex(@PathVariable String index){
        return index;
    }

}
