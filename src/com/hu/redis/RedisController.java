package com.hu.redis;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

@Controller
public class RedisController {

	@RequestMapping(value = "/Verify_code_CodeSender", method = RequestMethod.POST)
	@ResponseBody
	public String CodeSender(String phone_no) {

		String phoneNu = phone_no;
		String code = this.getCode(6);
		System.out.println(code);

		if (phoneNu == null || phoneNu == "") {
			return "手机号不能为空";

		} else {
			Jedis jedis = new Jedis("47.99.96.52", 6379);

			String key = "Verify_code:" + phoneNu + ":code";
			String count = "Verify_code:" + phoneNu + ":count";

			String countNum = jedis.get(count);
			if (countNum == null) {// 第一次访问
				jedis.set(count, "0");
				countNum = "0";
			}

			if (Integer.parseInt(countNum) == 0) {// 每日第一次访问
				jedis.setex(key, 120, code);
				jedis.setex(count, 24 * 60 * 60, "1");

				jedis.close();
				System.out.println("第一次发");
				return "true";
			} else if (Integer.parseInt(countNum) >= 1 && Integer.parseInt(countNum) <= 2) {
				jedis.setex(key, 120, code);
				jedis.incr(count);
				jedis.close();
				System.out.println("第二次发");
				return "true";

			} else {
				jedis.close();
				return "limit";
			}

		}

	}

	@RequestMapping(value = "/Verify_code_CodeVerify", method = RequestMethod.POST)
	@ResponseBody
	public String CodeVerify(String phone_no, String verify_code) {

		String phoneNu = phone_no;
		String code1 = verify_code;

		if (phoneNu == null || phoneNu == "") {
			return "手机号不能为空";

		} else {

			Jedis jedis = new Jedis("47.99.96.52", 6379);

			String key = "Verify_code:" + phoneNu + ":code";

			String code2 = jedis.get(key);

			if (code1.equals(code2)) {
				jedis.close();
				return "true";
			} else {
				jedis.close();
				return "false";
			}

		}

	}

	private String getCode(int length) {
		String code = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int rand = random.nextInt(10);
			code += rand;
		}
		return code;
	}
}
