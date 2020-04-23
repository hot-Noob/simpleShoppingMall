package com.wm.shoppingWeb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wm.shoppingCart.model.ShoppingCartCommodityVO;
import com.wm.shoppingCart.service.HelloWorld;
import com.wm.shoppingWeb.controller.model.UserVO;
import com.wm.shoppingWeb.util.multiRequestBody.MultiRequestBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName HelloWorldContoller
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/4 18:50
 * @Version 1.0
 **/
@Controller
public class HelloWorldController {
    @Reference
    private HelloWorld helloworld;

    @GetMapping("/hello")
    @ResponseBody
    public String helloWorld() {
        return helloworld.helloWorld();
    }

    @RequestMapping("/testMultiRequestBody")
    @ResponseBody
    public String testMultiRequestBody(@MultiRequestBody ShoppingCartCommodityVO commodity,
                                       @MultiRequestBody UserVO userVO) {
        System.out.println("33" + commodity + userVO);
        return "???？";
    }
}
