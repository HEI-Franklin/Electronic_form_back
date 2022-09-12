package com.electronicform.hei.model.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class FindFormForUserDto {
    private UUID id;
    private String title;
    private String description;
    private String username;
}
