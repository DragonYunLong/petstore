package com.yunlong.consumer.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.yunlong.api.service.IPetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/pet")
public class PetController {

    @Reference(
            version = "1.0.0",interfaceName = "IPetService",
            interfaceClass = IPetService.class,
            timeout = 120000
    )
    private IPetService petService;

    @RequestMapping(value = "queryAll",method = RequestMethod.GET)
    public ResponseEntity<String> queryAll(){
        List list = petService.getCategory();
        String s = JSONObject.toJSONString(list);
        return new ResponseEntity<String>(s, HttpStatus.OK);
    }

    @RequestMapping(value = "/queryProducts/{catid}",method = RequestMethod.GET)
    public ResponseEntity<String> queryProducts(@PathVariable String catid){
        String json = petService.getProducts(catid);
        return new ResponseEntity<String>(json,HttpStatus.OK);
    }

    @RequestMapping(value = "/queryItems/{productid}",method = RequestMethod.GET)
    public ResponseEntity<String> queryItems(@PathVariable String productid){
        String json = petService.getItems(productid);
        return new ResponseEntity<String>(json,HttpStatus.OK);
    }

    @RequestMapping(value = "/queryItem/{productid}/{itemid}",method = RequestMethod.GET)
    public ResponseEntity<String> queryItem(@PathVariable String productid,@PathVariable String itemid){
        String json = petService.getItem(productid, itemid);
        return new ResponseEntity<String>(json,HttpStatus.OK);
    }
}
