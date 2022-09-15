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

import com.electronicform.hei.model.dto.questionDto.CreateQuestionDto;
import com.electronicform.hei.model.dto.questionDto.FindAllQuestionDto;
import com.electronicform.hei.model.dto.questionDto.UpdateQuestionDto;
import com.electronicform.hei.model.mapper.FindAllQuestionMapper;
import com.electronicform.hei.service.QuestionService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final FindAllQuestionMapper findAllQuestionMapper;

    @GetMapping("/all") // get list of all Question
    public List<FindAllQuestionDto> getAllQuestion() throws ResponseStatusException {
        return questionService.getAllQuestion().stream().map(findAllQuestionMapper::findAllQuestionDto).toList();
    }

    @GetMapping("/get/{uuid}") // get question from id
    public FindAllQuestionDto getQuestionById(@PathVariable("uuid") String uuid) throws ResponseStatusException {
        return findAllQuestionMapper.findAllQuestionDto(questionService.getQuestionById(uuid));
    }

    @GetMapping("/form/{uuid}") // get all Question for the Form parent
    public List<FindAllQuestionDto> getQuestionFromFormId(@PathVariable("uuid") String uuid)
            throws ResponseStatusException {
        return questionService.getQuestionByFormId(uuid).stream().map(findAllQuestionMapper::findAllQuestionDto)
                .toList();
    }

    @PostMapping("/create") // Create Question for the connected user
    public String createQuestion(
            @RequestBody() CreateQuestionDto[] questions) throws ResponseStatusException {
        return questionService.createQuestion(questions);
    }

    // update Question with id
    @PutMapping("/update/{uuid}")
    public FindAllQuestionDto updateQuestionById(
            @PathVariable("uuid") String uuid,
            @RequestBody() UpdateQuestionDto updateQuestionDto) throws ResponseStatusException {// Question Id
        return findAllQuestionMapper.findAllQuestionDto(questionService.updateQuestionById(uuid, updateQuestionDto));
    }

    @DeleteMapping("/delete/{uuid}")
    public String deleteQuestionById(
            @PathVariable("uuid") String uuid // Form Id
    ) throws ResponseStatusException {
        return questionService.deleteQuestionById(uuid);
    }
}
