package com.wm.shoppingCart.dao.mapper;

import com.wm.shoppingCart.model.ShoppingCartCommodityDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName UserShoppingCartMapper
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/10 15:24
 * @Version 1.0
 **/
@Mapper
@Component
public interface UserShoppingCartMapper {
    @Select("SELECT * FROM shopping_cart WHERE user_id = #{userId}")
    List<ShoppingCartCommodityDO> getCommoditiesByUserId(@Param("userId") int userId);

    @Insert("INSERT INTO shopping_cart(user_id,commodity_id,create_time,property,count,commodity_describe,per_price,picture)" +
            " VALUES (#{commodityDO.userId},#{commodityDO.commodityId},NOW(),#{commodityDO.property},#{commodityDO.count},#{commodityDO.commodityDescribe},#{commodityDO.perPrice},#{commodityDO.picture})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(@Param("commodityDO") ShoppingCartCommodityDO commodityDO);

    @Insert("<script>" +
            "INSERT INTO shopping_cart(user_id,commodity_id,create_time,property,count,commodity_describe,per_price,picture)" +
            "VALUES <foreach collection='list' item='commodityDO'  separator=','>" +
            "(#{commodityDO.userId},#{commodityDO.commodityId},NOW(),#{commodityDO.property},#{commodityDO.count},#{commodityDO.commodityDescribe},#{commodityDO.perPrice},#{commodityDO.picture})" +
            " </foreach>" +
            "</script>")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertList(List<ShoppingCartCommodityDO> commodityDOS);

    @Update("UPDATE shopping_cart SET count = #{commodityDO.count} WHERE user_id = #{commodityDO.userId} AND commodity_id = #{commodityDO.commodityId} AND property = #{commodityDO.property}")
    boolean updateCount(@Param("commodityDO") ShoppingCartCommodityDO commodityDO);

    @Update({"<script>" +
            "<foreach collection='list' item='commodityDO'  separator=';'>" +
            "UPDATE shopping_cart SET count = #{commodityDO.count} WHERE user_id = #{commodityDO.userId} AND commodity_id = #{commodityDO.commodityId} AND property = #{commodityDO.property}" +
            "</foreach>" +
            "</script>"})
    @Options()
    boolean updateListCount(List<ShoppingCartCommodityDO> commodityDOS);

    @Delete("DELETE FROM shopping_cart WHERE user_id = #{commodityDO.userId} AND commodity_id = #{commodityDO.commodityId} AND property = #{commodityDO.property}")
    boolean delete(@Param("commodityDO") ShoppingCartCommodityDO commodityDO);

    @Delete("<script>" +
            "<foreach collection='list' item='commodityDO'  separator=';'>" +
            "DELETE FROM shopping_cart WHERE user_id = #{commodityDO.userId} AND commodity_id = #{commodityDO.commodityId} AND property = #{commodityDO.property}" +
            " </foreach>" +
            "</script>")
    boolean deleteList(List<ShoppingCartCommodityDO> commodityDOS);
}
