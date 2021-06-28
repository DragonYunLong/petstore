package com.yunlong.api.service;

import java.util.List;

public interface IPetService {

    public List getCategory();
    public String getProducts(String catid);
    String getItems(String productid);
    String getItem(String productid,String itemid);
}
