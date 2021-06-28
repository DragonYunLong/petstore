package com.yunlong.provider.redis.dao;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import org.springframework.scripting.support.ResourceScriptSource;

import java.util.List;
import java.util.Set;

@Configuration
public class RedisDao {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisTemplate redisTemplate1;

    public void setString(String key,String value){
        ValueOperations valueOperations = redisTemplate1.opsForValue();
        valueOperations.set(key,value);

    }

    public Object getString(String key){
        ValueOperations valueOperations = redisTemplate1.opsForValue();
        return valueOperations.get(key);

    }

    public void setHash(String tables,String keys,Object values){
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put(tables,keys,values);
    }

    public Set getSets(String name,String key){
        Set set = redisTemplate.keys(name+":"+key);
        SetOperations setOperations = redisTemplate.opsForSet();
        Set set1 =  setOperations.union("",set);
        return set1;
    }

    public Set getSet(String key){
        SetOperations setOperations = redisTemplate.opsForSet();
        Set set = setOperations.members(key);
        return set;
    }


    public Object getHash(String name,String key){
        HashOperations hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(name,key);

    }

    public List getHashAll(String key){
        HashOperations hashOperations = redisTemplate.opsForHash();
        return hashOperations.values(key);

    }

    public List executeRedisScript(List input,String scriptName){
        DefaultRedisScript<List> defaultRedisScript = new DefaultRedisScript();
        defaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(scriptName)));
        defaultRedisScript.setResultType(List.class);
        List list = (List)redisTemplate.execute(defaultRedisScript,input);
        return list;
    }

    public List executeRedisScriptString(List input,String ScritName){
        DefaultRedisScript defaultRedisScript = new DefaultRedisScript();
        defaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(ScritName)));
        defaultRedisScript.setResultType(List.class);
        List list = (List)redisTemplate1.execute(defaultRedisScript,input);
        return list;
    }




    public void setSet(String name, Object value) {
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add(name,value);
    }
}
