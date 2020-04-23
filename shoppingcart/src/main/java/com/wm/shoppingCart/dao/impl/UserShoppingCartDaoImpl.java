package com.wm.shoppingCart.dao.impl;

import com.wm.shoppingCart.dao.interfaces.UserShoppingCartDao;
import com.wm.shoppingCart.dao.mapper.UserShoppingCartMapper;
import com.wm.shoppingCart.model.ShoppingCartCommodityDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserShoppingCartDaoImpl
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/10 15:00
 * @Version 1.0
 **/
@Repository
@Transactional
public class UserShoppingCartDaoImpl implements UserShoppingCartDao {
    private static int INSERT_ONE_RECORD_SUCCESS_CODE = 1;
    @Autowired
    private UserShoppingCartMapper cartMapper;

    @Override
    public boolean addCommodity(ShoppingCartCommodityDO commodityDO) {
        if (cartMapper.insert(commodityDO) == INSERT_ONE_RECORD_SUCCESS_CODE) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addCommodities(List<ShoppingCartCommodityDO> commodityDOS) {
        if (commodityDOS.size() == cartMapper.insertList(commodityDOS)) {
            return true;
        }
        return false;
    }

    @Override
    public List<ShoppingCartCommodityDO> getCommoditiesByUserId(int userId) {
        return cartMapper.getCommoditiesByUserId(userId);
    }

    @Override
    public boolean updateCommodityCount(ShoppingCartCommodityDO commodityDO) {
        return cartMapper.updateCount(commodityDO);
    }

    @Override
    public boolean updateCommoditiesCount(List<ShoppingCartCommodityDO> commodityDOS) {
        return cartMapper.updateListCount(commodityDOS);
    }

    @Override
    public boolean removeCommodities(List<ShoppingCartCommodityDO> commodityDOS) {
        return cartMapper.deleteList(commodityDOS);
    }

    @Override
    public boolean addAndUpdateCommodities(List<ShoppingCartCommodityDO> addList, List<ShoppingCartCommodityDO> updateList) {
        boolean success = addList.size() == cartMapper.insertList(addList);
        success &= cartMapper.updateListCount(updateList);
        return success;
    }

    @Override
    public boolean updateAndRemoveCommodities(List<ShoppingCartCommodityDO> updateList, List<ShoppingCartCommodityDO> removeList) {
        boolean success = cartMapper.updateListCount(updateList);
        success &= cartMapper.deleteList(removeList);
        return success;
    }
}
