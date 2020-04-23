package com.wm.shoppingCart.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wm.shoppingCart.dao.interfaces.UserShoppingCartDao;
import com.wm.shoppingCart.model.ShoppingCartCommodityDO;
import com.wm.shoppingCart.model.ShoppingCartCommodityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserShoppingCartImpl
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/7 11:17
 * @Version 1.0
 **/
@Service
@Component
public class UserShoppingCartImpl implements UserShoppingCart {
    @Autowired
    private UserShoppingCartDao userShoppingCartDao;

    @Override
    public boolean addCommodityToUserShoppingCart(ShoppingCartCommodityVO commodityVO) {
        if (!parameterCalibration(commodityVO) || commodityVO.getCount() <= 0) {
            return false;
        }
        ArrayList<ShoppingCartCommodityVO> commodityVOS = getAllUserShoppingCartCommodity(commodityVO.getUserId());
        ShoppingCartCommodityDO commodityDO = new ShoppingCartCommodityDO(commodityVO);
        if (commodityVOS != null) {
            for (ShoppingCartCommodityVO commodity : commodityVOS) {
                if (commodityVO.equals(commodity)) {
                    commodityDO.setCount(commodityDO.getCount() + commodity.getCount());
                    return userShoppingCartDao.updateCommodityCount(commodityDO);
                }
            }
        }
        return userShoppingCartDao.addCommodity(commodityDO);
    }

    @Override
    public boolean addCommodityToUserShoppingCart(List<ShoppingCartCommodityVO> commodities) {
        if (commodities == null && commodities.size() > 0) {
            return false;
        }
        List<ShoppingCartCommodityDO> insertList = new ArrayList<>();
        List<ShoppingCartCommodityDO> updateList = new ArrayList<>();

        ArrayList<ShoppingCartCommodityVO> commodityVOS = getAllUserShoppingCartCommodity(commodities.get(0).getUserId());
        Out:
        for (ShoppingCartCommodityVO commodity : commodities) {
            if (!parameterCalibration(commodity) || commodity.getCount() <= 0) {
                return false;
            }
            for (ShoppingCartCommodityVO commodityVO : commodityVOS) {
                if (commodityVO.equals(commodity)) {
                    ShoppingCartCommodityDO commodityDO = new ShoppingCartCommodityDO(commodityVO);
                    commodityDO.setCount(commodity.getCount() + commodityVO.getCount());
                    updateList.add(commodityDO);
                    continue Out;
                }
            }
            insertList.add(new ShoppingCartCommodityDO(commodity));
        }

        if (insertList.size() <= 0) {
            if (updateList.size() <= 0) {
                return false;
            }
            return userShoppingCartDao.updateCommoditiesCount(insertList);
        } else {
            if (updateList.size() <= 0) {
                return userShoppingCartDao.addCommodities(insertList);
            }
            return userShoppingCartDao.addAndUpdateCommodities(insertList, updateList);
        }
    }

    @Override
    public ArrayList<ShoppingCartCommodityVO> getAllUserShoppingCartCommodity(int id) {
        if (id <= 0) {
            return null;
        }
        List<ShoppingCartCommodityDO> commodities =  userShoppingCartDao.getCommoditiesByUserId(id);
        if (commodities == null) {
            return null;
        }
        ArrayList<ShoppingCartCommodityVO> commodityVOS = new ArrayList<>();
        for (ShoppingCartCommodityDO commodityDO : commodities) {
            ShoppingCartCommodityVO commodityVO = new ShoppingCartCommodityVO(commodityDO);
            commodityVOS.add(commodityVO);
        }
        return commodityVOS;
    }

    @Override
    public boolean manageUserShoppingCart(List<ShoppingCartCommodityVO> commodities) {
        if (commodities == null || commodities.size() <= 0) {
            return false;
        }

        List<ShoppingCartCommodityDO> removeList = new ArrayList<>();
        List<ShoppingCartCommodityDO> updateList = new ArrayList<>();

        ArrayList<ShoppingCartCommodityVO> commodityVOS = getAllUserShoppingCartCommodity(commodities.get(0).getUserId());
        Out:
        for (ShoppingCartCommodityVO commodity : commodities) {
            if (!parameterCalibration(commodity)) {
                System.out.println(110);
                return false;
            }
            for (ShoppingCartCommodityVO commodityVO : commodityVOS) {
                if (commodityVO.equals(commodity)) {
                    ShoppingCartCommodityDO commodityDO = new ShoppingCartCommodityDO(commodityVO);
                    commodityDO.setCount(commodity.getCount() + commodityVO.getCount());
                    if (commodityDO.getCount() > 0) {
                        updateList.add(commodityDO);
                    } else {
                        removeList.add(commodityDO);
                    }
                   continue Out;
                }
            }
            System.out.println(125);
            return false;
        }

        if (updateList.size() <= 0) {
            if (removeList.size() <= 0) {
                System.out.println(131);
                return false;
            }
            return userShoppingCartDao.removeCommodities(removeList);
        } else {
            if (removeList.size() <= 0) {
                return userShoppingCartDao.updateCommoditiesCount(updateList);
            }
            return userShoppingCartDao.updateAndRemoveCommodities(updateList, removeList);
        }
    }
    /**
     * @Author 李鉴
     * @Description 对参数的校验提取出来
     * @Date 2020/4/11 10:15
     * @Param 从RPC消费端传过来的ShoppingCartCommodityVO对象
     * @Return true表示参数合法，false表示参数不合法
     * @Exception
     **/
    private boolean parameterCalibration(ShoppingCartCommodityVO commodity) {
        if (commodity == null) {
            return false;
        }

        // 参数内容检验
        if (commodity.getUserId() <= 0 || commodity.getCommodityId() <= 0) {
            return false;
        }
        // TODO 可以做一些详细的校验，比如用户id是否存在，商品id是否存在，其它参数是否合法

        if (commodity.getProperty() == null || commodity.getProperty().length() <= 0) {
            return false;
        }
        return true;
    }
}
