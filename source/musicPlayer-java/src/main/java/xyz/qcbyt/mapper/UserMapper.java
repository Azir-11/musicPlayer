package xyz.qcbyt.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.qcbyt.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    //注册用户
    @Insert("insert into user (username,password,email) values(#{username},#{password},#{email}) ")
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


}
