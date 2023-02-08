package com.willy.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    Logger logger = LogManager.getLogger(getClass());

    @GetMapping(value = "/index")
    public String test() {
        logger.info("Controller");
        return "index";
    }

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
    }
}
