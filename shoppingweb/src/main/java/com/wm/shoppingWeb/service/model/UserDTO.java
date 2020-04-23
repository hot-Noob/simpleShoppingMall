package com.wm.shoppingWeb.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserDTO
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/5 10:30
 * @Version 1.0
 **/
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int userId;
    private String name;
    private String password;
}
