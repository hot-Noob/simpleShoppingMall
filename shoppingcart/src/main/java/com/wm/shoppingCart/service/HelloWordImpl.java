package com.wm.shoppingCart.service;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @ClassName HellowordImpl
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/4 17:12
 * @Version 1.0
 **/
@Service
public class HelloWordImpl implements HelloWorld {
    @Override
    public String helloWorld() {
        return "hello李俨";
    }
}
