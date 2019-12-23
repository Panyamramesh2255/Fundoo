package com.java8.util;

public interface Sayablee {
	default void say() {
		System.out.println("this is the default method ");
	}

	void saymore(String msg);

	static void sayLouder(String msg) {
		System.out.println("in static method:: " + msg);
	}

}
