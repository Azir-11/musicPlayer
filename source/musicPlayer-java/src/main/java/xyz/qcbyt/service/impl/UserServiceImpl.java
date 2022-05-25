package xyz.qcbyt.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.qcbyt.mapper.UserMapper;
import xyz.qcbyt.entity.User;
import xyz.qcbyt.service.UserService;
import xyz.qcbyt.utils.JwtUtils;
import xyz.qcbyt.utils.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer register(User user) {
        User user1 = userMapper.registerUnique(user.getUsername());
        if(user1==null){
            return userMapper.register(user);
        }
        return null;
    }

    @Override
    public User registerUnique(String username) {
        return userMapper.registerUnique(username);
    }

    @Override
    public Result userlogin(User user) {
        User userlogin = userMapper.userlogin(user);
        if(userlogin!=null){
            try {
                Subject subject= SecurityUtils.getSubject();
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
                subject.login(usernamePasswordToken);
                Map<String,String> payload=new HashMap<>();
                payload.put("id",userlogin.getId().toString());

                payload.put("username",userlogin.getUsername());
                String token = JwtUtils.getToken(payload);
                Result result=new Result();
                Map<String,Object> map=new HashMap<>();
                map.put("token",token);
                map.put("id",userlogin.getId());
                map.put("username",userlogin.getUsername());
                map.put("role",userlogin.getRole());
                result.setData(map);

                return result;
            }catch (Exception e){
                e.printStackTrace();

            }
        }

        return null;
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }


}
