package ru.cadmy.finance.service;

import java.util.List;

import ru.cadmy.finance.model.BalanceRecord;
import ru.cadmy.finance.model.User;

/**
 * Created by Cadmy on 22.03.2016.
 */
public interface BalanceService {
    public void addBalanceRecord(BalanceRecord balanceRecord);
    public List<?> listBalanceRecords(User user);
    public void removeBalanceRecord(Integer id);
}
