package com.electronicform.hei.model.dto.formDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class FindFormForUserDto {
    private String id;
    private String title;
    private String description;
    private String username;
}
