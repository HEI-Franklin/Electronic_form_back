package com.electronicform.hei.model.dto.appUserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class MeDto {
    private Long id;
    private String username;
    private String email;
}
