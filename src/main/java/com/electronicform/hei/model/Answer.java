package com.electronicform.hei.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
public class Answer implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();
    private String reply;
    private String[] selected;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    public Answer(String reply, Question question) {
        this.reply = reply;
        this.question = question;
    }

    public Answer(String[] selected, Question question) {
        this.selected = selected;
        this.question = question;
    }

}