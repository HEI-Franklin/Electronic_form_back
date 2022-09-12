package com.electronicform.hei.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    private UUID id = UUID.randomUUID();
    @Column(nullable = false)
    private String question;
    @Enumerated(EnumType.STRING)
    private QuestionType type;
    private String[] choise;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "form_id", referencedColumnName = "id")
    private Form from;

    public Question(String question, QuestionType type, Form from) {
        this.question = question;
        this.type = type;
        this.from = from;
    }

    public Question(String question, QuestionType type, String[] choise, Form from) {
        this.question = question;
        this.type = type;
        this.choise = choise;
        this.from = from;
    }
}
