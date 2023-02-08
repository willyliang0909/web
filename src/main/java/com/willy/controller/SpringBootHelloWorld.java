package com.willy.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBootHelloWorld {

    Logger logger = LogManager.getLogger(getClass());

    @RequestMapping(value = "/rest/index")
    public String test() {
        logger.info("RestController");
        return "index";
    }
}
