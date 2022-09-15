package com.electronicform.hei.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.electronicform.hei.model.Form;
import com.electronicform.hei.model.Question;
import com.electronicform.hei.model.dto.questionDto.CreateQuestionDto;
import com.electronicform.hei.model.dto.questionDto.UpdateQuestionDto;
import com.electronicform.hei.model.questionType.QuestionType;
import com.electronicform.hei.repository.FormRepository;
import com.electronicform.hei.repository.QuestionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final FormRepository formRepository;

    public List<Question> getAllQuestion() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(String uuid) {
        Question question = questionRepository.findById(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Error: Question with id %s not found in database ", uuid)));

        return question;
    }

    public List<Question> getQuestionByFormId(String uuid) {
        boolean formExist = formRepository.existsById(uuid);
        if (!formExist) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Error: Form with id %s not found in database ", uuid));
        }

        return questionRepository.findAllQuestionByFormId(uuid);
    }

    public String createQuestion(CreateQuestionDto[] questions) {
        try {
            Form form = formRepository.findById(questions[0].getFormUuid()).get();
            for (int i = 0; i < questions.length; i++) {
                Question question = new Question();
                question.setQuestion(questions[i].getQuestion());
                question.setType(questions[i].getType());
                if (questions[i].getType() == QuestionType.CHECKBOXES
                        || questions[i].getType() == QuestionType.MULTIPLE_CHOICE) {
                    question.setChoise(questions[i].getChoise());
                }
                question.setForm(form);
                questionRepository.save(question);
            }
        } catch (ResponseStatusException e) {
            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Serveur Error: Unable to create Question");
        }

        return "Create question with succes";
    }

    @Transactional
    public Question updateQuestionById(String uuid, UpdateQuestionDto updateQuestionDto) {
        boolean QuestionExist = questionRepository.existsById(uuid);
        if (!QuestionExist) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Error: Question with id %s not found in database ", uuid));
        }

        Question question = questionRepository.findById(uuid).get();

        try {

            if (updateQuestionDto.getQuestion() != null && updateQuestionDto.getQuestion().length() > 0
                    && !question.getQuestion().equals(updateQuestionDto.getQuestion())) {
                question.setQuestion(updateQuestionDto.getQuestion());
            }

            if (updateQuestionDto.getType() != null
                    && !question.getType().equals(updateQuestionDto.getType())) {
                question.setType(updateQuestionDto.getType());
            }

            if (updateQuestionDto.getChoise() != null && updateQuestionDto.getChoise().length > 0
                    && !question.getChoise().equals(updateQuestionDto.getChoise())) {
                question.setChoise(updateQuestionDto.getChoise());
            }
        } catch (ResponseStatusException e) {
            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Serveur Error: Unable to Update Question");
        }

        return question;
    }

    public String deleteQuestionById(String uuid) {
        boolean existe = questionRepository.existsById(uuid);
        if (!existe) {
            throw new IllegalStateException(
                    String.format("Error: Question with id %s not fond in database ", uuid));
        }

        questionRepository.deleteById(uuid);
        return "Question delete With Success";
    }

}
