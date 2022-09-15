package com.electronicform.hei.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.electronicform.hei.model.Form;
import com.electronicform.hei.model.Question;
import com.electronicform.hei.model.dto.formDto.CreateFormDto;
import com.electronicform.hei.repository.FormRepository;
import com.electronicform.hei.repository.QuestionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FormService {

    private final FormRepository formRepository;
    private final AppUserService appUserService;
    private final QuestionRepository questionRepository;

    // get all form
    public List<Form> getAllForm() {
        return formRepository.findAll();
    }

    // get form from id
    public Form getFormById(String uuid) {
        Form form = formRepository.findById(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Error: Form with id %s not found in database ", uuid)));
        return form;
    }

    // get all Form for the connected user
    public List<Form> getFormByUserId(Long id) {
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
        } catch (ResponseStatusException e) {
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Can't create form");
        }
        return form;
    }

    @Transactional
    public Form updateFormById(Long id, String uuid, CreateFormDto createFormDto) {
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
        } catch (ResponseStatusException e) {
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Can't update form");
        }

        return form;
    }

    public String deleteFormById(Long id, String uuid) {
        boolean existe = formRepository.existsById(uuid);
        if (!existe) {
            throw new IllegalStateException(
                    String.format("Error: Form with id %s don't exist in database ", uuid));
        }

        if (formRepository.findById(uuid).get().getAppUser().getId() != id) {
            throw new IllegalStateException("You don't have a permission to update this Form");
        }

        deleteAllQuestionForForm(uuid);

        formRepository.deleteById(uuid);

        return "Delete success";
    }

    private void deleteAllQuestionForForm(String uuid) {
        List<Question> questions = questionRepository.findAllQuestionByFormId(uuid);

        for (int i = 0; i < questions.size(); i++) {
            questionRepository.delete(questions.get(i));
        }
    }

}
