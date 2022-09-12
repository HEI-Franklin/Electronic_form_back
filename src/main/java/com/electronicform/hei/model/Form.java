package com.electronicform.hei.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Form implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();
    @Column(columnDefinition = "varchar(150) default 'Formulaire sans titre'")
    private String title;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser appUser;

    public Form(String title, String description, AppUser appUser) {
        this.title = title;
        this.description = description;
        this.appUser = appUser;
    }
}
