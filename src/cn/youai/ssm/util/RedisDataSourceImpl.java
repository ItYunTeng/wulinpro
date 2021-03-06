package cn.youai.ssm.util;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
@Repository
public class RedisDataSourceImpl implements RedisDataSource {
    private static final Logger log = LoggerFactory.getLogger(RedisDataSourceImpl.class);

    @Resource
    private ShardedJedisPool shardedJedisPool;

    public ShardedJedis getRedisClient() {
        try {
            ShardedJedis shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
            log.error("getRedisClent error", e);
        }
        return null;
    }

	@Override
	public void returnResource(ShardedJedis shardedJedis) {
		shardedJedisPool.returnResource(shardedJedis);
		
	}

	@Override
	public void returnResource(ShardedJedis shardedJedis, boolean broken) {
		if (broken) {
            shardedJedisPool.returnBrokenResource(shardedJedis);
        } else {
            shardedJedisPool.returnResource(shardedJedis);
        }
	}
	
}
