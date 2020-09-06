package com.hu.redis;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class Seckill_redisByScript {
	
	//private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Seckill_redisByScript.class);
	
	public static void main(String[] args) throws IOException {
		JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
		
		Jedis jedis = jedisPool.getResource();
		
		System.out.println(jedis.ping());
		
		jedis.close();
		
		//测试集群
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		
		nodes.add(new HostAndPort("192.168.211.128", 6380));
		
		JedisCluster cluster=new JedisCluster(nodes);
		
		cluster.set("username", "admin");
		
		System.out.println("放值成功");
		
		cluster.close();
		
		
		
		
		
		
		
		
		//doSeKill("201","sk:0101");
	}
	
	static String secKillScript="local userid=KEYS[1];\r\n"+
	"local prodid=KEYS[2];\r\n"+
			"local qtkey='Sekill:'..prodid..\":kc\";\r\n"+
	"local usersKey='Seckill:'..prodid..\":user\";\r\n"+
			"local userExists=redis.call(\"sismember\",usersKey,userid);\r\n"+
	"if tonumber(userExists)==1 then \r\n"+
			"return 2;\r\n"+
	"end \r\n"+
			"local num = redis.call(\"get\",qtkey);\r\n"+
	"if tonumber(num)<= 0 then \r\n"+
			"return 0;\r\n"+
	"else \r\n"+
			"redis.call(\"decr\",qtkey);\r\n"+
	"redis.call(\"sadd\",usersKey,userid);\r\n"+
			"end\r\n"+
	"return 1";
	
	static String secKillScript2 = 
			"local userExists=redis.call(\"simember\",\"{sk}:0101\",userid;\r\n"+
	"return 1";
	
	public static boolean doDecKill(String uid,String prodid) throws IOException{
		JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
		
		Jedis jedis = jedisPool.getResource();
		
		String sha1 = jedis.scriptLoad(secKillScript);
		
		Object result = jedis.evalsha(sha1, 2, uid,prodid);
		
		String reString =String.valueOf(result);
		
		if ("0".equals(reString)) {
			System.out.println("已经抢完了！！");
		} else if("1".equals(reString)){
			System.out.println("抢购成功！！");
		} else if("2".equals(reString)) {
			System.out.println("该用户已经抢购过了!!");
		} else {
			System.out.println("抢购异常!!");
		}
		
		jedis.close();
		
		return true;
	}

}
