package com.electronicform.hei.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.electronicform.hei.model.Form;

@Repository
public interface FormRepository extends JpaRepository<Form, String> {

    @Query(value = "Select f from Form f where f.appUser.id = ?1")
    List<Form> findAllFormByUserId(Long id);
}
