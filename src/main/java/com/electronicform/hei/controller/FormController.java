package com.electronicform.hei.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.electronicform.hei.model.dto.formDto.CreateFormDto;
import com.electronicform.hei.model.dto.formDto.FindFormForUserDto;
import com.electronicform.hei.model.mapper.formMapper.FindFormForUserMapper;
import com.electronicform.hei.service.FormService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/forms")
public class FormController {

    private final FormService formService;
    private final FindFormForUserMapper findFormForUserMapper;

    @GetMapping("/all") // get list of all forms
    public List<FindFormForUserDto> getAllForm() throws ResponseStatusException {
        return formService.getAllForm().stream().map(
                findFormForUserMapper::findFormForUserDto).toList();
    }

    @GetMapping("/{uuid}") // get from by id
    public FindFormForUserDto getFormById(@PathVariable("uuid") String uuid) throws ResponseStatusException {
        return findFormForUserMapper.findFormForUserDto(formService.getFormById(uuid));
    }

    @GetMapping("/user") // get all forms for the connected user
    public List<FindFormForUserDto> getFormByUserId(@RequestAttribute("id") Long id) throws ResponseStatusException {
        return formService.getFormByUserId(id).stream().map(
                findFormForUserMapper::findFormForUserDto).toList();
    }

    @PostMapping("/create") // Create form for the connected user
    public FindFormForUserDto createForm(
            @RequestBody() CreateFormDto createFormDto,
            @RequestAttribute("id") Long id) throws ResponseStatusException {
        return findFormForUserMapper.findFormForUserDto(formService.createForm(createFormDto, id));
    }

    // update form with id
    @PutMapping("/update/{uuid}")
    public FindFormForUserDto updateFormById(
            @RequestAttribute("id") Long id, // Only the user login can update her Form
            @PathVariable("uuid") String uuid, // Form Id
            @RequestBody() CreateFormDto createFormDto) throws ResponseStatusException {
        return findFormForUserMapper.findFormForUserDto(formService.updateFormById(id, uuid, createFormDto));
    }

    // Delete form from Id
    @DeleteMapping("/delete/{uuid}")
    public String deleteFormById(
            @RequestAttribute("id") Long id, // Only the user login can delete her Form
            @PathVariable("uuid") String uuid // Form Id
    ) throws ResponseStatusException {
        return formService.deleteFormById(id, uuid);
    }
}
