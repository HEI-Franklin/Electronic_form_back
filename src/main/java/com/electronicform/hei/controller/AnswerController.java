package com.electronicform.hei.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.electronicform.hei.model.dto.answerDto.CreateAnswerDto;
import com.electronicform.hei.model.dto.answerDto.FindAllAnswerDto;
import com.electronicform.hei.model.mapper.answerMapper.FindAllAnswerMapper;
import com.electronicform.hei.service.AnswerService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerService;
    private final FindAllAnswerMapper findAllAnswerMapper;

    @GetMapping("/all") // get list of all answer
    public List<FindAllAnswerDto> getAllQuestion() throws ResponseStatusException {
        return answerService.getAllAnswer().stream().map(findAllAnswerMapper::findAllAnswerDto).toList();
    }

    @GetMapping("/get/{uuid}") // get Answer by Id
    public FindAllAnswerDto getAnswerById(@PathVariable("uuid") String uuid) throws ResponseStatusException {
        return findAllAnswerMapper.findAllAnswerDto(answerService.getAnswerById(uuid));
    }

    @GetMapping("/quetion/{uuid}") // get all Answer for the Question parent
    public List<FindAllAnswerDto> getQuestionAnswerById(@PathVariable("uuid") String uuid)
            throws ResponseStatusException {
        return answerService.getQuestionAnswerById(uuid).stream().map(findAllAnswerMapper::findAllAnswerDto)
                .toList();
    }

    @GetMapping("/form/{uuid}") // get all Answer for the answer parent
    public List<FindAllAnswerDto> getAnswerByFormId(@PathVariable("uuid") String uuid)
            throws ResponseStatusException {
        return answerService.getAnswerByFormId(uuid).stream().map(findAllAnswerMapper::findAllAnswerDto)
                .toList();
    }

    @PostMapping("/create") // Create Answer for the Question
    public String createAnswer(
            @RequestBody() CreateAnswerDto[] createAnswerDto,
            @RequestAttribute("id") Long id) throws ResponseStatusException {
        return answerService.createAnswer(createAnswerDto, id);
    }
}
