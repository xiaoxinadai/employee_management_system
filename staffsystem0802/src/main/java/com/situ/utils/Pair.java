package com.situ.utils;

/**
 * 键值对
 * @author adai
 */
public class Pair<T1, T2> {
	private final T1 t1;
	private final T2 t2;
	public Pair(T1 t1, T2 t2) {
		super();
		this.t1 = t1;
		this.t2 = t2;
	}
	
	//静态方法
	public static <K,V>  Pair<K,V> of(K t1,V t2) {
		return new Pair<>(t1,t2);
	}
	
	public T1 getFirst() {
		return t1;
	}
	public T2 getSecond() {
		return t2;
	}
}
