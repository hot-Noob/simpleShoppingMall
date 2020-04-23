package com.wm.shoppingCart.dao.impl;

import com.wm.shoppingCart.ShoppingCartApplication;
import com.wm.shoppingCart.dao.interfaces.UserShoppingCartDao;
import com.wm.shoppingCart.model.ShoppingCartCommodityDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserShoppingCartDaoImplTest
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/10 15:32
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingCartApplication.class)
public class UserShoppingCartDaoImplTest {
    @Autowired
    private UserShoppingCartDao userShoppingCartDao;
    private ShoppingCartCommodityDO commodityDO1;
    private ShoppingCartCommodityDO commodityDO2;
    private ShoppingCartCommodityDO commodityDO3;
    private List<ShoppingCartCommodityDO> list =  new ArrayList<>();

    {
//        commodityDO1 = new ShoppingCartCommodityDO();
//        commodityDO1.setCommodityId(004);
//        commodityDO1.setUserId(79953110);
//        commodityDO1.setCount(6);
//        commodityDO2 = new ShoppingCartCommodityDO();
//        commodityDO2.setCommodityId(005);
//        commodityDO2.setUserId(79953110);
//        commodityDO2.setCount(7);
//        commodityDO3 = new ShoppingCartCommodityDO();
//        commodityDO2.setCommodityId(003);
//        commodityDO2.setUserId(79953110);
//        commodityDO2.setCount(8);
//        list.add(commodityDO1);
//        list.add(commodityDO2);
//        list.add(commodityDO3);
    }
    @Test
    public void addCommodityToUserShoppingCart() {
        userShoppingCartDao.addCommodity(commodityDO1);
        System.out.println(commodityDO1);
    }

    @Test
    public void addCommoditiesToUserShoppingCart() {
        System.out.println(userShoppingCartDao.addCommodities(list));
        System.out.println(commodityDO1);
        System.out.println(commodityDO2);
    }

    @Test
    public void getCommoditiesByUserId() {
        System.out.println(userShoppingCartDao.getCommoditiesByUserId(799531106));
    }

    @Test
    public void updateUserShoppingCartCommodityCount() {
        System.out.println(userShoppingCartDao.updateCommodityCount(commodityDO1));
    }

    @Test
    public void removeUserShoppingCartCommodity() {
    }

    @Test
    public void removeUserShoppingCartCommodities() {
    }
}