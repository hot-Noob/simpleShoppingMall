package com.wm.shoppingCart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @ClassName ShoppingCartCommodityDO
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/7 10:43
 * @Version 1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartCommodityDO {
    int id;
    int userId;
    int commodityId;
    Timestamp createTime;
    String property;
    int count;
    String commodityDescribe;
    BigDecimal perPrice;
    byte[] picture;

    public ShoppingCartCommodityDO(ShoppingCartCommodityVO commodityVO) {
        userId = commodityVO.getUserId();
        commodityId = commodityVO.getCommodityId();
        createTime = commodityVO.getCreateTime();
        property = commodityVO.getProperty();
        count = commodityVO.getCount();
        commodityDescribe = commodityVO.getCommodityDescribe();
        perPrice = commodityVO.getPerPrice();
        picture = commodityVO.getPicture();
    }

}
