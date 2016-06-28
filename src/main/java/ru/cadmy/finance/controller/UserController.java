package ru.cadmy.finance.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.cadmy.finance.model.*;
import ru.cadmy.finance.service.*;

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
        UserAdditionResults additionResult = userService.addUser(user);
        switch (additionResult) {
            case SUCCESS:
                logger.info("User ".join(user.getUsername()).join(" was created"));
                balanceRecordController.getSystemMessage("User was successfully created", ALERT_SUCCESS_STYLE);
                return "redirect:/";
            case USERNAME_EXISTS:
                logger.info("Username ".join(user.getUsername()).join(" already exists"));
                messageStyle = ALERT_DANGER_STYLE;
                systemMessage = "Username already exists";
                return "redirect:/signup";
            case EMAIL_EXISTS:
                logger.info("Email ".join(user.getEmail()).join(" already exists"));
                messageStyle = ALERT_DANGER_STYLE;
                systemMessage = "Email already exists";
                return "redirect:/signup";
            default:
                logger.info("Something strange in your neighbourhood \nWhile user creation");
                messageStyle = ALERT_DANGER_STYLE;
                systemMessage = "Something has gone wrong";
                return "redirect:/signup";
        }
    }

    private void clearSystemMessage() {
        messageStyle = HIDDEN_STYLE;
        systemMessage = "";
    }
}
