package xyz.qcbyt.utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Map;

@Component
public class JwtUtils {
    private static final String SIGN="mqc666";
    /**
     * 生成token
     * @param //传入payload
     * @return //返回token
     */
    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();

        instance.add(Calendar.DATE,1);//默认5天过期

        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token= builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));
        return  token;
    }
    /**
     * 验证token
     * 失败抛异常，成功就返回token
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }
}
