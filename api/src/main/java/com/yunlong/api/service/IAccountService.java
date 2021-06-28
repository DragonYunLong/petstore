package com.yunlong.api.service;

import com.yunlong.api.model.Account;
import com.yunlong.api.model.MyConst;

public interface IAccountService {

    public boolean login(Account account,String sessionid);
    public String getSession(String key);
    public boolean register(Account account);
    public void out(String sessionid);

}
