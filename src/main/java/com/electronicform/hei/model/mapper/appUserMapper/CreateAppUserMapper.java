package com.electronicform.hei.model.mapper.appUserMapper;

import org.springframework.stereotype.Component;

import com.electronicform.hei.model.AppUser;
import com.electronicform.hei.model.dto.appUserDto.CreateAppUserDto;

@Component
public class CreateAppUserMapper {
    public CreateAppUserDto createAppUserDto(AppUser appUser) {
        return CreateAppUserDto.builder()
                .id(appUser.getId())
                .username(appUser.getUsername())
                .email(appUser.getEmail())
                .build();

    }
}
