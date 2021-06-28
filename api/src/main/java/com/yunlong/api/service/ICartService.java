package com.yunlong.api.service;

import com.yunlong.api.model.Cart;
import com.yunlong.api.model.Orders;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface ICartService {
    public List<Cart> getCart(String keys);
    public void createCart(String userid,int max);
    public void addCart(String userid,String itemid,String productid,String num);
    public void delCart(String userid,String orderid,String itemid);
    public void check(Orders orders);
}
