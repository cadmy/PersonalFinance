package ru.cadmy.finance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "USER")
public @Data class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "email", nullable = false, length = 500)
    private String email;

    @Column(name = "login", nullable = false, length = 500)
    private String login;

    @Column(name = "password", nullable = false, length = 500)
    private String password;

    @Column(name = "first_name", nullable = false, length = 500)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 500)
    private String lastName;

    @Column(name = "role", nullable = false, length = 500)
    private String role;

}
