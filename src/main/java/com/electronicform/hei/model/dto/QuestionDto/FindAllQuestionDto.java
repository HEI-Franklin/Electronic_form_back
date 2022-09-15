package com.electronicform.hei.model.dto.QuestionDto;

import java.util.List;

import com.electronicform.hei.model.Answer;
import com.electronicform.hei.model.questionType.QuestionType;

import lombok.Data;

@Data
public class FindAllQuestionDto {
    private String question;
    private QuestionType type;
    private String[] choise;
    private String formuuid;
    private List<Answer> answer;
}
