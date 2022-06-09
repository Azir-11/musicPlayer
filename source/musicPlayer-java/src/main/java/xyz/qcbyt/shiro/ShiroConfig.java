package xyz.qcbyt.shiro;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import xyz.qcbyt.shiro.Filter.ClientShiroThree;
import xyz.qcbyt.shiro.Filter.LoginFilter;
import xyz.qcbyt.shiro.realm.UserRealm;
//import xyz.qcbyt.shiro.realm.UserRealm;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig  {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter=new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilter.setSecurityManager(securityManager);
        //解决shiro 无权限默认跳转 login.jsp 的问题
        Map<String, Filter> filter = shiroFilter.getFilters();
        filter.put("authc",new LoginFilter());
        filter.put("client",new ClientShiroThree());
        shiroFilter.setFilters(filter);
        //添加shiro 内置的过滤器
        /**
         * anon:无需认证即可访问
         * authc: 必须认证才能访问
         * user:必须拥有 记住我 才能访问
         * perms :拥有某个资源的权限才能访问
         * role: 拥有某个权限才能访问
         */
        Map<String,String> filters=new LinkedHashMap<>();
        filters.put("/reg/**","anon");
        filters.put("/reg/loginout","authc");
        filters.put("/static/**","anon");
        shiroFilter.setFilterChainDefinitionMap(filters);
        return shiroFilter;
    }


    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    /**
     * 以下三个Bean是解决Shiro注解不生效问题，官方给出，不要问为什么
     * @return
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public static DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;

    }


}
