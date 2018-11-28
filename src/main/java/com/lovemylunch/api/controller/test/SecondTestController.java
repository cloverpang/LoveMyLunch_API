package com.lovemylunch.api.controller.test;

import com.lovemylunch.api.config.ServiceConfig;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@RequestMapping(value={"/{center}"})
@Api(tags = {"test2"}, description = "test 2 APIs")
public class SecondTestController {
    @Value("${base.url}")
    private String baseURL;

    @Autowired
    private ServiceConfig serviceConfig;


    @RequestMapping(value={"/test"}, method= RequestMethod.GET)
    public String test(@PathVariable String center){
        return "test : " + center;
    }

    @RequestMapping(value={"/baseUrl"}, method= RequestMethod.GET)
    public String baseuRL(@PathVariable String center){
        return "baseURL : " + serviceConfig.getBaseURL();
    }
}
