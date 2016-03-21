package ru.cadmy.finance.service;

/**
 * Created by Cadmy on 10.03.2016.
 */
import java.util.List;

import ru.cadmy.finance.model.Person;

public interface PersonService {

    public void addPerson(Person person);
    public List<Person> listPeople();
    public void removePerson(Integer id);
}
