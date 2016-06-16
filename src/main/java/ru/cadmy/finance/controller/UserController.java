package ru.cadmy.finance.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.cadmy.finance.model.*;
import ru.cadmy.finance.service.UserService;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by Cadmy on 27.04.2016.
 */
@Controller
public class UserController {

    final static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/signup")
    public String signup(Map<String, Object> map) {
        map.put("user", new User());
        map.put("userList", userService.getUserList());
        return "signup";
    }

    @RequestMapping(value = "/add_person", method = RequestMethod.POST)
    public String addPerson(@Valid @ModelAttribute("user") User user, BindingResult result) {
        user.setRole(Role.USER);
        user.setState(State.ACTIVE);
        if (userService.addUser(user))
        {
            logger.info("User ".join(user.getUsername()).join(" was created"));
            return "redirect:/";
        }
        else
        {
            logger.info("User ".join(user.getUsername()).join(" already exists"));

            return "signup :: failure-details";
        }
    }
}
