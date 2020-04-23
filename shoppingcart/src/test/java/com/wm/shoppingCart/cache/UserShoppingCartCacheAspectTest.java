package com.wm.shoppingCart.cache;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wm.shoppingCart.ShoppingCartApplication;
import com.wm.shoppingCart.model.ShoppingCartCommodityDO;
import com.wm.shoppingCart.model.ShoppingCartCommodityVO;
import com.wm.shoppingCart.service.UserShoppingCart;
import com.wm.shoppingCart.service.UserShoppingCartImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @ClassName UserShoppingCartCacheAspectTest
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/15 17:36
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingCartApplication.class)
public class UserShoppingCartCacheAspectTest {
    @Reference
    public UserShoppingCart userShoppingCart;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private ShoppingCartCommodityVO commodityDO1;
    private ShoppingCartCommodityVO commodityDO2;
    private ShoppingCartCommodityVO commodityDO3;
    private List<ShoppingCartCommodityVO> list =  new ArrayList<>();

    {
        commodityDO1 = new ShoppingCartCommodityVO();
        commodityDO1.setCommodityId(004);
        commodityDO1.setUserId(799531106);
        commodityDO1.setCount(6);
        commodityDO2 = new ShoppingCartCommodityVO();
        commodityDO2.setCommodityId(005);
        commodityDO2.setUserId(799531106);
        commodityDO2.setCount(7);
        commodityDO3 = new ShoppingCartCommodityVO();
        commodityDO3.setCommodityId(003);
        commodityDO3.setUserId(799531106);
        commodityDO3.setCount(8);
        commodityDO3.setProperty("140*20");
        list.add(commodityDO1);
        list.add(commodityDO2);
        list.add(commodityDO3);
    }

    @Test
    public void aroundGetAllUserShoppingCartCommodity() {
        System.out.println(userShoppingCart.getAllUserShoppingCartCommodity(799531106));
    }

    @Test
    public void aroundParamIsList() {
    }

    @Test
    public void aroundParamIsSingle() {
        System.out.println(userShoppingCart.addCommodityToUserShoppingCart(commodityDO3));
    }
}