package com.electronicform.hei.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
    private String id = UUID.randomUUID().toString();
    private String reply;
    private String[] selected;

    @ManyToOne
    private Question question;

    @ManyToOne
    private AppUser appUser;

    public Answer(String reply, Question question, AppUser appUser) {
        this.reply = reply;
        this.question = question;
        this.appUser = appUser;
    }

    public Answer(String[] selected, Question question, AppUser appUser) {
        this.selected = selected;
        this.question = question;
        this.appUser = appUser;
    }

}