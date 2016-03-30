package ru.cadmy.finance.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.criteria.*;

import ru.cadmy.finance.model.*;

/**
 * Created by Cadmy on 10.03.2016.
 */

@Service
public class UserServiceImpl extends ModelService implements UserService
{

    @Override
    @Transactional
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public List<User> getUserList() {
        CriteriaQuery<User> c = em.getCriteriaBuilder().createQuery(User.class);
        c.from(User.class);
        return em.createQuery(c).getResultList();
    }

    @Override
    @Transactional
    public void removeUser(Integer id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }

    @Override
    @Transactional
    public User getCurrentUser()
    {
        CriteriaQuery<User> criteriaQuery = em.getCriteriaBuilder().createQuery(User.class);
        Root<User> userRequest = criteriaQuery.from(User.class);

        Expression<String> exp = userRequest.get("id");
        Predicate predicate = exp.in(1);

        criteriaQuery.where(predicate);
        return em.createQuery(criteriaQuery).getSingleResult();
    }

}

