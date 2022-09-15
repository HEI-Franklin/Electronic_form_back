package com.electronicform.hei.model.dto.appUserDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAppUserDto {
    private Long id;
    private String username;
    private String email;
}
