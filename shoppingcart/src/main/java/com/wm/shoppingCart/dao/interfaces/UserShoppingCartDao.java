package com.wm.shoppingCart.dao.interfaces;

import com.wm.shoppingCart.model.ShoppingCartCommodityDO;

import java.util.List;

/**
 * @ClassName UserShoppingCartDao
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/10 14:50
 * @Version 1.0
 **/
public interface UserShoppingCartDao {
    boolean addCommodity(ShoppingCartCommodityDO commodityDO);
    boolean addCommodities(List<ShoppingCartCommodityDO> commodityDOS);
    List<ShoppingCartCommodityDO> getCommoditiesByUserId(int userId);
    boolean updateCommodityCount(ShoppingCartCommodityDO commodityDO);
    boolean updateCommoditiesCount(List<ShoppingCartCommodityDO> commodityDOS);
    boolean removeCommodities(List<ShoppingCartCommodityDO> commodityDOS);
    boolean addAndUpdateCommodities(List<ShoppingCartCommodityDO> addList, List<ShoppingCartCommodityDO> updateList);
    boolean updateAndRemoveCommodities(List<ShoppingCartCommodityDO> updateList, List<ShoppingCartCommodityDO> removeList);
}
