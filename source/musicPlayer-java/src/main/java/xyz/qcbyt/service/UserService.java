package xyz.qcbyt.service;

import xyz.qcbyt.entity.User;
import xyz.qcbyt.utils.Result;

import java.util.Date;
import java.util.List;

public interface UserService {

    Integer register(User user);

    User registerUnique(String username);

    Result userlogin(User user);

    List<User> findAllUser();

    //根据一个条件查询一个用户
    User findOneUser( Integer id,String username,Integer role,String registertime);

    //登录的时候更新用户最近一次登录时间
    Integer updateTotalLoginDay(Integer id,Integer totallogin,Date recentlogin);
}
