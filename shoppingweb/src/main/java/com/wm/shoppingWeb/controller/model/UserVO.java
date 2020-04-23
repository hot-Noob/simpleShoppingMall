package com.wm.shoppingWeb.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserVO
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/5 9:38
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private int userId;
    private String name;
}
