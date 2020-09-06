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

		// ƴ��key
		String kc = "doSekill:" + prodid + ":kc";

		String users = "doSekill:" + prodid + ":users";

		Jedis jedis = new Jedis("47.99.96.52", 6379);
		
		//���ӿ��
		jedis.watch(kc);

		// ��ɱ��û��ʼ����ʾ���Ϊnull
		if (jedis.get(kc) == null) {
			//System.out.println(kc);
			System.out.println("��ɱ��û�п�ʼ");
			jedis.close();
			return false;
		}
		// �Ѿ���ɱ�ɹ�����ʾΪ�洢uid��set�������Ѿ��и��û���uid
		if (jedis.sismember(users, uid)==true) {
			System.out.println("�Ѿ���ɱ�ɹ�");
			jedis.close();
			return false;
		}
		// �жϿ�棬������0�������棬���ˣ���С�ڵ���0����ɱ����
		if (Integer.parseInt(jedis.get(kc)) <= 0) {
			System.out.println("��ɱ����");
			jedis.close();
			return false;
		} else {
			Transaction multi = jedis.multi();
			
			multi.decr(kc);
			multi.sadd(users, uid);
			
			List<Object> exec = multi.exec();
			
			if (exec == null || exec.size() == 0) {
				System.out.println("��ɱʧ��");
				jedis.close();
				return false;
			}
			
//			jedis.decr(kc);
//			jedis.sadd(users, uid);
			System.out.println("�������ɹ����Ѿ���users�����ֵ");
			jedis.close();
			return true;

		}
	}

}
