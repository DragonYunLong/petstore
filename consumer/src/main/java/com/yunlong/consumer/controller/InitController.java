package com.yunlong.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yunlong.api.service.IInitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/init")
public class InitController {

    @Reference(version = "1.0.0",interfaceName = "IInitService",
            interfaceClass = IInitService.class,
            timeout = 120000)
    private IInitService service;


    @RequestMapping("/init")
    public ResponseEntity<String> init(){
        System.out.println("ok");
        service.init();
        return new ResponseEntity<String>("True",HttpStatus.OK);
    }
}
