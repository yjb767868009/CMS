package com.xmu.cms.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmu.cms.support.LoginType;
import com.xmu.cms.support.SecurityProperties;
import com.xmu.cms.support.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JuboYu on 2018/12/3.
 * @version 1.0
 */
@Component("AuthenticationSuccessHandler")
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        if (LoginType.JSON.equals(securityProperties.getLoginType())) {
            //System.out.println(authentication);
            UserInfo info = (UserInfo) authentication.getDetails();
            Map<String, String> message = new HashMap<String, String>();
            message.put("id", info.getUserId().toString());
            message.put("account", info.getAccount());
            message.put("role", info.getUserType());
            Boolean active = info.getActive();
            if (active != null) {
                message.put("active", info.getActive().toString());
            }
            response.getWriter().write(objectMapper.writeValueAsString(message));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
