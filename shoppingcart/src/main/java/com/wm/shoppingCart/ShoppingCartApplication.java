package com.wm.shoppingCart;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.wm.shoppingCart.dao.impl.UserShoppingCartDaoImpl;
import com.wm.shoppingCart.dao.mapper.UserShoppingCartMapper;
import com.wm.shoppingCart.model.ShoppingCartCommodityDO;
import com.wm.shoppingCart.service.UserShoppingCart;
import com.wm.shoppingCart.service.UserShoppingCartImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ShoppingCartApplication
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/4 17:22
 * @Version 1.0
 **/

@SpringBootApplication
@EnableTransactionManagement
@ImportResource(locations = "provider.xml")
@EnableDubbo(scanBasePackages = "com.wm.shoppingCart.service")
@MapperScan("com.wm.shoppingCart.dao.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ShoppingCartApplication extends ApplicationObjectSupport {
    private static ApplicationContext context;
    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);
//        test();
    }

    private static  void test() {
        UserShoppingCartImpl userShoppingCart = (UserShoppingCartImpl) context.getBean(UserShoppingCart.class);
        System.out.println(userShoppingCart.getAllUserShoppingCartCommodity(799531106));
    }

    /**
     * @Author 李鉴
     * @Description 通过ApplicationContext来获取bean
     * @Date 2020/4/13 17:59
     * @Param [context]
     * @Return void
     * @Exception
     **/
    @Override
    protected void initApplicationContext(ApplicationContext context) throws BeansException {
        super.initApplicationContext(context);
        ShoppingCartApplication.context = context;
    }
}
