package com.wm.shoppingWeb.dao;

import com.wm.shoppingWeb.service.model.UserDTO;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/5 10:21
 * @Version 1.0
 **/
public interface UserDao {
    UserDTO getUserByID(int id);
}
