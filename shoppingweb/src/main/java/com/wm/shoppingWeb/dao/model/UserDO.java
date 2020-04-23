package com.wm.shoppingWeb.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserDO
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/5 10:23
 * @Version 1.0
 **/
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDO {
    private int id;
    private String name;
    private String password;
}
