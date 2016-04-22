package ru.cadmy.finance.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.cadmy.finance.model.*;
import ru.cadmy.finance.service.*;

import javax.servlet.http.HttpServletRequest;
import java.text.*;
import java.util.*;

/**
 * Created by Cadmy on 05.03.2016.
 */
@Controller
public class MyController {

    final static Logger logger = Logger.getLogger(MyController.class);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private UserService userService;

    @Autowired
    private BalanceService balanceService;

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

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
        //TODO breakpoint here and evaluate balanceRecord and everything will become clear
        balanceRecord.setUser(userService.getCurrentUser());
        try
        {
            balanceRecord.setDate( dateFormat.parse( result.getFieldValue("date").toString() ) );
        }
        catch (ParseException e)
        {
            balanceRecord.setDate( new Date());
        }

        balanceService.addBalanceRecord(balanceRecord);
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
