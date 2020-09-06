package com.hu.redis;

import java.io.IOException;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SekillController {
	@RequestMapping(value = "dosekill")
	@ResponseBody
	public String dosekill(String prodid) throws IOException {
		// System.out.println("¥ÚÕ®¡À");

		String userid = new Random().nextInt(50000) + "";
		
		String prodid0=prodid;
		
		//boolean if_success=SeKill_redis.doSeKill(userid,prodid0);
		
		boolean if_success = Seckill_redisByScript.doDecKill(userid, prodid0);
		
		String result=if_success+"";
 
		return result;
	}

}
