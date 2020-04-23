package com.wm.shoppingWeb.controller;

import com.wm.shoppingWeb.controller.model.UserVO;
import com.wm.shoppingWeb.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

import static com.wm.shoppingWeb.controller.UserConstant.USER_ID;
import static com.wm.shoppingWeb.controller.UserConstant.USER_VO;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/5 9:36
 * @Version 1.0
 **/
@Controller
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;
    private SessionContext sessionContext = SessionContext.getInstance();

    /**
     * @Author 李鉴
     * @Description //TODO
     * @Date 2020/4/6 17:39
     * @Param [id, password]
     * @Return com.wm.shoppingWeb.controller.model.UserVO
     * @Exception 
     **/
    @RequestMapping("/login")
    @ResponseBody
    public UserVO login(int id, String password, HttpSession session) {
        if (id <= 0 || password == null || password.length() <= 0) {
            return null;
        }
        UserVO userVO;
        if (session != null && (userVO = (UserVO) session.getAttribute(USER_VO)) != null) {
            return userVO;
        }
        userVO = userLoginService.userLogin(id, password);
        if (userVO != null) {
            session.setAttribute(USER_ID, userVO.getUserId());
            session.setAttribute(USER_VO, userVO);
            sessionContext.addSession(session);
        }
        return userVO;
    }
}
