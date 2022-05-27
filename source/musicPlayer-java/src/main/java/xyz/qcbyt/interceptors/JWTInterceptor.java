package xyz.qcbyt.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.qcbyt.utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {
    //拦截接口的请求
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头的token
        String token = request.getHeader("token");
        Map<String,Object> map =new HashMap<>();
        try {
            JwtUtils.verify(token);
            return true;//验证成功请求放行
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg","签名不一致");
        }catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg","token失效");
        }catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg","算法不一致");
        }catch (InvalidClaimException e) {
            e.printStackTrace();
            map.put("msg","payload失效");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("null值-----");
            map.put("msg","无token,非法的访问");
        }
        map.put("state",false);//设置状态
        //将map转为json
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
    /**
     * 注意！！！！！写完token拦截器后，需要再写一个拦截器配置类
     */
}
