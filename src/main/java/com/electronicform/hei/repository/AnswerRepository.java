package com.electronicform.hei.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.electronicform.hei.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, String> {

    @Query(value = "Select a from Answer a where a.question.id = ?1")
    List<Answer> findAllAnswerByQuetionId(String uuid);

    @Query(value = "Select a from Answer a where a.question.form.id = ?1")
    List<Answer> findAllAnswerByFormId(String uuid);

}
