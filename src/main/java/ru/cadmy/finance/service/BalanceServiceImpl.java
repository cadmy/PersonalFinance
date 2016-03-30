package ru.cadmy.finance.service;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cadmy.finance.model.*;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Cadmy on 22.03.2016.
 */
@Service
public class BalanceServiceImpl extends ModelService implements BalanceService {
/*
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
*/
    @Override
    @Transactional
    public void addBalanceRecord(BalanceRecord balanceRecord) {
        em.persist(balanceRecord);
    }

    /*@Override
    @Transactional
    public List<BalanceRecord> balanceRecordList(User user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Balance where user = :user ");
        query.setParameter("user", user);
        return query.list();
    }*/


    @Override
    @Transactional
    public List<BalanceRecord> balanceRecordList(User user)
    {
        CriteriaQuery<BalanceRecord> criteriaQuery = em.getCriteriaBuilder().createQuery(BalanceRecord.class);
        Root<BalanceRecord> balanceRequest = criteriaQuery.from(BalanceRecord.class);
        Expression<String> exp = balanceRequest.get("user");
        Predicate predicate = exp.in(user);

        criteriaQuery.where(predicate);
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    @Transactional
    public void removeBalanceRecord(Integer id) {
        BalanceRecord balanceRecord = em.find(BalanceRecord.class, id);
        if (balanceRecord != null) {
            em.remove(balanceRecord);
        }
    }
}



