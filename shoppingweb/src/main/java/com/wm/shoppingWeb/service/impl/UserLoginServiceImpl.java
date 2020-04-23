package com.wm.shoppingWeb.service.impl;

import com.wm.shoppingWeb.controller.model.UserVO;
import com.wm.shoppingWeb.dao.UserDao;
import com.wm.shoppingWeb.service.UserLoginService;
import com.wm.shoppingWeb.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserLoginService
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/5 9:40
 * @Version 1.0
 **/
@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserDao userDao;

    /**
     * @Author 李鉴
     * @Description 判断用户账号密码是否正确，如果正确保存session并返回用户基本信息
     * @Date 2020/4/6 17:41
     * @Param [id, password]
     * @Return com.wm.shoppingWeb.controller.model.UserVO
     * @Exception
     **/
    @Override
    public UserVO userLogin(int userId, String password) {
        UserDTO userDTO = userDao.getUserByID(userId);
        if (userDTO == null || userDTO.getUserId() != userId) {
            return null;
        }
        if (!userDTO.getPassword().equals(password)) {
            return null;
        }
        return new UserVO(userId, userDTO.getName());
    }
}
