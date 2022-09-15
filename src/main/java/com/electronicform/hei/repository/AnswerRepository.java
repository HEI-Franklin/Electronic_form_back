package com.electronicform.hei.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electronicform.hei.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, String> {

}
