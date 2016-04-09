package ru.cadmy.finance.model;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Cadmy on 21.03.2016.
 */
@Entity
@Table(name = "BALANCE")
public @Data class BalanceRecord {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Column(name="date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Type(type="date")
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "sign")
    private Sign sign;

    @Column(name = "value", nullable = false)
    private Integer value;

    @Column(name = "category", nullable = true, length = 500)
    private String category;

    @Column(name = "title", nullable = true, length = 500)
    private String title;
}
