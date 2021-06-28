local maxid = redis.call("get","maxid:"..KEYS[1])
return maxid