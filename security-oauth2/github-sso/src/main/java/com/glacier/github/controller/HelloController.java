package com.glacier.github.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-02-20 18:27
 */
@RestController
public class HelloController {
    /**
     * @return
     */
    @RequestMapping("/")
    public String home() {
        return "Hello World";
    }
}
