package ru.cadmy.finance.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;

import ru.cadmy.finance.configuration.ApplicationContextConfig;
import ru.cadmy.finance.model.BalanceRecord;
import ru.cadmy.finance.model.Sign;
import ru.cadmy.finance.model.User;

import static org.junit.Assert.*;


/**
 * Created by Cadmy on 02.06.2016.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(classes = ApplicationContextConfig.class)
public class BalanceServiceImplIT {
    @Autowired
    BalanceService balanceService;

    @Mock
    User user = Mockito.mock(User.class);

    @Ignore
    @Test
    @Rollback(true)
    public void addBalanceRecord() throws Exception {
        BalanceRecord balanceRecord = new BalanceRecord(user, Date.valueOf("2016-06-02"), Sign.INCOME, 1000, "Test", "Test");
        balanceService.addBalanceRecord(balanceRecord);
        BalanceRecord balanceRecordTest = balanceService.getBalanceRecordById(balanceRecord.getId());
        assertEquals(balanceRecord, balanceRecordTest);
    }

    @Test
    public void balanceRecordList() throws Exception {

    }

    @Test
    public void balanceRecordList1() throws Exception {

    }

    @Test
    public void balanceRecordList2() throws Exception {

    }

    @Test
    public void balanceRecordList3() throws Exception {

    }

    @Test
    public void balanceRecordList4() throws Exception {

    }

    @Test
    public void balanceRecordList5() throws Exception {

    }

    @Test
    public void balanceRecordList6() throws Exception {

    }

    @Test
    public void balanceRecordList7() throws Exception {

    }

    @Test
    public void getBalanceRecordById() throws Exception {

    }

    @Test
    public void removeBalanceRecord() throws Exception {

    }

}