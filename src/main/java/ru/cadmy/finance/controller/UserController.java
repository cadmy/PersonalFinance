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


    private final static Logger logger = Logger.getLogger(UserController.class);
    public static final String HIDDEN_STYLE = "hidden";
    public static final String ALERT_SUCCESS_STYLE = "alert alert-success";
    public static final String ALERT_DANGER_STYLE = "alert alert-danger";
    private String systemMessage = "";
    private String messageStyle = HIDDEN_STYLE;

    @Autowired
    private UserService userService;

    @Autowired
    BalanceRecordController balanceRecordController;

    @RequestMapping("/signup")
    public String signup(Map<String, Object> map) {
        map.put("user", userService.getCurrentUser());
        map.put("userList", userService.getUserList());
        map.put("messageStyle", messageStyle);
        map.put("systemMessage", systemMessage);
        map.put(messageStyle, HIDDEN_STYLE);
        clearSystemMessage();
        return "signup";
    }

    @RequestMapping(value = "/add_person", method = RequestMethod.POST)
    public String addPerson(@Valid @ModelAttribute("user") User user, BindingResult result) {
        user.setRole(Role.USER);
        user.setState(State.ACTIVE);
        if (userService.addUser(user))
        {
            logger.info("User ".join(user.getUsername()).join(" was created"));
            balanceRecordController.getSystemMessage("User was successfully created", ALERT_SUCCESS_STYLE);
            return "redirect:/";
        }
        else
        {
            logger.info("User ".join(user.getUsername()).join(" already exists"));
            messageStyle = ALERT_DANGER_STYLE;
            systemMessage =  "Username already exists";
            return "redirect:/signup";
        }
    }

    private void clearSystemMessage() {
        messageStyle = HIDDEN_STYLE;
        systemMessage = "";
    }
}
