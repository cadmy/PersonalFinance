package ru.cadmy.finance.service;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.cadmy.finance.configuration.ApplicationContextConfig;
import ru.cadmy.finance.model.*;

import java.sql.Date;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


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

    @Test
    @Rollback(true)
    @Ignore
    public void addBalanceRecordWithSameData() throws Exception {
        User user = new User("IT@test.com", "test", "test", "test", Role.USER, State.ACTIVE, "IT");
        BalanceRecord balanceRecord1 = new BalanceRecord(user, Date.valueOf("2016-06-02"), Sign.INCOME, 1000, "Test", "Test");
        BalanceRecord balanceRecord2 = new BalanceRecord(user, Date.valueOf("2016-06-02"), Sign.INCOME, 1000, "Test", "Test");
        balanceService.addBalanceRecord(balanceRecord1);
        balanceService.addBalanceRecord(balanceRecord2);
        BalanceRecord balanceRecordTest1 = balanceService.getBalanceRecordById(balanceRecord1.getId());
        BalanceRecord balanceRecordTest2 = balanceService.getBalanceRecordById(balanceRecord2.getId());
        assertThat(balanceRecordTest1, not(balanceRecordTest2));
    }

    @Test
    @Rollback(true)
    public void removeBalanceRecord() throws Exception {
        User user = new User("IT@test.com", "test", "test", "test", Role.USER, State.ACTIVE, "IT");
        BalanceRecord balanceRecord = new BalanceRecord(user, Date.valueOf("2016-06-02"), Sign.INCOME, 1000, "Test", "Test");
        balanceService.addBalanceRecord(balanceRecord);
        long balanceRecordId = balanceRecord.getId();
        balanceService.removeBalanceRecord(balanceRecordId);
        BalanceRecord balanceRecordTest = balanceService.getBalanceRecordById(balanceRecordId);
        assertEquals(balanceRecordTest, null);
    }

    @Test
    @Rollback(true)
    public void getBalanceRecordList() throws Exception {
        User user = new User("IT@test.com", "test", "test", "test", Role.USER, State.ACTIVE, "IT");
        BalanceRecord balanceRecord = new BalanceRecord(user, Date.valueOf("2016-06-02"), Sign.INCOME, 1000, "Test", "Test");
        balanceService.addBalanceRecord(balanceRecord);
        long id = balanceRecord.getId();
        balanceService.removeBalanceRecord(id);
        BalanceRecord balanceRecordTest = balanceService.getBalanceRecordById(balanceRecord.getId());
        assertEquals(balanceRecord, balanceRecordTest);
    }


}