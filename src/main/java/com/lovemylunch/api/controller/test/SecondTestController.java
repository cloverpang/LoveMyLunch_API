package com.lovemylunch.api.controller.test;

import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@RequestMapping(value={"/{center}/test"})
@Api(tags = {"test2"}, description = "test 2 APIs")
public class SecondTestController {
    @RequestMapping(value={"/test"}, method= RequestMethod.GET)
    public String search(@PathVariable String center){
        return "test : " + center;
    }
}
