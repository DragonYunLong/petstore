--
-- Created by IntelliJ IDEA.
-- User: yunlong
-- Date: 2020/11/8
-- Time: 17:16
-- To change this template use File | Settings | File Templates.
--
redis.call("set","orders:"..KEYS[1]..":"..KEYS[2],KEYS[3]..KEYS[4])
redis.call("set","maxid:"..KEYS[1],"0"..KEYS[2])
