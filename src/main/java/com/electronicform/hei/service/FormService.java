package com.electronicform.hei.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.electronicform.hei.model.Form;
import com.electronicform.hei.model.dto.CreateFormDto;
import com.electronicform.hei.repository.FormRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FormService {

    private final FormRepository formRepository;
    private final AppUserService appUserService;

    // get all form
    public List<Form> getAllForm() {
        return formRepository.findAll();
    }

    // get form from id
    public Form getFormFromId(UUID uuid) {
        Form form = formRepository.findById(uuid).orElseThrow(
                () -> new IllegalStateException(
                        String.format("Error: Form with id %s don't exist in database ", uuid)));
        return form;
    }

    // get all Form for the connected user
    public List<Form> getFormFromUserId(Long id) {
        return formRepository.findAllFormByUserId(id);
    }

    // create new from for connected user
    public Form createForm(CreateFormDto createFormDto, Long id) {
        Form form = new Form();
        try {
            form.setTitle(createFormDto.getTitle());
            form.setDescription(createFormDto.getDescription());
            form.setAppUser(appUserService.findOne(id));
            formRepository.save(form);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        return form;
    }

    @Transactional
    public Form updateForm(Long id, UUID uuid, CreateFormDto createFormDto) {
        Form form = formRepository.findById(uuid).orElseThrow(
                () -> new IllegalStateException(
                        String.format("Error: Form with id %s don't exist in database ", uuid)));

        if (form.getAppUser().getId() != id) {
            throw new IllegalStateException("You don't have a permission to update this Form");
        }

        if (createFormDto.getTitle() != null && createFormDto.getTitle().length() > 0
                && !form.getTitle().equals(createFormDto.getTitle())) {
            form.setTitle(createFormDto.getTitle());
        }

        if (createFormDto.getDescription() != null && createFormDto.getDescription().length() > 0
                && !form.getDescription().equals(createFormDto.getDescription())) {
            form.setDescription(createFormDto.getDescription());
        }

        try {
            formRepository.save(form);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return form;
    }

    public String deleteForm(Long id, UUID uuid) {
        Form form = formRepository.findById(uuid).orElseThrow(
                () -> new IllegalStateException(
                        String.format("Error: Form with id %s don't exist in database ", uuid)));

        if (form.getAppUser().getId() != id) {
            throw new IllegalStateException("You don't have a permission to update this Form");
        }

        try {
            formRepository.deleteById(uuid);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "Delete success";
    }

}
