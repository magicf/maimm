package com.laima.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author jiayi.zhang
 * 
 */
public class EhcacheUtil {

	private static Logger log = LoggerFactory.getLogger(EhcacheUtil.class);

	public static <K, V> void save(K key, V value) {
		Cache cache = BeanUtil.getBean("ehCache", Cache.class);
		Element element = new Element(key, value);
		cache.put(element);
		log.debug("更新缓存 key:" + key.toString() + "\t value:" + value.toString());
	}

	public static <K> boolean isExist(K key) {
		Cache cache = BeanUtil.getBean("ehCache", Cache.class);
		if (cache.isKeyInCache(key)) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static <K, V> V load(K key) {
		V value = null;
		Cache cache = BeanUtil.getBean("ehCache", Cache.class);
		if (cache.isKeyInCache(key)) {
			Element element = cache.get(key);
			value = (V) element.getObjectValue();
			log.debug("读取缓存 key:" + key.toString() + "\t value:" + value.toString());
		}
		return value;
	}

	public static <K> void del(K key) {
		Cache cache = BeanUtil.getBean("ehCache", Cache.class);
		if (cache.isKeyInCache(key)) {
			cache.remove(key);
		}
	}

	public static <V> void saveH(String key, String field, V value) {
		Map<String, V> cacheMap = load(key);
		if (cacheMap == null) {
			cacheMap = new HashMap<String, V>();
		}
		Collections.synchronizedMap(cacheMap).put(field, value);
		save(key, cacheMap);
	}

	public static <V> V loadH(String key, String field) {
		Map<String, V> cacheMap = load(key);
		if (cacheMap == null) {
			return null;
		}
		V v = cacheMap.get(field);
		return v;
	}

	public static void delH(String key, String... fields) {
		Map<String, Object> cacheMap = load(key);
		if (cacheMap == null) {
			return;
		}
		Map<String, Object> cachMapSync = Collections.synchronizedMap(cacheMap);
		for (String field : fields) {
			if (cachMapSync.containsKey(field))
				cachMapSync.remove(field);
		}
		save(key, cacheMap);
	}

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> syncMap = Collections.synchronizedMap(map);
		syncMap.put("1", "abc");
		System.out.println(map);
		map.put("2", "ccc");
		System.out.println(syncMap);
	}

}
