package com.yunlong.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.yunlong.api.model.Orders;
import com.yunlong.api.service.ICartService;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.math.BigDecimal;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    @Reference(
            version = "1.0.0",
            interfaceName = "ICartService",
            interfaceClass = ICartService.class,
            timeout = 120000
    )
    private ICartService cartService;

    @RequestMapping(value = "/getCart/{userid}",method = RequestMethod.GET)
    public ResponseEntity<String> getCart(@PathVariable String userid){
        String json = JSON.toJSONString(cartService.getCart(userid));
        return new ResponseEntity<String>(json,HttpStatus.OK);
    }

    @RequestMapping(value = "/addCart/{userid}/{itemid}/{productid}/{num}",method = RequestMethod.PUT)
    public ResponseEntity<String> addCart(@PathVariable String userid,@PathVariable String itemid,@PathVariable String productid,@PathVariable String num){
        cartService.addCart(userid, itemid, productid, num);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    @RequestMapping(value = "/del/{userid}/{orderid}/{itemid}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> del(@PathVariable String userid,@PathVariable String orderid,@PathVariable String itemid){
        cartService.delCart(userid,orderid,itemid);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/check/{userid}/{totalprice}",method = RequestMethod.POST)
    public ResponseEntity<Void> check(@PathVariable String userid,@PathVariable String totalprice){
        Orders order = new Orders();
        order.setUserid(Integer.parseInt(userid));
        order.setTotalprice(BigDecimal.valueOf(Double.parseDouble(totalprice)));
        cartService.check(order);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
