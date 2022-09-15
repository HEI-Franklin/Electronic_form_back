package com.electronicform.hei.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Form implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id = UUID.randomUUID().toString();
    @Column(columnDefinition = "varchar(150) default 'Formulaire sans titre'")
    private String title;
    @Column(columnDefinition = "varchar(255) default 'Formulaire sans description'")
    private String description;

    @ManyToOne
    private AppUser appUser;

    @OneToMany(mappedBy = "id", cascade = CascadeType.REMOVE)
    private List<Question> question;

    public Form(String title, String description, AppUser appUser) {
        this.title = title;
        this.description = description;
        this.appUser = appUser;
    }
}
