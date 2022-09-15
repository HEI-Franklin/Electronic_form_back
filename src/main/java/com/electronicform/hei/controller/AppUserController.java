package com.electronicform.hei.controller;

import java.util.HashMap;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.electronicform.hei.model.AppUser;
import com.electronicform.hei.model.dto.AppUserDto.LoginAppUserDto;
import com.electronicform.hei.model.dto.AppUserDto.MeDto;
import com.electronicform.hei.model.dto.AppUserDto.TokenDto;
import com.electronicform.hei.model.mapper.MeMapper;
import com.electronicform.hei.service.AppUserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AppUserController {

    public static final HashMap<String, HttpMethod> AUTHORIZED_ROUTE = new HashMap<>();
    static {
        AUTHORIZED_ROUTE.put("/user/create", HttpMethod.POST);
        AUTHORIZED_ROUTE.put("/user/login", HttpMethod.POST);
        AUTHORIZED_ROUTE.put("/", HttpMethod.GET);
    }

    private final AppUserService appUserService;
    private final MeMapper meMapper;

    @GetMapping()
    public String hello() {
        return "<h1>Welcome to Electronic Form API</h1>";
    }

    // Endpoint to create User
    @PostMapping("/user/create")
    public ResponseEntity<AppUser> createUser(@RequestBody() AppUser user) throws ResponseStatusException {
        return appUserService.createUser(user);
    }

    // Endpoint to login
    @PostMapping("/user/login")
    public TokenDto login(@RequestBody() LoginAppUserDto user) throws ResponseStatusException {
        return new TokenDto(appUserService.login(user.getUsername(), user.getPassword()));
    }

    // Endpoint to find the register user
    @GetMapping("/user/me")
    @SecurityRequirement(name = "api")
    public MeDto me(@RequestAttribute("id") Long id) {
        return meMapper.meDto(appUserService.findOne(id));
    }

}
