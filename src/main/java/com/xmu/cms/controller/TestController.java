package com.xmu.cms.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xmu.cms.support.Token;
import com.xmu.cms.support.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/test")
public class TestController {
    private static final String SECRET = "JKKLJOoasdlfj";
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @GetMapping(value = "/testSession")
    public String testSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("userType") == null) {
            session.setAttribute("userType", "student");
            return "No user login";
        } else {
            return session.getAttribute("userType").toString();
        }

    }

    @GetMapping(value = "/getJWT")
    public UserInfo getJWT(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Token")) {
                String token = cookie.getValue();
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
        return null;
    }

    @GetMapping(value = "/createJWT")
    public void testJWT() {
        Token.setToken(new UserInfo(1, "admin"));
    }

    @GetMapping(value = "/getUserType")
    public Object getUserType(HttpSession session) {
        return session.getAttribute("userType");
    }

    @GetMapping(value = "/getUser")
    public Object getUserId(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.toString());
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }

    private static String UPLOADED_FOLDER = "D://temp//";

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("please select a file!", HttpStatus.OK);
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully uploaded - " +
                file.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/getSecurity")
    public Object getSecurity() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = securityContext.getAuthentication();
        return auth;
    }


}
