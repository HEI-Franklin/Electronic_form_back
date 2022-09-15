package com.electronicform.hei.model.mapper;

import org.springframework.stereotype.Component;

import com.electronicform.hei.model.Question;
import com.electronicform.hei.model.dto.questionDto.FindAllQuestionDto;

@Component
public class FindAllQuestionMapper {
    public FindAllQuestionDto findAllQuestionDto(Question question) {
        return FindAllQuestionDto.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .type(question.getType())
                .choise(question.getChoise())
                .formuuid(question.getForm().getId())
                .answerNumber(question.getAnswer().size())
                .build();
    }
}