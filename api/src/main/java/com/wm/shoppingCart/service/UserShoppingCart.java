package com.wm.shoppingCart.service;

import com.wm.shoppingCart.model.ShoppingCartCommodityVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserShoppingCart
 * @Description 对用户购物车进行一系列操作
 * @Author 李鉴
 * @Date 2020/4/7 10:41
 * @Version 1.0
 **/
public interface UserShoppingCart {
    boolean addCommodityToUserShoppingCart(ShoppingCartCommodityVO commodity);
    boolean addCommodityToUserShoppingCart(List<ShoppingCartCommodityVO> commodities);
    ArrayList<ShoppingCartCommodityVO> getAllUserShoppingCartCommodity(int id);
    boolean manageUserShoppingCart(List<ShoppingCartCommodityVO> commodities);
}
