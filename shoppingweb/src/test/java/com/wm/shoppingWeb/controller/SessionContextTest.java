package com.wm.shoppingWeb.controller;

import com.wm.shoppingCart.model.ShoppingCartCommodityVO;
import com.wm.shoppingWeb.controller.model.UserVO;
import com.wm.shoppingWeb.util.gson.GsonUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SessionContextTest
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/7 22:03
 * @Version 1.0
 **/
class SessionContextTest {

    @Test
    void getInstance() {
        ShoppingCartCommodityVO commodityVO = new ShoppingCartCommodityVO();
        commodityVO.setUserId(799531106);
        commodityVO.setCommodityId(001);
        commodityVO.setCount(1);
        commodityVO.setProperty("白色;印花");
        commodityVO.setPerPrice(new BigDecimal("100"));
        List<ShoppingCartCommodityVO> lis = new ArrayList<>();
        System.out.println(GsonUtil.getGson().toJson(commodityVO));
        UserVO userVO = new UserVO(799531106, "李鉴");
        System.out.println(GsonUtil.getGson().toJson(userVO));
        Boolean b = new Boolean(true);
        System.out.println(GsonUtil.getGson().toJson(b));
        ArrayList<ShoppingCartCommodityVO> list = new ArrayList<>();
        lis.add(commodityVO);
        ShoppingCartCommodityVO commodityVO1 = new ShoppingCartCommodityVO();
        commodityVO1.setUserId(799531106);
        commodityVO1.setCommodityId(1);
        commodityVO1.setCount(1);
        commodityVO1.setProperty("白色;印花");
        commodityVO1.setPerPrice(new BigDecimal("100"));
        ShoppingCartCommodityVO commodityVO2 = new ShoppingCartCommodityVO();
        commodityVO2.setUserId(799531106);
        commodityVO2.setCommodityId(2);
        commodityVO2.setCount(2);
        commodityVO2.setProperty("黑色;印花");
        commodityVO2.setPerPrice(new BigDecimal("100"));
        ShoppingCartCommodityVO commodityVO3 = new ShoppingCartCommodityVO();
        commodityVO3.setUserId(799531106);
        commodityVO3.setCommodityId(5);
        commodityVO3.setCount(5);
        commodityVO3.setProperty("黑色;纯黑");
        commodityVO3.setPerPrice(new BigDecimal("100"));
        list.add(commodityVO1);
        list.add(commodityVO2);
        list.add(commodityVO3);
        System.out.println(GsonUtil.getGson().toJson(list));
    }
}