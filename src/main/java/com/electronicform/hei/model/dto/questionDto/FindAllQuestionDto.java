package com.electronicform.hei.model.dto.questionDto;

import com.electronicform.hei.model.questionType.QuestionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllQuestionDto {
    private String id;
    private String question;
    private QuestionType type;
    private String[] choise;
    private String formuuid;
    private Integer answerNumber;
}
