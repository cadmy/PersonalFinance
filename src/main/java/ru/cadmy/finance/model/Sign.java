package ru.cadmy.finance.model;

import lombok.Getter;

/**
 * Created by Cadmy on 29.03.2016.
 */
@Getter
public enum Sign {
    COST(1),  //1 store in database
    INCOME(2); //2

    private int value;

    Sign(int value) {
        this.value = value;
    }

    public static Sign valueOf(int value) {
        for (Sign sign : values()) {
            if (sign.value == value) {
                return sign;
            }
        }
        throw new IllegalArgumentException("No enum for value " + value);
    }
}