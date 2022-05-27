package xyz.qcbyt.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.qcbyt.service.UserService;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService service;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从token中获取信息,此token只是shiro用于身份验证的,并非前端传过来的token.
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        String username=token.getUsername();
        String password=service.registerUnique(username).getPassword();

        if(null==password){
            throw new AuthenticationException("doGetAuthenticationInfo中的用户名不对");
        }else if(!password.equals(new String(token.getPassword()))){
            throw new AuthenticationException("doGetAuthenticationInfo中的密码不对");
        }
        //组合一个验证信息
        System.out.println("token.getPrincipal()默认返回的username======"+token.getPrincipal());
        System.out.println("getName()"+getName());

        return new SimpleAuthenticationInfo(token.getPrincipal(),password,username);
    }
}
