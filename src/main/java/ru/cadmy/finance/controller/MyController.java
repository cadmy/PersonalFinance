package ru.cadmy.finance.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ru.cadmy.finance.model.BalanceRecord;
import ru.cadmy.finance.model.User;
import ru.cadmy.finance.service.BalanceService;
import ru.cadmy.finance.service.UserService;

/**
 * Created by Cadmy on 05.03.2016.
 */
@Controller
public class MyController {

    final static Logger logger = Logger.getLogger(MyController.class);

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

    @RequestMapping("/signup")
    public String signup(Map<String, Object> map) {
        map.put("user", new User());
        map.put("userList", userService.getUserList());
        return "signup";
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("user") User user, BindingResult result) {
        userService.addUser(user);
        logger.info(String.join(" was created"));
        return "redirect:/";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBalanceRecord(@ModelAttribute("balanceRecord") BalanceRecord balanceRecord, BindingResult result) {
        balanceRecord.setUser(userService.getCurrentUser());
        balanceRecord.setDate(new Date());
        balanceService.addBalanceRecord(balanceRecord); //TODO breakpoint here and evaluate balanceRecord and everything will become clear
        logger.info(String.join(" was created"));
        return "redirect:/";
    }

    /** Error page. */
    @RequestMapping("/error.html")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        while (throwable != null) {
            errorMessage.append("<li>").append(escapeTags(throwable.getMessage())).append("</li>");
            throwable = throwable.getCause();
        }
        errorMessage.append("</ul>");
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error.html";
    }

    /** Substitute 'less than' and 'greater than' symbols by its HTML entities. */
    private String escapeTags(String text) {
        if (text == null) {
            return null;
        }
        return text.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }
}
