package com.electronicform.hei.security;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.electronicform.hei.controller.AppUserController;
import com.electronicform.hei.security.jwt.JwtService;

public class SecurityConfig extends OncePerRequestFilter {

    private JwtService jwt;

    public SecurityConfig(JwtService jwt){
        this.jwt = jwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getMethod().matches(HttpMethod.OPTIONS.name())) {
            filterChain.doFilter(request, response);
        } else {
            var authorization = request.getHeader("authorization");
            if(authorization == null) response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
            else {
                try{
                    var token = authorization.split("Bearer ")[1];
                    var decoded = jwt.verify(token);
                    request.setAttribute("id", Long.parseLong(decoded.getClaim("id").asString()));
                    filterChain.doFilter(request, response);
                }catch(Exception e){ response.sendError(HttpStatus.METHOD_NOT_ALLOWED.value(), "Invalid Token"); }
            }
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        var matcher = new AntPathMatcher();
        System.out.println(request.getRequestURI());
        if(matcher.match("/api-docs/**", request.getRequestURI()) || matcher.match("/swagger-ui/**", request.getRequestURI())) return true;
        var test = new AtomicBoolean(false);
        AppUserController.AUTHORIZED_ROUTE.forEach((k, v) -> {
            test.set(test.get() || (k.equals(request.getRequestURI()) && v.matches(request.getMethod())));
        });
        return test.get();
    }
    
}
