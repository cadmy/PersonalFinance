package ru.cadmy.finance.model;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "USER")
public
@Data
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "email", unique = true, nullable = false, length = 500)
    private String email;

    @Column(name = "username", unique = true, nullable = false, length = 500)
    private String username;

    @Column(name = "password", nullable = false, length = 500)
    private String password;

    @Column(name = "first_name", nullable = false, length = 500)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 500)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private State state;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

}
