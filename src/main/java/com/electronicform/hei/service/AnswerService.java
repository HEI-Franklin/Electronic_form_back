package com.electronicform.hei.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.electronicform.hei.model.Answer;
import com.electronicform.hei.model.AppUser;
import com.electronicform.hei.model.Question;
import com.electronicform.hei.model.dto.answerDto.CreateAnswerDto;
import com.electronicform.hei.repository.AnswerRepository;
import com.electronicform.hei.repository.FormRepository;
import com.electronicform.hei.repository.QuestionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final FormRepository formRepository;
    private final AppUserService appUserService;

    public List<Answer> getAllAnswer() {
        return answerRepository.findAll();
    }

    public Answer getAnswerById(String uuid) {
        Answer answer = answerRepository.findById(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Error: Answer with id %s not found in database ", uuid)));

        return answer;
    }

    public List<Answer> getQuestionAnswerById(String uuid) {
        boolean questionExist = questionRepository.existsById(uuid);
        if (!questionExist) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Error: Question with id %s not found in database ", uuid));
        }

        return answerRepository.findAllAnswerByQuetionId(uuid);
    }

    public List<Answer> getAnswerByFormId(String uuid) {
        boolean formExist = formRepository.existsById(uuid);
        if (!formExist) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Error: Form with id %s not found in database ", uuid));
        }

        return answerRepository.findAllAnswerByFormId(uuid);
    }

    public String createAnswer(CreateAnswerDto[] createAnswerDto, Long id) {
        AppUser appUser = appUserService.findOne(id);
        try {
            for (int i = 0; i < createAnswerDto.length; i++) {
                Answer answer = new Answer();
                Question question = questionRepository.findById(createAnswerDto[i].getQuestion()).get();
                answer.setAppUser(appUser);
                answer.setQuestion(question);

                if (createAnswerDto[i].getSelected() != null) {
                    answer.setSelected(createAnswerDto[i].getSelected());
                }

                if (createAnswerDto[i].getReply() != null) {
                    answer.setReply(createAnswerDto[i].getReply());
                }

                answerRepository.save(answer);
            }

        } catch (ResponseStatusException e) {
            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Serveur Error: Unable to create Answer");
        }

        return "Create Answer with succes";
    }

}