package com.wm.shoppingWeb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wm.shoppingWeb.dao.mapper")
public class ShoppingWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingWebApplication.class, args);
    }

}
