package ru.cadmy.finance.service;

import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import javax.persistence.NoResultException;
import javax.persistence.criteria.*;

import ru.cadmy.finance.model.User;
import ru.cadmy.finance.model.State;

/**
 * Created by Cadmy on 10.03.2016.
 */

@Service("userServiceImpl")
public class UserServiceImpl extends ModelService implements UserService, UserDetailsService
{
    private Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

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
    @Transactional(readOnly=true)
    public User getCurrentUser()
    {
        return getUserByUsername(getCurrentUsername());
    }

    @Override
    @Transactional(readOnly=true)
    public String getCurrentUsername()
    {
        if (authentication != null) {
            return authentication.getName();
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly=true)
    public User getUserById(Integer userId)
    {
        CriteriaQuery<User> criteriaQuery = em.getCriteriaBuilder().createQuery(User.class);
        Root<User> userRequest = criteriaQuery.from(User.class);

        Expression<String> exp = userRequest.get("id");
        Predicate predicate = exp.in(userId);

        criteriaQuery.where(predicate);
        return em.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public User getUserByUsername(String username)
    {
        CriteriaQuery<User> criteriaQuery = em.getCriteriaBuilder().createQuery(User.class);
        Root<User> userRequest = criteriaQuery.from(User.class);

        Expression<String> exp = userRequest.get("username");
        Predicate predicate = exp.in(username);

        criteriaQuery.where(predicate);
        try {
            return em.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return new User();
        }
    }

    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = getUserByUsername(username);
        if(user.getUsername() == null){
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getState().equals(State.ACTIVE), true, true, true, getGrantedAuthorities(user));
    }


    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+ user.getRole()));
        return authorities;
    }
}

