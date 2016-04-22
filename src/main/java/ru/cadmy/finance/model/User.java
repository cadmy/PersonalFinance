package ru.cadmy.finance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.security.core.userdetails.User;

@Entity
@Table(name = "USER")
public @Data class User
{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name="user")
    private org.springframework.security.core.userdetails.User user;

    @Column(name = "email", nullable = false, length = 500)
    private String email;

    @Column(name = "first_name", nullable = false, length = 500)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 500)
    private String lastName;

}
