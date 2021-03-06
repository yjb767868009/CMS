package com.xmu.cms.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmu.cms.support.JwtUtils;
import com.xmu.cms.support.UserInfo;
import com.xmu.cms.support.UsernameIsExitedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/12/12.
 * @version 1.0
 */
@Component
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    @Autowired
    private ObjectMapper objectMapper;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * 在此方法中检验客户端请求头中的token,
     * 如果存在并合法,就把token中的信息封装到 Authentication 类型的对象中,
     * 最后使用  SecurityContextHolder.getContext().setAuthentication(authentication); 改变或删除当前已经验证的 pricipal
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtType = "Authorization";
        String jwtHead = "Bearer ";
        String token = request.getHeader(jwtType);

        //判断是否有token
        if (token == null || !token.startsWith(jwtHead)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (Exception ignored) {

        }

        //放行
        chain.doFilter(request, response);
    }

    /**
     * 解析token中的信息,并判断是否过期
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        UserInfo info = JwtUtils.getToken();
        if (null == info) {
            throw new UsernameIsExitedException("失效的令牌");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        String userType = info.getUserType();
        switch (userType) {
            case "admin":
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                break;
            case "teacher":
                authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
                break;
            case "student":
                authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
                break;
            default:
                break;
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(info.getAccount(), null, authorities);
        authenticationToken.setDetails(info);
        return authenticationToken;
    }

}
