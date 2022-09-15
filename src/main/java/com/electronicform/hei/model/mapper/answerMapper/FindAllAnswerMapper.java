package com.electronicform.hei.model.mapper.answerMapper;

import org.springframework.stereotype.Component;

import com.electronicform.hei.model.Answer;
import com.electronicform.hei.model.dto.answerDto.FindAllAnswerDto;

@Component
public class FindAllAnswerMapper {
    public FindAllAnswerDto findAllAnswerDto(Answer answer) {
        return FindAllAnswerDto.builder()
                .id(answer.getId())
                .reply(answer.getReply())
                .selected(answer.getSelected())
                .question(answer.getQuestion().getQuestion())
                .appUser(answer.getAppUser().getUsername())
                .build();
    }
}
