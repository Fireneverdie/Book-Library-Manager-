package com.firewood.interceptor;

import com.firewood.context.BaseContext;
import com.firewood.properties.JwtProperties;
import com.firewood.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //解析token
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        //校验令牌
        try {
            log.info("jwt令牌校验:{}",token);
            Claims claims = JwtUtils.parseJWT(jwtProperties.getAdminSecretKey(),token);
//            Long userId = Long.valueOf(claims.get("id").toString());
            Integer userId = claims.get("id", Integer.class);
//            Long userId = (Long)claims.get("id");
            log.info("当前的用户id为：{}",userId);
            //放行，获取用户id存储到threadLocal里
            BaseContext.setCurrentId(userId);
            return true;
        }catch (Exception e){
            //错误解析，抛出异常不放行
            response.setStatus(401);
            return false;
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContext.removeCurrentId();
    }
}
