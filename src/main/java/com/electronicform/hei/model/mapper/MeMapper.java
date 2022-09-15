package com.electronicform.hei.model.mapper;

import org.springframework.stereotype.Component;

import com.electronicform.hei.model.AppUser;
import com.electronicform.hei.model.dto.AppUserDto.MeDto;

@Component
public class MeMapper {
    public MeDto meDto(AppUser appUser) {
        return MeDto.builder()
                .id(appUser.getId())
                .username(appUser.getUsername())
                .email(appUser.getEmail())
                .build();
    }
}
