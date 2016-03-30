package ru.cadmy.finance.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import ru.cadmy.finance.model.BalanceRecord;
import ru.cadmy.finance.model.User;

/**
 * Created by Cadmy on 22.03.2016.
 */
@Service
public class BalanceServiceImpl extends ModelService implements BalanceService {

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

    @Override
    @Transactional
    public void addBalanceRecord(BalanceRecord balanceRecord) {
        em.persist(balanceRecord);
    }

    @Override
    @Transactional
    public List<BalanceRecord> listBalanceRecords(User user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Balance where user = :user ");
        query.setParameter("user", user);
        return query.list();
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



