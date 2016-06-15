package ru.cadmy.finance.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;

import ru.cadmy.finance.configuration.ApplicationContextConfig;
import ru.cadmy.finance.model.BalanceRecord;
import ru.cadmy.finance.model.Role;
import ru.cadmy.finance.model.Sign;
import ru.cadmy.finance.model.State;
import ru.cadmy.finance.model.User;

import static org.junit.Assert.*;


/**
 * Created by Cadmy on 02.06.2016.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(classes = ApplicationContextConfig.class)
@ActiveProfiles("dev")
public class BalanceServiceImplIT {
    @Autowired
    BalanceService balanceService;

    @Test
    @Rollback(true)
    public void addBalanceRecord() throws Exception {
        User user = new User("IT@test.com", "test", "test", "test", Role.USER, State.ACTIVE, "IT");
        BalanceRecord balanceRecord = new BalanceRecord(user, Date.valueOf("2016-06-02"), Sign.INCOME, 1000, "Test", "Test");
        balanceService.addBalanceRecord(balanceRecord);
        BalanceRecord balanceRecordTest = balanceService.getBalanceRecordById(balanceRecord.getId());
        assertEquals(balanceRecord, balanceRecordTest);
    }

}