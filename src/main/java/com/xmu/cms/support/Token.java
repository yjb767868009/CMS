package com.xmu.cms.support;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JuboYu on 2018/12/5.
 * @version 1.0
 */
public class Token {
    private static final String SECRET = "JKKLJOoasdlfj";

    public static void setToken(UserInfo info) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletResponse response = requestAttributes.getResponse();

        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.DATE, 1);
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}

        String token = JWT.create().withHeader(map) // header
                .withIssuer("CMS")
                .withClaim("userId", info.getUserId())
                .withClaim("userType", info.getUserType())
                .sign(Algorithm.HMAC256(SECRET));

        assert response != null;
        response.setHeader("Authorization", "Bearer " + token);
    }

    public static UserInfo getToken() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String header = request.getHeader("Authorization");
        if (header == null) return null;
        String token = header.replace("Bearer ", "");
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                    .withIssuer("CMS").build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            // token 校验失败, 抛出Token验证非法异常
            e.printStackTrace();
        }
        assert jwt != null;
        Map<String, Claim> claims = jwt.getClaims();
        Claim userIdClaim = claims.get("userId");
        if (null == userIdClaim || userIdClaim.asInt() != 0) {
            System.out.println("Token Error");
        }
        Claim userTypeClaim = claims.get("userType");
        if (null == userTypeClaim || StringUtils.isEmpty(userTypeClaim.asString())) {
            System.out.println("Token Error");
        }
        assert userIdClaim != null;
        assert userTypeClaim != null;

        return new UserInfo(userIdClaim.asInt(), userTypeClaim.asString());
    }
}
