package com.yunlong.consumer.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.yunlong.api.model.Account;
import com.yunlong.api.model.MyConst;
import com.yunlong.api.service.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/account")
public class AccountController {

    @Reference(
            version = "1.0.0",
            interfaceName = "IAccountSercice",
            interfaceClass = IAccountService.class,
            timeout = 12000
    )
    private IAccountService accountService;

    @RequestMapping(value = "/login/{sessionid}",method = RequestMethod.POST)
    public ResponseEntity<Void> login(@RequestBody Account account, @PathVariable String sessionid){

        if(accountService.login(account,sessionid)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/welcome/{sessionid}",method = RequestMethod.GET)
    public ResponseEntity<String> welcome(@PathVariable String sessionid){
        if(sessionid.equals("")) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        String name  = accountService.getSession(sessionid);
        return new ResponseEntity<>(name,HttpStatus.OK);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public  ResponseEntity<Void> register(@RequestBody Account account){

        if(accountService.register(account)){
            return new ResponseEntity<Void>(HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/out/{sessionid}",method = RequestMethod.POST)
    public ResponseEntity<Void> out(@PathVariable String sessionid){
        accountService.out(sessionid);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
