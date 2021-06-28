package com.yunlong.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.yunlong.api.model.Cart;
import com.yunlong.api.model.CartExample;
import com.yunlong.api.model.Item;
import com.yunlong.api.model.Orders;
import com.yunlong.api.service.ICartService;
import com.yunlong.provider.mapper.CartMapper;
import com.yunlong.provider.mapper.OrdersMapper;
import com.yunlong.provider.redis.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(
        version = "1.0.0",
        interfaceName = "ICartService",
        interfaceClass = ICartService.class
)
public class CartService implements ICartService {

    @Autowired
    private RedisDao redisDao;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private CartMapper cartMapper;

    @Override
    public List getCart(String userid) {
        List list = new ArrayList();
        list.add(userid);
        List list1 = redisDao.executeRedisScriptString(list,"getMaxid.lua");
        List listc = new ArrayList();
        if(list1.get(0) == null){
            createCart(userid,1);
        }else{
            String maxid = list1.get(0).toString().substring(1);
            if(list1.get(0).toString().charAt(0) == '1'){
                list.add(maxid);
                List<String> list2 = redisDao.executeRedisScriptString(list, "getCartKeys.lua");
                for( String x : list2){
                    List list3 = new ArrayList();
                    Cart cart = new Cart();
                    cart.setUserid(Integer.parseInt(userid));
                    cart.setOrderid(Integer.parseInt(x.split(":")[2]));
                    cart.setItemid(Integer.parseInt(x.split(":")[3]));
                    cart.setQuantity(Integer.parseInt(redisDao.getString(x).toString()));
                    Item item = (Item) redisDao.getSets("item","*:"+x.split(":")[3]).toArray()[0];
                    cart.setItem(item);
                    listc.add(cart);
                }
            }else{
                createCart(userid,Integer.parseInt(maxid)+1);
            }
        }
        return listc;
    }

    @Override
    public void createCart(String userid,int max) {
        Orders orders = new Orders();
        orders.setUserid(Integer.parseInt(userid));
        orders.setOrderid(max);
        ordersMapper.insert(orders);
        redisDao.setString("maxid:"+userid,"1"+max);
    }

    @Override
    public void addCart(String userid, String itemid, String productid,String num) {
        List<String> listmax = new ArrayList();
        listmax.add(userid);
        List list1 = redisDao.executeRedisScriptString(listmax,"getMaxid.lua");
        if(list1.toArray()[0] == null){ // 如果没有购物车，创建一个购物车，从1开始。
            createCart(userid,1);
        }
        int max = Integer.parseInt(list1.toArray()[0].toString().substring(1));
        char s = list1.toArray()[0].toString().charAt(0);
        if(list1.toArray()[0].toString().charAt(0) == '0'){
            createCart(userid,max+1);
            max++;
        }
        int orderid = max;
        listmax.add(""+orderid);
        List<Cart> list = new ArrayList<>();
        Cart cart1 = new Cart();
        List<String> list2 = redisDao.executeRedisScriptString(listmax, "getCartKeys.lua");
        for( String x : list2){
            Cart cart = new Cart();
            cart.setUserid(Integer.parseInt(userid));
            cart.setOrderid(Integer.parseInt(x.split(":")[2]));
            cart.setItemid(Integer.parseInt(x.split(":")[3]));
            cart.setQuantity(Integer.parseInt(redisDao.getString(x).toString()));
            list.add(cart);
        }
        cart1.setItemid(Integer.parseInt(itemid));
        cart1.setUserid(Integer.parseInt(userid));
        cart1.setOrderid(orderid);
        cart1.setQuantity(Integer.parseInt(num));
        for(Cart x:list){
            if(x.getItemid() == Integer.parseInt(itemid)){
                int n = x.getQuantity()+Integer.parseInt(num);
                redisDao.setString("cart:"+userid+":"+orderid+":"+itemid,""+n);
                cart1.setQuantity(n);
                cartMapper.updateByPrimaryKey(cart1);
                return;
            }
        };
        redisDao.setString("cart:"+userid+":"+orderid+":"+itemid,num);
        cartMapper.insertSelective(cart1);

    }

    @Override
    public void delCart(String userid, String orderid, String itemid) {
        List list = new ArrayList();
        list.add(userid);
        list.add(orderid);
        list.add(itemid);
        Cart cart = new Cart();
        cart.setUserid(Integer.parseInt(userid));
        cart.setOrderid(Integer.parseInt(orderid));
        cart.setItemid(Integer.parseInt(itemid));
        cartMapper.deleteByPrimaryKey(cart);
        redisDao.executeRedisScriptString(list,"delCart.lua");
    }

    @Override
    public void check(Orders orders) {
        List list1 = new ArrayList();
        list1.add(orders.getUserid().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        orders.setOrderdate(new Date());
        List list = redisDao.executeRedisScriptString(list1,"getMaxid.lua");
        String orderid = list.toArray()[0].toString().substring(1);
        orders.setOrderid(Integer.parseInt(orderid));
        list1.add(orderid);
        list1.add(simpleDateFormat.format(new Date()));
        list1.add(orders.getTotalprice().toString());
        redisDao.executeRedisScriptString(list1,"checkCart.lua");
        ordersMapper.updateByPrimaryKey(orders);

    }



}
