package ru.cadmy.finance.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import ru.cadmy.finance.model.User;

/**
 * Created by Cadmy on 10.03.2016.
 */

@Service
public class PersonServiceImpl extends ModelService implements PersonService {

    @Override
    @Transactional
    public void addPerson(User user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public List<User> listPeople() {
        CriteriaQuery<User> c = em.getCriteriaBuilder().createQuery(User.class);
        c.from(User.class);
        return em.createQuery(c).getResultList();
    }

    @Override
    @Transactional
    public void removePerson(Integer id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }

}

