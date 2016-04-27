package ru.cadmy.finance.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

import ru.cadmy.finance.model.Role;
import ru.cadmy.finance.model.State;
import ru.cadmy.finance.model.User;
import ru.cadmy.finance.service.UserService;

/**
 * Created by Cadmy on 27.04.2016.
 */
@Controller
public class UserController {

    final static Logger logger = Logger.getLogger(PersonalFinanceSystemController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/signup")
    public String signup(Map<String, Object> map) {
        map.put("user", new User());
        map.put("userList", userService.getUserList());
        return "signup";
    }

    @RequestMapping(value = "/add_person", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("user") User user, BindingResult result) {
        user.setRole(Role.USER);
        user.setState(State.ACTIVE);
        userService.addUser(user);
        logger.info("User ".join(user.getUsername().toString()).join(" was created"));
        return "redirect:/";
    }
}
