package ru.cadmy.finance.service;

/**
 * Created by Cadmy on 10.03.2016.
 */
import java.util.List;

import ru.cadmy.finance.model.User;

public interface PersonService {

    public void addPerson(User user);
    public List<User> listPeople();
    public void removePerson(Integer id);
}
