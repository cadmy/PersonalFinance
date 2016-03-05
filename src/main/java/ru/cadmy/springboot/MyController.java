package ru.cadmy.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Cadmy on 05.03.2016.
 */
@Controller
public class MyController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
