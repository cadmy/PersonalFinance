package ru.cadmy.finance.service;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Cadmy on 22.03.2016.
 */
public abstract class ModelService {
    @PersistenceContext
    EntityManager em;
}
