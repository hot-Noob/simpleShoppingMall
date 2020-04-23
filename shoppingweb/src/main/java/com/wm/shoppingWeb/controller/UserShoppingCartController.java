package com.wm.shoppingWeb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.reflect.TypeToken;
import com.wm.shoppingCart.model.ShoppingCartCommodityDO;
import com.wm.shoppingCart.model.ShoppingCartCommodityVO;
import com.wm.shoppingCart.service.UserShoppingCart;
import com.wm.shoppingWeb.util.gson.GsonUtil;
import com.wm.shoppingWeb.util.multiRequestBody.MultiRequestBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserShoppingCartController
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/7 11:18
 * @Version 1.0
 **/
@Controller
public class UserShoppingCartController {
    @Reference
    private UserShoppingCart userShoppingCart;
    private static final Type USER_SHOPPING_CART_COMMODITY_VO_LINKED_LIST_TYPE = new TypeToken<ArrayList<ShoppingCartCommodityVO>>() {}.getType();
    private static final String  COOKIE_SHOPPING_CART = "shoppingCartCommodities";
    private SessionContext sessionContext = SessionContext.getInstance();
    private static final int COOKIE_MAX_LIVE_TIME = 60 * 60;

    /**
     * @Author 李鉴
     * @Description  通过sessionn判断用户是否登录，如果未登录则将商品添加到cookie里，如果登录调用购物车的RPC方法添加到用户的购物车表里
     * 这里我使用的是GSON序列化工具，因为GSON相对比其它序列化工具跟简单更全面，但是性能可能会降低。
     * @Date 2020/4/8 8:30
     * @Param 前段需要传过来要添加的对象
     * @Return true表示添加成功，false表示添加失败
     * @Exception
     **/
    @ResponseBody
    @PostMapping(value = "/addCommodityToUserShoppingCart")
    public boolean addCommodityToUserShoppingCart(@MultiRequestBody ShoppingCartCommodityVO commodity,
                                                  HttpServletResponse response,
                                                  @CookieValue(value = COOKIE_SHOPPING_CART, required = false) Cookie cookieShoppingCart) {
        if (commodity == null || commodity.getUserId() <= 0 || commodity.getCommodityId() <= 0) {
            return false;
        }
        // 通过session判断用户是否登录
        HttpSession session = sessionContext.getSession(commodity.getUserId());
        // 如果session不为null则在sessionContext里注册过则证明用户处于登录状态，调用RPC方法添加到购物车里
        if (session != null) {
            return userShoppingCart.addCommodityToUserShoppingCart(commodity);
        }
        // 用户未登录，保存购物车在会话cookie里
        try {
            if (cookieShoppingCart == null) {
                ArrayList<ShoppingCartCommodityVO> commodityVOS = new ArrayList<>();
                commodityVOS.add(commodity);
                Cookie cookie = new Cookie(COOKIE_SHOPPING_CART, URLEncoder.encode(GsonUtil.getGson().toJson(commodityVOS), "utf-8"));
                cookie.setMaxAge(COOKIE_MAX_LIVE_TIME);
                response.addCookie(cookie);
            } else {
                ArrayList<ShoppingCartCommodityVO> commodityVOS = GsonUtil.getGson().fromJson(URLDecoder.decode(cookieShoppingCart.getValue(), "utf-8"),
                        USER_SHOPPING_CART_COMMODITY_VO_LINKED_LIST_TYPE);
                for (ShoppingCartCommodityVO commodityVO : commodityVOS) {
                    System.out.println(commodityVO.getCount());
                    if (commodity.equals(commodityVO)) {
                        commodityVO.setCount(commodity.getCount() + commodityVO.getCount());
                        cookieShoppingCart.setValue(URLEncoder.encode(GsonUtil.getGson().toJson(commodityVOS), "utf-8"));
                        response.addCookie(cookieShoppingCart);
                        return true;
                    }
                }
                commodityVOS.add(commodity);
                cookieShoppingCart.setValue(URLEncoder.encode(GsonUtil.getGson().toJson(commodityVOS), "utf-8"));
                response.addCookie(cookieShoppingCart);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @ResponseBody
    @RequestMapping("/getAllUserShoppingCartCommodity")
    public List<ShoppingCartCommodityVO> getAllUserShoppingCartCommodity(int userId,
                                                                         @CookieValue(value = COOKIE_SHOPPING_CART, required = false) Cookie cookieShoppingCart) {
        if (userId <= 0) {
            return null;
        }
        if (cookieShoppingCart != null) {
            System.out.println(cookieShoppingCart);
            try {
                ArrayList<ShoppingCartCommodityVO> commodityVOS = GsonUtil.getGson().fromJson(URLDecoder.decode(cookieShoppingCart.getValue(), "utf-8"),
                        USER_SHOPPING_CART_COMMODITY_VO_LINKED_LIST_TYPE);
                if (sessionContext.getSession(userId) == null) {
                    return commodityVOS;
                }
                userShoppingCart.addCommodityToUserShoppingCart(commodityVOS);
                cookieShoppingCart.setMaxAge(0);
            } catch (UnsupportedEncodingException e) {
                // 记录就可以
                e.printStackTrace();
            }
        }
        if (sessionContext.getSession(userId) == null) {
            return null;
        }
        List<ShoppingCartCommodityVO> result = userShoppingCart.getAllUserShoppingCartCommodity(userId);
        return result;
    }


    @ResponseBody
    @PostMapping("/manageUserShoppingCart")
    public boolean manageUserShoppingCart(@RequestBody ArrayList<ShoppingCartCommodityVO> commodities){
        if (commodities == null || commodities.size() <= 0) {
            return false;
        }
        System.out.println(commodities);
        return userShoppingCart.manageUserShoppingCart(commodities);
     }
}
