package ru.cadmy.finance.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

import ru.cadmy.finance.model.BalanceRecord;
import ru.cadmy.finance.service.BalanceService;
import ru.cadmy.finance.service.UserService;


/**
 * Created by Cadmy on 27.04.2016.
 */
@Controller
public class BalanceRecordController {

    final static Logger logger = Logger.getLogger(ErrorController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BalanceService balanceService;


    @RequestMapping(value = {"/home", "/login", "/"})
    public String index(Map<String, Object> map) {
        map.put("balanceRecord", new BalanceRecord());
        map.put("balanceRecordList", balanceService.balanceRecordList(userService.getCurrentUser()));

        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBalanceRecord(@ModelAttribute("balanceRecord") BalanceRecord balanceRecord, BindingResult result) {
        balanceRecord.setUser(userService.getCurrentUser());
        balanceService.addBalanceRecord(balanceRecord);
        logger.info("BalanceRecord #" + balanceRecord.getId().toString() + " was created");
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{balanceRecordId}", method = RequestMethod.POST)
    public String deleteBalanceRecord(@PathVariable("balanceRecordId") Long balanceRecordId) {
        balanceService.removeBalanceRecord(balanceRecordId);
        logger.info("Balance record #" + balanceRecordId.toString() + " was deleted");
        return "redirect:/";
    }
}
