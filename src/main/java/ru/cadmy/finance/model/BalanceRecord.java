package ru.cadmy.finance.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    @Type(type="date")
    private Date date;

    @Column(name = "sign")
    private boolean sign;

    @Column(name = "value", nullable = false)
    private Integer value;

    @Column(name = "category", nullable = true, length = 500)
    private String category;
}
