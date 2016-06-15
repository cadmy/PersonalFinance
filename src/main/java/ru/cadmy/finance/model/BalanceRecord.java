package ru.cadmy.finance.model;

import lombok.Data;

import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Cadmy on 21.03.2016.
 */
@Entity
@Table(name = "BALANCE")
public
@Data
class BalanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user")
    private User user;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Type(type = "date")
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

    public BalanceRecord(){

    }

    public BalanceRecord(User user, Date date, Sign sign, Integer value, String category, String title){
        this.user = user;
        this.date =date;
        this.sign = sign;
        this.value = value;
        this.category = category;
        this.title = title;
    }

}
