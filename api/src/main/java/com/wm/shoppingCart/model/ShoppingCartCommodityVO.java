package com.wm.shoppingCart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

/**
 * @ClassName ShoppingCartCommodityVO
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/7 16:42
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartCommodityVO implements Serializable{
    int userId;
    int commodityId;
    Timestamp createTime;
    String property;
    int count;
    String commodityDescribe;
    BigDecimal perPrice;
    byte[] picture;

    public ShoppingCartCommodityVO(ShoppingCartCommodityDO commodityDO) {
        userId = commodityDO.getUserId();
        commodityId = commodityDO.getCommodityId();
        createTime = commodityDO.getCreateTime();
        property = commodityDO.getProperty();
        count = commodityDO.getCount();
        commodityDescribe = commodityDO.getCommodityDescribe();
        perPrice = commodityDO.getPerPrice();
        picture = commodityDO.getPicture();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartCommodityVO that = (ShoppingCartCommodityVO) o;
        return userId == that.userId &&
                commodityId == that.commodityId &&
                Objects.equals(property, that.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, commodityId, property);
    }

    @Override
    public String toString() {
        return "ShoppingCartCommodityVO{" +
                "userId=" + userId +
                ", commodityId=" + commodityId +
                ", createTime=" + (createTime == null ? null : createTime.toLocalDateTime()) +
                ", property='" + property + '\'' +
                ", count=" + count +
                ", commodityDescribe='" + commodityDescribe + '\'' +
                ", perPrice=" + perPrice +
                ", picture=" + Arrays.toString(picture) +
                '}';
    }

}
