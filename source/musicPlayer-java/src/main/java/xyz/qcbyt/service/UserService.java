package xyz.qcbyt.service;

import xyz.qcbyt.entity.User;
import xyz.qcbyt.utils.Result;

import java.util.List;

public interface UserService {

    Integer register(User user);

    User registerUnique(String username);

    Result userlogin(User user);

    List<User> findAllUser();
}
