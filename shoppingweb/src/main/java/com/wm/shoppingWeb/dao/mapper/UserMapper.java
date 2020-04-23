package com.wm.shoppingWeb.dao.mapper;

import com.wm.shoppingWeb.dao.model.UserDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/5 10:01
 * @Version 1.0
 **/
@Mapper
@Component
public interface UserMapper {
    @Select("SELECT id,name,password FROM user WHERE id = #{id}")
    UserDO getUserById(@Param("id") int id);

    @Insert("insert into USER(NAME, SEX, AGE) values(#{name}, #{sex}, #{age})")
    boolean insert(UserDO user);

    @Update("update USER set SEX=#{sex}, AGE=#{age} where NAME=#{name}")
    boolean update(@Param("name") String name, @Param("sex") String sex, @Param("age") int age);

    @Delete("delete from USER where NAME = #{name}")
    void delete(@Param("name") String name);
}
