package xyz.qcbyt.shiro.Filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.qcbyt.utils.JwtUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * shiro的 token过滤器， 和判断是否是ajax请求
 */
@Component
public class ClientShiroThree extends AuthenticatingFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse response1) throws Exception {
        HttpServletResponse response = (HttpServletResponse) response1;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String ajax = request.getHeader("x-requested-with");
        if (null == ajax) {
            System.out.println("=====不是ajax");
            response.sendRedirect("/login");
        } else {
            System.out.println("=====是ajax" + ajax);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("访问有问题");
        }
        return false;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return null;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse response, Object mappedValue) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("token");
        System.out.println("================"+token);
        if (null == token||"".equals(token)) {
            System.out.println("-------------------token为空");
            return false;
        }
        //验证token的真实性
        try {
            JwtUtils.verify(token);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("----------------token有问题");
            return false;
        }
        return true;
    }

    //config 加了跨域之后，shiro 也要加跨域处理
    @Override
    protected boolean preHandle(ServletRequest request,ServletResponse response)throws Exception{
        HttpServletRequest httpServletRequest= WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse=WebUtils.toHttp(response);
        httpServletResponse.setHeader("Access-control-Allow-Origin",httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods","GET,POST,POTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers",httpServletRequest.getHeader("Access-Control-Request-Headers"));
        if(httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){
            httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request,response);
    }
}