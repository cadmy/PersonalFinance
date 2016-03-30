package ru.cadmy.finance.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ru.cadmy.finance.model.BalanceRecord;
import ru.cadmy.finance.model.User;
import ru.cadmy.finance.service.BalanceService;
import ru.cadmy.finance.service.BalanceServiceImpl;
import ru.cadmy.finance.service.PersonService;

/**
 * Created by Cadmy on 05.03.2016.
 */
@Controller
public class MyController {

    final static Logger logger = Logger.getLogger(MyController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private BalanceService balanceService;

    @RequestMapping(value = {"/home", "/login", "/"})
    public String index(Map<String, Object> map) {

        map.put("person", new User());
        map.put("peopleList", personService.listPeople());

        return "index";
    }

    @RequestMapping("/signup")
    public String signup() {
        return "signup";
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("user") User user, BindingResult result) {
        personService.addPerson(user);
        logger.info(String.join(" was created"));
        return "redirect:/";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("balanceRecord") BalanceRecord balanceRecord, BindingResult result) {
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
