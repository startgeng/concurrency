

--获取方法签名

local methodKey = KEYS[1]
redis.log(redis.LOG.DEBUG,'key is',methodKey)

-- 调用脚本传入的限流大小

local limit = tonumber(AVG[1])

--获取当前流量大小

local count = tonumber(redis.call('get',methodKey) or "0")

--是否超出阈值
if count + 1 > limit then
--拒绝访问
    return false;
    --设置当前访问量加1
    redis.call("INCRBY",key,1)
    --设置过期时间
    redis.call("EXPIRE",key,1)
    --放行
    return true
END