package ru.cadmy.finance.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.cadmy.finance.model.BalanceRecord;
import ru.cadmy.finance.service.*;

import java.util.Map;

import static ru.cadmy.finance.controller.UserController.HIDDEN_STYLE;


/**
 * Created by Cadmy on 27.04.2016.
 */
@Controller
public class BalanceRecordController {

    private final static Logger logger = Logger.getLogger(BalanceRecordController.class);
    private String systemMessage = "";
    private String messageStyle = HIDDEN_STYLE;

    @Autowired
    private UserService userService;

    @Autowired
    private BalanceService balanceService;


    @RequestMapping(value = {"/home", "/login", "/", "/PersonalFinance"})
    public String index(Map<String, Object> map) {
        map.put("balanceRecord", new BalanceRecord());
        map.put("balanceRecordList", balanceService.balanceRecordList(userService.getCurrentUser()));
        map.put("messageStyle", messageStyle);
        map.put("systemMessage", systemMessage);
        clearSystemMessage();
        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBalanceRecord(@ModelAttribute("balanceRecord") BalanceRecord balanceRecord, BindingResult result) {
        balanceRecord.setUser(userService.getCurrentUser());
        balanceService.addBalanceRecord(balanceRecord);
        logger.info("BalanceRecord #".join(balanceRecord.getId().toString()).join(" was created"));
        return "redirect:/PersonalFinance/";
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String refreshBalanceTable() {
        if (!balanceService.balanceRecordList().isEmpty())
        {
            StringBuilder jsonResult =  new StringBuilder();
            jsonResult.append("{ \"data\": [");
            for (BalanceRecord balanceRecord : balanceService.balanceRecordList())
            {
                jsonResult.append("[");
                jsonResult.append("\"");
                jsonResult.append(balanceRecord.getDate().toString());
                jsonResult.append("\" ,\"");
                jsonResult.append(balanceRecord.getCategory());
                jsonResult.append("\" ,\"");
                jsonResult.append(balanceRecord.getTitle());
                jsonResult.append("\" ,\"");
                jsonResult.append(balanceRecord.getSign().toString());
                jsonResult.append("\" ,");
                jsonResult.append(balanceRecord.getValue().toString());
                jsonResult.append("],");
            }
            jsonResult.deleteCharAt(jsonResult.length()-1);
            jsonResult.append("]}");
            return jsonResult.toString();
        }
        return "{}";
    }

    @RequestMapping(value = "/delete/{balanceRecordId}", method = RequestMethod.POST)
    public String deleteBalanceRecord(@PathVariable("balanceRecordId") Long balanceRecordId) {
        balanceService.removeBalanceRecord(balanceRecordId);
        logger.info("Balance record #".join(balanceRecordId.toString()).join(" was deleted"));
        return "redirect:/PersonalFinance/";
    }

    public void getSystemMessage(String message, String style){
        messageStyle = style;
        systemMessage = message;
    }

    private void clearSystemMessage() {
        messageStyle = HIDDEN_STYLE;
        systemMessage = "";
    }
}
