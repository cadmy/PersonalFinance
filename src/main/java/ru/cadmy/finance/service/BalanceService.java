package ru.cadmy.finance.service;

import java.util.List;

import ru.cadmy.finance.model.BalanceRecord;
import ru.cadmy.finance.model.User;

/**
 * Created by Cadmy on 22.03.2016.
 */
public interface BalanceService {
    void addBalanceRecord(BalanceRecord balanceRecord);

    List<BalanceRecord> balanceRecordList(User user);

    void removeBalanceRecord(Integer balanceRecordId);
}
