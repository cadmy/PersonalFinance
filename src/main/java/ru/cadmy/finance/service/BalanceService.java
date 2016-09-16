package ru.cadmy.finance.service;

import ru.cadmy.finance.model.*;

import java.util.*;

/**
 * Created by Cadmy on 22.03.2016.
 */
public interface BalanceService {
    void addBalanceRecord(BalanceRecord balanceRecord);

    List<BalanceRecord> balanceRecordList();

    List<BalanceRecord> balanceRecordList(User user);

    List<BalanceRecord> balanceRecordList(Date date);

    List<BalanceRecord> balanceRecordList(User user, Date date);

    List<BalanceRecord> balanceRecordList(User user, Date dateFrom, Date dateTo);

    List<BalanceRecord> balanceRecordList(User user, String category);

    List<BalanceRecord> balanceRecordList(User user, Date date, String category);

    List<BalanceRecord> balanceRecordList(User user, Date dateFrom, Date dateTo, String category);

    BalanceRecord getBalanceRecordById(Long id);

    void editBalanceRecord(BalanceRecord balanceRecord);

    void removeBalanceRecord(Long balanceRecordId);
}

