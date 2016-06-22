package ru.cadmy.finance.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cadmy.finance.model.*;

import javax.persistence.criteria.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Cadmy on 22.03.2016.
 */
@Service
public class BalanceServiceImpl extends ModelService implements BalanceService {

    @Override
    @Transactional
    public void addBalanceRecord(BalanceRecord balanceRecord) {
        em.persist(balanceRecord);
    }

    @Override
    public List<BalanceRecord> balanceRecordList() {
        CriteriaQuery<BalanceRecord> c = em.getCriteriaBuilder().createQuery(BalanceRecord.class);
        c.from(BalanceRecord.class);
        return em.createQuery(c).getResultList();
    }

    @Override
    @Transactional
    public List<BalanceRecord> balanceRecordList(User user) {
        if (user != null && user.getState() != null && user.getState().equals(State.ACTIVE)) {
            CriteriaQuery<BalanceRecord> criteriaQuery = em.getCriteriaBuilder().createQuery(BalanceRecord.class);
            Root<BalanceRecord> balanceRequest = criteriaQuery.from(BalanceRecord.class);
            Expression<String> exp = balanceRequest.get("user");
            Predicate predicate = exp.in(user);
            criteriaQuery.where(predicate);
            return em.createQuery(criteriaQuery).getResultList();
        } else {
            return null;
        }
    }

    @Override
    public List<BalanceRecord> balanceRecordList(Date date) {
        return null;
    }

    @Override
    public List<BalanceRecord> balanceRecordList(User user, Date date) {
        return null;
    }

    @Override
    public List<BalanceRecord> balanceRecordList(User user, Date dateFrom, Date dateTo) {
        return null;
    }

    @Override
    public List<BalanceRecord> balanceRecordList(User user, String category) {
        return null;
    }

    @Override
    public List<BalanceRecord> balanceRecordList(User user, Date date, String category) {
        return null;
    }

    @Override
    public List<BalanceRecord> balanceRecordList(User user, Date dateFrom, Date dateTo, String category) {
        return null;
    }

    @Override
    public BalanceRecord getBalanceRecordById(Long id) {
        return em.find(BalanceRecord.class, id);
    }

    @Override
    @Transactional
    public void removeBalanceRecord(Long balanceRecordId) {
        BalanceRecord balanceRecord = em.find(BalanceRecord.class, balanceRecordId);
        if (balanceRecord != null) {
            em.remove(balanceRecord);
        }
    }
}



