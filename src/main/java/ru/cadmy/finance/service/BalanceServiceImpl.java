package ru.cadmy.finance.service;

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

    @Override
    @Transactional
    public void addBalanceRecord(BalanceRecord balanceRecord) {
        em.persist(balanceRecord);
    }

    @Override
    @Transactional
    public List<BalanceRecord> balanceRecordList(User user)
    {
        if (user.getState() != State.NEW){
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
    @Transactional
    public void removeBalanceRecord(Integer id) {
        BalanceRecord balanceRecord = em.find(BalanceRecord.class, id);
        if (balanceRecord != null) {
            em.remove(balanceRecord);
        }
    }
}



