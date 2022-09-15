package com.electronicform.hei.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.electronicform.hei.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {

    @Query(value = "Select q from Question q where q.form.id = ?1")
    List<Question> findAllQuestionByFormId(String uuid);

}
