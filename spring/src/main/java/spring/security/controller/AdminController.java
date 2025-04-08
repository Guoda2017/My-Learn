package main.java.spring.security.controller;

import main.java.spring.security.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: guofengbo
 * @date: 2020-06-10 18:07
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TestService testService;

    @GetMapping("/demo")
    public String demo() {
        return testService.demo();
    }

    @GetMapping("/demo2")
    @ResponseStatus(value = HttpStatus.MOVED_TEMPORARILY)
    public String demo2() {
        return testService.demo();
    }

}
