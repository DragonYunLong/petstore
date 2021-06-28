package com.yunlong.api.model;

import java.io.Serializable;

public class OrdersKey implements Serializable {
    private Integer userid;

    private Integer orderid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }
}