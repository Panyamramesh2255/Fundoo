package com.java8.util;

@FunctionalInterface
public interface Readable {
	public String read(String name);
	static  void write()
	{
		System.out.println("test for functional interface  ");
	}

}
