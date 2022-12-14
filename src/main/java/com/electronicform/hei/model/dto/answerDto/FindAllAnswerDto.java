package com.electronicform.hei.model.dto.answerDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindAllAnswerDto {
    private String id;
    private String reply;
    private String[] selected;
    private String question;
    private String appUser;
}
