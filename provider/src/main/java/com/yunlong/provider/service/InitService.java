package com.yunlong.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.yunlong.api.model.*;
import com.yunlong.api.service.IInitService;
import com.yunlong.provider.mapper.*;
import com.yunlong.provider.redis.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(
        version = "1.0.0",
        interfaceName = "IInitService",
        interfaceClass = IInitService.class)
public class InitService implements IInitService {
    public InitService(){

    }

    @Autowired
    private RedisDao redisDao;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public void init() {
        List list  = new ArrayList();
        redisDao.executeRedisScript(list,"flushdb.lua");
        initAccount();
        initCategory();
        initProfile();
        initProduct();
        initItem();
        initorders();
        initCart();
    }

    private void initAccount(){
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andUseridIsNotNull();
        List<Account> list  = accountMapper.selectByExample(accountExample);
        list.forEach(x->redisDao.setHash("account",x.getUsername(),x));
    }
    private void initCategory(){
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andCatidIsNotNull();
        List<Category> list  = categoryMapper.selectByExample(categoryExample);
        list.forEach(x->redisDao.setHash("category",x.getCatno(),x));
    }
    private void initProfile(){
        ProfileExample profileExample  = new ProfileExample();
        profileExample.createCriteria().andUseridIsNotNull();
        List<Profile> list = profileMapper.selectByExample(profileExample);
        list.forEach(x->redisDao.setHash("profile",x.getUserid().toString(),x));
    }

    private void initProduct(){
       ProductExample productExample = new ProductExample();
       productExample.createCriteria().andProductidIsNotNull();
       List<Product> list  =  productMapper.selectByExample(productExample);
       list.forEach(x->redisDao.setSet("product:"+x.getCatid()+":"+x.getProductid(),x));
    }

    private void initItem(){
        ItemExample itemExample = new ItemExample();
        itemExample.createCriteria().andItemidIsNotNull();
        List<Item> list  =  itemMapper.selectByExample(itemExample);
        list.forEach(x->redisDao.setSet("item:"+x.getProductid()+":"+x.getItemid(),x));
    }

    public void initorders(){
        OrdersExample ordersExample = new OrdersExample();
        ordersExample.createCriteria().andOrderidIsNotNull();
        List<Orders> list = ordersMapper.selectByExample(ordersExample);
        list.forEach(x->{
            if (x.getOrderdate()!= null){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = simpleDateFormat.format(x.getOrderdate());
                redisDao.setString("orders:"+x.getUserid()+":"+x.getOrderid(),date+":"+x.getTotalprice());
            }
        });

        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andUseridIsNotNull();
        List<Account> list1 = accountMapper.selectByExample(accountExample);
        list1.forEach(x->{
            Optional optional = list.stream().filter(y->x.getUserid()==y.getUserid()).max((s1, s2)->s1.getOrderid()-s2.getOrderid());
            if(optional.isPresent()){
                Orders orders = (Orders) optional.get();
                redisDao.setString("maxid:"+orders.getUserid(),(orders.getOrderdate()!=null?"0":"1")+orders.getOrderid());
            }
        });
    }

    public void initCart(){
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andUseridIsNotNull();
        List<Cart> list = cartMapper.selectByExample(cartExample);
        list.forEach(x->redisDao.setString("cart:"+x.getUserid()+":"+x.getOrderid()+":"+x.getItemid(),x.getQuantity().toString()));
    }

}
