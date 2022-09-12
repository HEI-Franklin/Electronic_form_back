package com.electronicform.hei.security.jwt;

import java.util.HashMap;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.electronicform.hei.model.AppUser;
import com.electronicform.hei.security.SecurityConfig;

@Service
public class JwtService {
    private Algorithm algorithm;

    public JwtService(Environment env){ 
        this.algorithm = Algorithm.HMAC256(env.getProperty("secret.key")); 
    }

    public String sign(AppUser u){
        var payload = new HashMap<String, Object>();
        payload.put("username", u.getUsername());
        payload.put("email", u.getEmail());
        payload.put("id", u.getId() + "");
        return JWT.create()
                .withPayload(payload)
                .sign(algorithm);
    }

    public DecodedJWT verify(String token){
        try{
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        }catch(Exception e){ return null; }
    }

    @Bean
    public FilterRegistrationBean<SecurityConfig> loggingFilter(){
        FilterRegistrationBean<SecurityConfig> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SecurityConfig(this));
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
