package com.electronicform.hei.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.electronicform.hei.model.questionType.QuestionType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Question implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id = UUID.randomUUID().toString();
    @Column(nullable = false)
    private String question;
    @Enumerated(EnumType.STRING)
    private QuestionType type;
    private String[] choise;

    @ManyToOne
    private Form form;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answer;

    public Question(String question, QuestionType type, Form form) {
        this.question = question;
        this.type = type;
        this.form = form;
    }

    public Question(String question, QuestionType type, String[] choise, Form form) {
        this.question = question;
        this.type = type;
        this.choise = choise;
        this.form = form;
    }
}
