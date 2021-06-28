package com.yunlong.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.yunlong.api.model.Cart;
import com.yunlong.api.service.IPetService;
import com.yunlong.provider.redis.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(
        version = "1.0.0",interfaceName = "IPetService",
        interfaceClass = IPetService.class
)
public class PetService implements IPetService {
    @Autowired
    private RedisDao redisDao;
    @Override
    public List getCategory() {
        return redisDao.getHashAll("category");
    }

    @Override
    public String getProducts(String catid) {

        String json = JSONObject.toJSONString(redisDao.getSets("product",catid+"*"));
        return json;
    }

    @Override
    public String getItems(String productid) {
        String json = JSONObject.toJSONString(redisDao.getSets("item",productid+"*"));
        return json;
    }

    @Override
    public String getItem(String productid, String itemid) {
        String json = JSONObject.toJSONString(redisDao.getSet("item:"+productid+":"+itemid));
        return json;
    }


}
