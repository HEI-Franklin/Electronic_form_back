package com.electronicform.hei.model.dto.QuestionDto;

import com.electronicform.hei.model.questionType.QuestionType;

import lombok.Data;

@Data
public class UpdateQuestionDto {
    private String question;
    private QuestionType type;
    private String choise[];
}
