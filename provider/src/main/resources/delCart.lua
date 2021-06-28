--
-- Created by IntelliJ IDEA.
-- User: yunlong
-- Date: 2020/11/8
-- Time: 14:18
-- To change this template use File | Settings | File Templates.
--
redis.call("del","cart:"..KEYS[1]..":"..KEYS[2]..":"..KEYS[3])

