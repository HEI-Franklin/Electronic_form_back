package com.electronicform.hei.model.dto.answerDto;

import lombok.Data;

@Data
public class CreateAnswerDto {
    private String reply;
    private String[] selected;
    private String question;
}
