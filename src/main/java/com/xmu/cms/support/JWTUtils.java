package com.xmu.cms.support;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JuboYu on 2018/12/5.
 * @version 1.0
 */
public class JWTUtils {
    private static final String SECRET = "JKKLJOoasdlfj";

    public static String setToken(UserInfo info) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletResponse response = requestAttributes.getResponse();

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}
        String userId = info.getUserId().toString();

        String token = JWT.create().withHeader(map) // header
                .withIssuer("CMS")
                .withExpiresAt(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 100))
                .withClaim("userId", userId)
                .withClaim("userType", info.getUserType())
                .withClaim("account", info.getAccount())
                .withClaim("name", info.getName())
                .sign(Algorithm.HMAC256(SECRET));

        assert response != null;
        response.setHeader("Authorization", "Bearer " + token);
        return token;
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
            throw new UsernameIsExitedException("无效的令牌");
        }

        Map<String, Claim> claims = jwt.getClaims();
        Claim userIdClaim = claims.get("userId");
        BigInteger userId = null;
        if (null == userIdClaim || StringUtils.isEmpty(userIdClaim.asString())) {
            throw new UsernameIsExitedException("无效的令牌");
        } else {
            userId = new BigInteger(userIdClaim.asString());
        }

        Claim userTypeClaim = claims.get("userType");
        String userType = null;
        if (null == userTypeClaim || StringUtils.isEmpty(userTypeClaim.asString())) {
            throw new UsernameIsExitedException("无效的令牌");
        } else {
            userType = userTypeClaim.asString();
        }

        Claim accountClaim = claims.get("account");
        String account = null;
        if (null == accountClaim || StringUtils.isEmpty(accountClaim.asString())) {
        } else {
            account = accountClaim.asString();
        }

        Claim nameClaim = claims.get("name");
        String name = null;
        if (null == nameClaim || StringUtils.isEmpty(nameClaim.asString())) {
        } else {
            name = nameClaim.asString();
        }

        return new UserInfo(userId, account, userType, name);
    }
}
