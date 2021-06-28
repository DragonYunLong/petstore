package com.yunlong.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.yunlong.api.model.Account;
import com.yunlong.api.model.MyConst;
import com.yunlong.api.service.IAccountService;
import com.yunlong.provider.mapper.AccountMapper;
import com.yunlong.provider.mapper.ProfileMapper;
import com.yunlong.provider.redis.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@Service(
        version = "1.0.0",
        interfaceName = "IAccountService",
        interfaceClass = IAccountService.class
)
public class AccountService implements IAccountService {

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ProfileMapper profileMapper;

    @Override
    public boolean login(Account account,String sessionid) {

        Account a = (Account)redisDao.getHash("account",account.getUsername());
        if(a.getPassword().equals(account.getPassword())){
            redisDao.setString(sessionid,a.getName()+":"+a.getUserid());
            return true;
        }
        return false;
    }

    @Override
    public String getSession(String key) {
        Object o = redisDao.getString(key);
        if(o != null){
            String s = o.toString();
            return s;
        }else{
            return "未登录";
        }



    }

    @Override
    public boolean register(Account account) {
        Object object = redisDao.getHash("account",account.getUsername());
        if(object != null){
            return false;
        }
        accountMapper.insertSelective(account);
        account.getProfile().setUserid(account.getUserid());
        profileMapper.insertSelective(account.getProfile());
        redisDao.setHash("account",account.getUsername(),account);
        redisDao.setHash("profile",account.getProfile().getUserid().toString(),account.getProfile());
        return true;
    }

    @Override
    public void out(String sessionid) {
        List list = new ArrayList();
        list.add(sessionid);
        redisDao.executeRedisScriptString(list,"delString.lua");
    }
}
