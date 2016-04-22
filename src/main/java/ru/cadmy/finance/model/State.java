package ru.cadmy.finance.model;

import javax.persistence.Enumerated;

public enum State
{
    NEW,
    ACTIVE,
    INACTIVE,
    DELETED,
    LOCKED;
}
