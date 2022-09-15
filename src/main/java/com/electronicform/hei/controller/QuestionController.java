package com.electronicform.hei.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.electronicform.hei.model.Question;
import com.electronicform.hei.model.dto.QuestionDto.CreateQuestionDto;
import com.electronicform.hei.model.dto.QuestionDto.UpdateQuestionDto;
import com.electronicform.hei.service.QuestionService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/all") // get list of all Question
    public List<Question> getAllQuestion() throws ResponseStatusException {
        return questionService.getAllQuestion();
    }

    @GetMapping("/get/{uuid}") // get question from id
    public Question getQuestionById(@PathVariable("uuid") String uuid) throws ResponseStatusException {
        return questionService.getQuestionById(uuid);
    }

    @GetMapping("/form/{uuid}") // get all Question for the Form parent
    public List<Question> getQuestionFromFormId(@PathVariable("uuid") String uuid) throws ResponseStatusException {
        return questionService.getQuestionByFormId(uuid);
    }

    @PostMapping("/create") // Create Question for the connected user
    public String createQuestion(
            @RequestBody() CreateQuestionDto[] questions) throws ResponseStatusException {
        return questionService.createQuestion(questions);
    }

    // update Question with id
    @PutMapping("/update/{uuid}")
    public Question updateQuestionById(
            @PathVariable("uuid") String uuid,
            @RequestBody() UpdateQuestionDto updateQuestionDto) throws ResponseStatusException {// Question Id
        return questionService.updateQuestionById(uuid, updateQuestionDto);
    }

    @DeleteMapping("/delete/{uuid}")
    public String deleteQuestionById(
            @PathVariable("uuid") String uuid // Form Id
    ) throws ResponseStatusException {
        return questionService.deleteQuestionById(uuid);
    }
}
