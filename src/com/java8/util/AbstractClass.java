package com.java8.util;

abstract class AbstractClass {
	
	public AbstractClass()
	{
		System.out.println("abstract class constructor.. ");
		
	}
	
	abstract void add(int a, int b );
	int sub(int a ,int b)
	{
		return -b;
	}
	static int multiplication(int a, int b)
	{
		return a*b;
	
	}
	

}
