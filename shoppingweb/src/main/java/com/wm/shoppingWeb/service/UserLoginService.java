package com.wm.shoppingWeb.service;

import com.wm.shoppingWeb.controller.model.UserVO;

/**
 * @ClassName UserLoginService
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/5 9:43
 * @Version 1.0
 **/
public interface UserLoginService {
    UserVO userLogin(int id, String password);
}
