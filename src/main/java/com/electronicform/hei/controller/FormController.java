package com.electronicform.hei.controller;

import java.util.List;
import java.util.UUID;

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

import com.electronicform.hei.model.dto.CreateFormDto;
import com.electronicform.hei.model.dto.FindFormForUserDto;
import com.electronicform.hei.model.mapper.FindFormForUserMapper;
import com.electronicform.hei.service.FormService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/forms")
public class FormController {

    private final FormService formService;
    private final FindFormForUserMapper findFormForUserMapper;

    @GetMapping("/all") // get list of all forms
    public List<FindFormForUserDto> getAllForm() {
        return formService.getAllForm().stream().map(
                findFormForUserMapper::findFormForUserDto).toList();
    }

    @GetMapping("/{uuid}") // get from from id
    public FindFormForUserDto getFormFromId(@PathVariable("uuid") UUID uuid) {
        return findFormForUserMapper.findFormForUserDto(formService.getFormFromId(uuid));
    }

    @GetMapping("/user") // get all forms for the connected user
    public List<FindFormForUserDto> getFormFromUserId(@RequestAttribute("id") Long id) {
        return formService.getFormFromUserId(id).stream().map(
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
    public FindFormForUserDto updateForm(
            @RequestAttribute("id") Long id, // Only the user login can update her Form
            @PathVariable("uuid") UUID uuid, // Form Id
            @RequestBody() CreateFormDto createFormDto) {
        return findFormForUserMapper.findFormForUserDto(formService.updateForm(id, uuid, createFormDto));
    }

    @DeleteMapping("/delete/{uuid}")
    public String deleteForm(
            @RequestAttribute("id") Long id, // Only the user login can delete her Form
            @PathVariable("uuid") UUID uuid // Form Id
    ) {
        return formService.deleteForm(id, uuid);
    }
}
