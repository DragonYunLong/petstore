--
-- Created by IntelliJ IDEA.
-- User: yunlong
-- Date: 2020/11/5
-- Time: 14:39
-- To change this template use File | Settings | File Templates.
--
local cart = redis.call("get","cart:"..KEYS[1]..":"..KEYS[2]..":"..KEYS[3])
return cart;

