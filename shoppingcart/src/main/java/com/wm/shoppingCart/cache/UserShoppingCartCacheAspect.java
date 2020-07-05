package com.wm.shoppingCart.cache;

import com.wm.shoppingCart.model.ShoppingCartCommodityVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *@ClassName UserShoppingCartCache
 *@Description 对购物车进行缓存，以用户id为主键，以商品id和属性组合为副键，进行存储，只有用户获取购物车时才会进行缓存，添加商品时若没有检测到缓存则不会主动进行缓存，若有则更新缓存。
 *@Author 李鉴
 *@Date 2020/4/15 17:08
 *@Version 1.0
 **/
@Aspect
@Component
@Order(2)
public class UserShoppingCartCacheAspect {
    private RedisTemplate<String, Object> objRedisTemplate;
    private HashOperations<String, String, ShoppingCartCommodityVO> hashOperations;
    private Map<String, ReentrantReadWriteLock> locks;
    private int TIME_OUT_SECONDS = 60 * 5;

    // 如果构造器里需要Bean，则需要在参数里填上，使用@Autowired注解是在构造器执行之后完成注入的。
    public UserShoppingCartCacheAspect(RedisTemplate<String, Object> objRedisTemplate) {
        this.objRedisTemplate = objRedisTemplate;
        hashOperations = objRedisTemplate.opsForHash();
        locks = new ConcurrentHashMap<>();
    }

    @Around("execution(public * com.wm.shoppingCart.service.UserShoppingCartImpl.getAllUserShoppingCartCommodity(int))")
    public Object aroundGetAllUserShoppingCartCommodity(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("对get方法进行拦截");
        Object[] argus = joinPoint.getArgs();
        String userId = String.valueOf(argus[0]);
        ReentrantReadWriteLock lock = locks.get(userId);
        if (lock == null) {
            lock = new ReentrantReadWriteLock();
            locks.put(userId, lock);
        }
        lock.readLock().lock();
        Map<String, ShoppingCartCommodityVO> commodityVOMap = hashOperations.entries(userId);
        ArrayList<ShoppingCartCommodityVO> result = null;
        if (commodityVOMap == null || commodityVOMap.size() <= 0) {
            result = (ArrayList<ShoppingCartCommodityVO>) joinPoint.proceed();
            if (result != null && result.size() > 0) {
                for (ShoppingCartCommodityVO commodityVO : result) {
                    hashOperations.put(userId, String.valueOf(commodityVO.getCommodityId()) + commodityVO.getProperty(), commodityVO);
                    objRedisTemplate.expire(userId, TIME_OUT_SECONDS, TimeUnit.SECONDS);
                }
            }
        } else {
            result = new ArrayList<>(commodityVOMap.values());
        }
        lock.readLock().unlock();
        return result;
    }

    @Around("execution(public * com.wm.shoppingCart.service.UserShoppingCartImpl.*(java.util.List<com.wm.shoppingCart.model.ShoppingCartCommodityVO>))")
    public Object aroundParamIsList(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("对参数为list方法进行拦截");
        ArrayList<ShoppingCartCommodityVO> commodityVOS = (ArrayList<ShoppingCartCommodityVO>) joinPoint.getArgs()[0];
        if (commodityVOS == null || commodityVOS.size() <= 0) {
            return false;
        }
        String userId = String.valueOf(commodityVOS.get(0).getUserId());
        ReentrantReadWriteLock lock = locks.get(userId);
        if (lock == null) {
            lock = new ReentrantReadWriteLock();
            locks.put(userId, lock);
        }
        lock.writeLock().lock();
        boolean success = (boolean) joinPoint.proceed();
        if (success) {
            Map<String, ShoppingCartCommodityVO> commodityVOMap = hashOperations.entries(userId);
            if (commodityVOMap != null && commodityVOMap.size() > 0) {
                for (ShoppingCartCommodityVO commodityVO :commodityVOS) {
                    ShoppingCartCommodityVO commodity = commodityVOMap.get(commodityVO.getCommodityId() + commodityVO.getProperty());
                    // 若没有则添加
                    if (commodity == null) {
                        hashOperations.put(userId, commodityVO.getCommodityId() + commodityVO.getProperty(), commodityVO);
                        continue;
                    }
                    //若有则更新缓存或者删除
                    int count = commodity.getCount() + commodityVO.getCount();
                    if (count <= 0) {
                        commodityVOMap.remove(commodityVO.getCommodityId() + commodityVO.getProperty());

                    } else {
                        commodity.setCount(count);
                    }
                }
            }
            objRedisTemplate.expire(userId, TIME_OUT_SECONDS, TimeUnit.SECONDS);
        }
        lock.writeLock().unlock();
        return success;
    }

    @Around("execution(public * com.wm.shoppingCart.service.UserShoppingCartImpl.*(com.wm.shoppingCart.model.ShoppingCartCommodityVO))")
    public Object aroundParamIsSingle(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("对参数为单个进行拦截");
        ShoppingCartCommodityVO commodityVO = (ShoppingCartCommodityVO) joinPoint.getArgs()[0];
        if (commodityVO == null) {
            return false;
        }
        String userId = String.valueOf(commodityVO.getUserId());
        
        boolean success = (boolean) joinPoint.proceed();
        if (success) {
            ReentrantReadWriteLock lock = locks.get(userId);
            if (lock == null) {
                lock = new ReentrantReadWriteLock();
                locks.put(userId, lock);
            }
            lock.writeLock().lock();
            Map<String, ShoppingCartCommodityVO> commodityVOMap = hashOperations.entries(userId);
            if (commodityVOMap != null && commodityVOMap.size() > 0) {
                ShoppingCartCommodityVO commodity = commodityVOMap.get(commodityVO.getCommodityId() + commodityVO.getProperty());
                if (commodity == null) {
                    System.out.println("添加成功");
                    hashOperations.put(userId, commodityVO.getCommodityId() + commodityVO.getProperty(), commodityVO);
                } else {
                    int count = commodity.getCount() + commodityVO.getCount();
                    if (count <= 0) {
                        System.out.println("更新成功");
                        commodityVOMap.remove(commodityVO.getCommodityId() + commodityVO.getProperty());
                    } else {
                        System.out.println("删除成功");
                        commodity.setCount(count);
                    }
                }
            }
            objRedisTemplate.expire(userId, TIME_OUT_SECONDS, TimeUnit.SECONDS);
            lock.writeLock().unlock();
        }

        return success;
    }
}
