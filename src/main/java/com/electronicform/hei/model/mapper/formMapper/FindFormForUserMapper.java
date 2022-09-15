package com.electronicform.hei.model.mapper.formMapper;

import org.springframework.stereotype.Component;

import com.electronicform.hei.model.Form;
import com.electronicform.hei.model.dto.formDto.FindFormForUserDto;

@Component
public class FindFormForUserMapper {
    public FindFormForUserDto findFormForUserDto(Form form) {
        return FindFormForUserDto.builder()
                .id(form.getId())
                .title(form.getTitle())
                .description(form.getDescription())
                .username(form.getAppUser().getUsername())
                .build();
    }
}
