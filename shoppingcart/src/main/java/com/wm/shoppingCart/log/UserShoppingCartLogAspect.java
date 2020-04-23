package com.wm.shoppingCart.log;

import com.wm.shoppingCart.service.UserShoppingCartImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserShoppingCartLogAspect
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/19 16:02
 * @Version 1.0
 **/
@Slf4j
@Aspect
@Component
@Order(1)
public class UserShoppingCartLogAspect {
    Logger logger = LoggerFactory.getLogger(UserShoppingCartImpl.class);

    @Pointcut("execution(public * com.wm.shoppingCart.service.UserShoppingCartImpl.*(..))")
    public void UserShoppingCartLog(){}

    @AfterReturning(value = "UserShoppingCartLog()", returning = "result")
    public void doAfterAdvice(JoinPoint joinPoint, Object result){
        logger.info("购物车成功执行" + joinPoint.getSignature().getName() + " " + result);
    }

    @AfterThrowing(pointcut = "UserShoppingCartLog()",throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
        //目标方法名：
        logger.error("在" + joinPoint.getSignature().getName() + "发生了" + exception);
    }
}
