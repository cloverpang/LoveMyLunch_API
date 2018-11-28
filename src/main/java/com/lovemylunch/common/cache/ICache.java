package com.lovemylunch.common.cache;

public interface ICache<T> {

	void set(String key, T value, int expireTime);
	
	T get(String key);

	T get(String key, Class<T> clazz);

	boolean clear();

	boolean remove(String key);
}
