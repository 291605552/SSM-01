package com.hu.redis;

import java.io.IOException;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class SeKill_redis {

	//private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SeKill_redis.class);

	public static void main(String[] args) {
		//Jedis jedis = new Jedis("47.99.96.52", 6379);
		Jedis jedis = new Jedis("192.168.211.128", 6379);

		System.out.println(jedis.ping());
		jedis.close();
	}

	public static boolean doSeKill(String uid, String prodid) throws IOException {

		// 拼接key
		String kc = "doSekill:" + prodid + ":kc";

		String users = "doSekill:" + prodid + ":users";

		Jedis jedis = new Jedis("47.99.96.52", 6379);
		
		//监视库存
		jedis.watch(kc);

		// 秒杀还没开始，表示库存为null
		if (jedis.get(kc) == null) {
			//System.out.println(kc);
			System.out.println("秒杀还没有开始");
			jedis.close();
			return false;
		}
		// 已经秒杀成功，表示为存储uid的set集合中已经有该用户的uid
		if (jedis.sismember(users, uid)==true) {
			System.out.println("已经秒杀成功");
			jedis.close();
			return false;
		}
		// 判断库存，若大于0，则减库存，加人，若小于等于0，秒杀结束
		if (Integer.parseInt(jedis.get(kc)) <= 0) {
			System.out.println("秒杀结束");
			jedis.close();
			return false;
		} else {
			Transaction multi = jedis.multi();
			
			multi.decr(kc);
			multi.sadd(users, uid);
			
			List<Object> exec = multi.exec();
			
			if (exec == null || exec.size() == 0) {
				System.out.println("秒杀失败");
				jedis.close();
				return false;
			}
			
//			jedis.decr(kc);
//			jedis.sadd(users, uid);
			System.out.println("库存调整成功，已经往users里面放值");
			jedis.close();
			return true;

		}
	}

}
