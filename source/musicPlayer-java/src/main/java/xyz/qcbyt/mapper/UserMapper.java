package xyz.qcbyt.mapper;

import org.apache.ibatis.annotations.*;
import xyz.qcbyt.entity.User;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {
    //注册用户
    @Insert("insert into user (username,password,email,registertime) values(#{username},#{password},#{email},#{registertime}) ")
    Integer register(User user);
    //注册判断用户名是否重复
    @Select("select * from user where username=#{username}")
    User registerUnique(String username);

    //用户登录
    @Select("select * from user where username=#{username} and password=#{password}")
    User userlogin( User user);

    //查询所有用户
    @Select("select * from user")
    List<User> findAllUser();

    //根据一个条件查询一个用户
    User findOneUser(Integer id,String username,Integer role,String registertime);

    //登录的时候更新 用户最近一次的登录时间
    @Update("update user set totallogin=#{totallogin},recentlogin=#{recentlogin} where id=#{id}")
    Integer updateRecentLogin(Integer id,Integer totallogin,Date recentlogin);

    
}
