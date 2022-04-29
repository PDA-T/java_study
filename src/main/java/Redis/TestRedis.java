package Redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestRedis {
	public static void main(String[] args) {
		// 连接jedis
		Jedis jedis = new Jedis("127.0.0.1",6379);
		// 测试连接
		System.out.println(jedis.ping());
		// 设置键值
		jedis.set("k1","k2");
		// 获取值
		System.out.println(jedis.get("k1"));
		// 判断键值是否存在
		System.out.println(jedis.exists("k3"));
		// 获取多个键,需要遍历
		Set<String> keys = jedis.keys("*");
		// 设置多个字符串键值
		jedis.mset("str1","v1","str2","v2","str3","v3");
		// 获取多个字符串值
		System.out.println(jedis.mget("str1","str2","str3"));

		// 创建map
		Map<String,String> map = new HashMap<>();
		// 存入数据
		map.put("aaa","aaa");
		map.put("bbb","bbb");
		map.put("ccc","ccc");
		map.put("ddd","ddd");
		// 放入redis
		jedis.hmset("map",map);
		// 取出map数据
		List<String> hmget = jedis.hmget("map", "aaa", "bbb");
		// 遍历取出
		for (String s:hmget){
			// 输出结果
			System.out.println(s);
		}

		// 存入zset,第二个参数为排序
		jedis.zadd("zset",5000,"v1");
	}
}
