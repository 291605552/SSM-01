package com.hu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	
	private static volatile JedisPool jedisPool = null;
	
	private JedisPoolUtil() {
		
	}
	
	public static JedisPool getJedisPoolInstance() {
		if (null == jedisPool) {
			synchronized (JedisPoolUtil.class) {
				if (null == jedisPool) {
					JedisPoolConfig poolConfig =new JedisPoolConfig();
					poolConfig.setMaxTotal(200);
					poolConfig.setMaxIdle(32);
					poolConfig.setMaxWaitMillis(100*1000);
					poolConfig.setBlockWhenExhausted(true);
					poolConfig.setTestOnBorrow(true);
					
					jedisPool =new JedisPool(poolConfig,"47.99.96.52", 6379,6000);
				}
			}
		}
		return jedisPool;
	}
	
	public static void release(JedisPool jedisPool,Jedis jedis) {
		if (null != jedis) {
			jedisPool.close();
		}
	}

}
