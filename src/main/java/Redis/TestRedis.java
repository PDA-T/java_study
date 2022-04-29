package Redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestRedis {
	public static void main(String[] args) {
		// ����jedis
		Jedis jedis = new Jedis("127.0.0.1",6379);
		// ��������
		System.out.println(jedis.ping());
		// ���ü�ֵ
		jedis.set("k1","k2");
		// ��ȡֵ
		System.out.println(jedis.get("k1"));
		// �жϼ�ֵ�Ƿ����
		System.out.println(jedis.exists("k3"));
		// ��ȡ�����,��Ҫ����
		Set<String> keys = jedis.keys("*");
		// ���ö���ַ�����ֵ
		jedis.mset("str1","v1","str2","v2","str3","v3");
		// ��ȡ����ַ���ֵ
		System.out.println(jedis.mget("str1","str2","str3"));

		// ����map
		Map<String,String> map = new HashMap<>();
		// ��������
		map.put("aaa","aaa");
		map.put("bbb","bbb");
		map.put("ccc","ccc");
		map.put("ddd","ddd");
		// ����redis
		jedis.hmset("map",map);
		// ȡ��map����
		List<String> hmget = jedis.hmget("map", "aaa", "bbb");
		// ����ȡ��
		for (String s:hmget){
			// ������
			System.out.println(s);
		}

		// ����zset,�ڶ�������Ϊ����
		jedis.zadd("zset",5000,"v1");
	}
}
