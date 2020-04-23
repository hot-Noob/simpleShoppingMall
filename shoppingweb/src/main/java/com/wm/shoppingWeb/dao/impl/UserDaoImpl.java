package com.wm.shoppingWeb.dao.impl;

import com.wm.shoppingWeb.dao.UserDao;
import com.wm.shoppingWeb.dao.mapper.UserMapper;
import com.wm.shoppingWeb.dao.model.UserDO;
import com.wm.shoppingWeb.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserLoginDao
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/5 9:39
 * @Version 1.0
 **/
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserMapper userMapper;

    /**
     * @Author 李鉴
     * @Description 通过用户id得到user表的信息
     * @Date 2020/4/6 17:40
     * @Param [id]
     * @Return com.wm.shoppingWeb.dao.model.UserDO
     * @Exception
     **/
    @Override
    public UserDTO getUserByID(int id) {
        UserDO userDO = userMapper.getUserById(id);
        return new UserDTO(userDO.getId(), userDO.getName(), userDO.getPassword());
    }
}
